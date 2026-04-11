package br.com.bit.guardian.registration.ui.register

import androidx.lifecycle.SavedStateHandle
import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationEvent
import br.com.bit.guardian.registration.ui.register.model.RegistrationIntent
import br.com.bit.guardian.core.test.MainDispatcherRule
import br.com.bit.guardian.registration.util.fakes.FakeCreateUserUseCase
import br.com.bit.guardian.registration.util.fakes.FakeEmailValidationUseCase
import br.com.bit.guardian.registration.util.fakes.FakePasswordValidationUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeEmailUseCase: FakeEmailValidationUseCase
    private lateinit var fakePasswordUseCase: FakePasswordValidationUseCase
    private lateinit var fakeCreateUserUseCase: FakeCreateUserUseCase
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        fakeEmailUseCase = FakeEmailValidationUseCase()
        fakePasswordUseCase = FakePasswordValidationUseCase()
        fakeCreateUserUseCase = FakeCreateUserUseCase()
        viewModel = RegisterViewModel(
            savedStateHandle = SavedStateHandle(),
            useCase = fakeCreateUserUseCase,
            emailUseCase = fakeEmailUseCase,
            passwordUseCase = fakePasswordUseCase
        )
    }

    @Test
    fun `init publishes idle state with empty fields`() {
        val state = viewModel.uiState.value
        assertTrue(state is RegisterUiState.Idle)
        val idle = state as RegisterUiState.Idle
        assertTrue(idle.ruleState.email.isEmpty())
        assertTrue(idle.ruleState.password.isEmpty())
        assertFalse(idle.ruleState.enableButton)
    }

    // — InputEmail —

    @Test
    fun `onIntent InputEmail with valid email updates email and clears invalid flag`() = runTest {
        fakeEmailUseCase.result = true

        viewModel.onIntent(RegistrationIntent.InputEmail("valid@example.com"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.email == "valid@example.com")
        assertFalse(state.ruleState.invalidEmail)
    }

    @Test
    fun `onIntent InputEmail with invalid email sets invalid flag`() = runTest {
        fakeEmailUseCase.result = false

        viewModel.onIntent(RegistrationIntent.InputEmail("not-an-email"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.invalidEmail)
    }

    @Test
    fun `onIntent InputEmail with invalid email disables button`() = runTest {
        fakeEmailUseCase.result = false

        viewModel.onIntent(RegistrationIntent.InputEmail("bad"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertFalse(state.ruleState.enableButton)
    }

    // — InputPassword —

    @Test
    fun `onIntent InputPassword updates password in state`() = runTest {
        viewModel.onIntent(RegistrationIntent.InputPassword("NewPass1@"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.password == "NewPass1@")
    }

    @Test
    fun `onIntent InputPassword with all rules valid clears invalid pass flag`() = runTest {
        fakePasswordUseCase.result = PasswordErrorType.entries.map {
            PasswordError(isValid = true, type = it)
        }

        viewModel.onIntent(RegistrationIntent.InputPassword("ValidPass1@"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertFalse(state.ruleState.invalidPass)
        assertFalse(state.ruleState.invalidConfPass)
    }

    @Test
    fun `onIntent InputPassword with failing non-confirm rule sets invalid pass flag`() = runTest {
        fakePasswordUseCase.result = PasswordErrorType.entries.map { type ->
            PasswordError(isValid = type == PasswordErrorType.EQUALS_PASSWORD, type = type)
        }

        viewModel.onIntent(RegistrationIntent.InputPassword("weak"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.invalidPass)
    }

    @Test
    fun `onIntent InputPassword with failing confirm rule sets invalid conf pass flag`() = runTest {
        fakePasswordUseCase.result = PasswordErrorType.entries.map { type ->
            PasswordError(isValid = type != PasswordErrorType.EQUALS_PASSWORD, type = type)
        }

        viewModel.onIntent(RegistrationIntent.InputPassword("ValidPass1@"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.invalidConfPass)
    }

    // — ConfirmPassword —

    @Test
    fun `onIntent ConfirmPassword updates confirmPassword in state`() = runTest {
        viewModel.onIntent(RegistrationIntent.ConfirmPassword("ValidPass1@"))

        val state = viewModel.uiState.value as RegisterUiState.Idle
        assertTrue(state.ruleState.passwordConfirm == "ValidPass1@")
    }

    // — CreateUser —

    @Test
    fun `onIntent CreateUser on success sends finish event`() = runTest {
        fakeEmailUseCase.result = true
        viewModel.onIntent(RegistrationIntent.InputEmail("user@example.com"))

        fakePasswordUseCase.result = PasswordErrorType.entries.map {
            PasswordError(isValid = true, type = it)
        }
        viewModel.onIntent(RegistrationIntent.InputPassword("ValidPass1@"))
        viewModel.onIntent(RegistrationIntent.ConfirmPassword("ValidPass1@"))

        fakeCreateUserUseCase.result = flowOf(User(name = "User", email = "user@example.com"))
        viewModel.onIntent(RegistrationIntent.CreateUser)

        val event = viewModel.events.first()
        assertTrue(event is RegistrationEvent.Finish)
    }

    @Test
    fun `onIntent CreateUser on error sends error event`() = runTest {
        fakeEmailUseCase.result = true
        viewModel.onIntent(RegistrationIntent.InputEmail("user@example.com"))

        fakePasswordUseCase.result = PasswordErrorType.entries.map {
            PasswordError(isValid = true, type = it)
        }
        viewModel.onIntent(RegistrationIntent.InputPassword("ValidPass1@"))

        fakeCreateUserUseCase.result = kotlinx.coroutines.flow.flow {
            throw RuntimeException("Server error")
        }
        viewModel.onIntent(RegistrationIntent.CreateUser)

        val event = viewModel.events.first()
        assertTrue(event is RegistrationEvent.Error)
    }

    @Test
    fun `onIntent CreateUser on error reverts to idle state`() = runTest {
        fakeEmailUseCase.result = true
        viewModel.onIntent(RegistrationIntent.InputEmail("user@example.com"))

        fakeCreateUserUseCase.result = kotlinx.coroutines.flow.flow {
            throw RuntimeException("Server error")
        }
        viewModel.onIntent(RegistrationIntent.CreateUser)

        assertTrue(viewModel.uiState.value is RegisterUiState.Idle)
    }
}
