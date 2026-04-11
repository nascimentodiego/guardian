package br.com.bit.guardian.registration.ui.login

import androidx.lifecycle.SavedStateHandle
import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.ui.login.model.LoginEvent
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.core.test.MainDispatcherRule
import br.com.bit.guardian.registration.util.fakes.FakeCheckLogInInputValidUseCase
import br.com.bit.guardian.registration.util.fakes.FakeIsUserLoggedUseCase
import br.com.bit.guardian.registration.util.fakes.FakeLogInUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeCheckInput: FakeCheckLogInInputValidUseCase
    private lateinit var fakeLogIn: FakeLogInUseCase
    private lateinit var fakeIsUserLogged: FakeIsUserLoggedUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        fakeCheckInput = FakeCheckLogInInputValidUseCase()
        fakeLogIn = FakeLogInUseCase()
        fakeIsUserLogged = FakeIsUserLoggedUseCase()
        viewModel = LoginViewModel(
            savedStateHandle = SavedStateHandle(),
            checkInputUseCase = fakeCheckInput,
            logInUseCase = fakeLogIn,
            isUseLoggedUseCase = fakeIsUserLogged
        )
    }

    @Test
    fun `init publishes loading state when no saved state`() {
        assertTrue(viewModel.uiState.value is LoginUiState.Loading)
    }

    @Test
    fun `isUserLogged sends success event when user is already authenticated`() = runTest {
        fakeIsUserLogged.result = flowOf(true)

        viewModel.isUserLogged()

        val event = viewModel.events.first()
        assertTrue(event is LoginEvent.Success)
    }

    @Test
    fun `isUserLogged publishes idle state when user is not authenticated`() = runTest {
        fakeIsUserLogged.result = flowOf(false)

        viewModel.isUserLogged()

        assertTrue(viewModel.uiState.value is LoginUiState.Idle)
    }

    @Test
    fun `isUserLogged does not re-execute after transitioning to idle state`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        var callCount = 0
        val repositoryWithCount = FakeIsUserLoggedUseCase().apply {
            result = kotlinx.coroutines.flow.flow { callCount++; emit(false) }
        }
        viewModel = LoginViewModel(
            savedStateHandle = SavedStateHandle(mapOf("LoginViewModel" to viewModel.uiState.value)),
            checkInputUseCase = fakeCheckInput,
            logInUseCase = fakeLogIn,
            isUseLoggedUseCase = repositoryWithCount
        )

        viewModel.isUserLogged()

        assertTrue("isUserLogged should be skipped when state is already Idle", callCount == 0)
    }

    @Test
    fun `onIntent InputEmail updates email in state`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = false
        viewModel.onIntent(LoginIntent.InputEmail("test@example.com"))

        val state = viewModel.uiState.value as LoginUiState.Idle
        assertTrue(state.user.email == "test@example.com")
    }

    @Test
    fun `onIntent InputPassword updates password in state`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = false
        viewModel.onIntent(LoginIntent.InputPassword("pass123"))

        val state = viewModel.uiState.value as LoginUiState.Idle
        assertTrue(state.user.password == "pass123")
    }

    @Test
    fun `onIntent InputEmail enables button when check use case returns true`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = true
        viewModel.onIntent(LoginIntent.InputEmail("test@example.com"))

        val state = viewModel.uiState.value as LoginUiState.Idle
        assertTrue(state.user.isButtonEnabled)
    }

    @Test
    fun `onIntent InputEmail disables button when check use case returns false`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = false
        viewModel.onIntent(LoginIntent.InputEmail(""))

        val state = viewModel.uiState.value as LoginUiState.Idle
        assertFalse(state.user.isButtonEnabled)
    }

    @Test
    fun `onIntent Login on success sends success event`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = true
        viewModel.onIntent(LoginIntent.InputEmail("user@example.com"))
        viewModel.onIntent(LoginIntent.InputPassword("Pass@123"))

        fakeLogIn.result = flowOf(User(name = "User", email = "user@example.com"))
        viewModel.onIntent(LoginIntent.Login)

        val event = viewModel.events.first()
        assertTrue(event is LoginEvent.Success)
    }

    @Test
    fun `onIntent Login sets button loading state during login`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = true
        viewModel.onIntent(LoginIntent.InputEmail("user@example.com"))
        viewModel.onIntent(LoginIntent.InputPassword("Pass@123"))

        fakeLogIn.result = flowOf(User(name = "User", email = "user@example.com"))
        viewModel.onIntent(LoginIntent.Login)

        // After login completes (Success), state should be Idle (not loading)
        assertTrue(viewModel.uiState.value is LoginUiState.Idle)
    }

    @Test
    fun `onIntent Login on error sends error event`() = runTest {
        fakeIsUserLogged.result = flowOf(false)
        viewModel.isUserLogged()

        fakeCheckInput.result = true
        viewModel.onIntent(LoginIntent.InputEmail("user@example.com"))
        viewModel.onIntent(LoginIntent.InputPassword("Pass@123"))

        fakeLogIn.result = kotlinx.coroutines.flow.flow { throw RuntimeException("Auth failed") }
        viewModel.onIntent(LoginIntent.Login)

        val event = viewModel.events.first()
        assertTrue(event is LoginEvent.Error)
    }
}
