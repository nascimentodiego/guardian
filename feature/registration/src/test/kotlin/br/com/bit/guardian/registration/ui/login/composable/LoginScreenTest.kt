package br.com.bit.guardian.registration.ui.login.composable

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import br.com.bit.guardian.core.test.ParameterizedComposeScreenTest
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.login.model.UserView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [34])
class LoginScreenTest(qualifier: String) : ParameterizedComposeScreenTest(qualifier) {

    private fun setContent(
        uiState: LoginUiState? = LoginUiState.Idle(UserView.Empty),
        intent: (LoginIntent) -> Unit = {},
        onCreateUserClick: () -> Unit = {}
    ) {
        val listener = object : LoginListener {
            override fun onCreateUserClickListener() = onCreateUserClick()
        }
        setScreenContent {
            LoginScreen(uiState = uiState, intent = intent, callbacks = listener)
        }
    }

    // — Idle state —

    @Test
    fun `LoginScreen displays email field when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("E-mail").assertExists()
    }

    @Test
    fun `LoginScreen displays password field when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Senha").assertExists()
    }

    @Test
    fun `LoginScreen displays login button when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Entrar").assertExists()
    }

    @Test
    fun `LoginScreen displays create account button when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Criar conta").assertExists()
    }

    @Test
    fun `LoginScreen login button is disabled when isButtonEnabled is false`() {
        setContent(uiState = LoginUiState.Idle(UserView.Empty.copy(isButtonEnabled = false)))
        composeRule.onNodeWithText("Entrar").assertIsNotEnabled()
    }

    @Test
    fun `LoginScreen login button is enabled when isButtonEnabled is true`() {
        setContent(uiState = LoginUiState.Idle(UserView.Empty.copy(isButtonEnabled = true)))
        composeRule.onNodeWithText("Entrar").assertIsEnabled()
    }

    // — Interactions —

    @Test
    fun `LoginScreen email input triggers InputEmail intent`() {
        var captured: LoginIntent? = null
        setContent(intent = { captured = it })
        composeRule.onNodeWithText("E-mail").performTextInput("user@example.com")
        assertEquals(LoginIntent.InputEmail("user@example.com"), captured)
    }

    @Test
    fun `LoginScreen login button click triggers Login intent`() {
        var captured: LoginIntent? = null
        setContent(
            uiState = LoginUiState.Idle(UserView.Empty.copy(isButtonEnabled = true)),
            intent = { captured = it }
        )
        composeRule.onNodeWithText("Entrar").performClick()
        assertEquals(LoginIntent.Login, captured)
    }

    @Test
    fun `LoginScreen create account button click triggers callback`() {
        var clicked = false
        setContent(onCreateUserClick = { clicked = true })
        composeRule.onNodeWithText("Criar conta").performClick()
        assertTrue(clicked)
    }

    // — Loading state —

    @Test
    fun `LoginScreen shows app name when state is Loading`() {
        setContent(uiState = LoginUiState.Loading)
        composeRule.onNodeWithText("Guardian").assertExists()
    }

    // — Null state —

    @Test
    fun `LoginScreen does not display form when state is null`() {
        setContent(uiState = null)
        composeRule.onNodeWithText("E-mail").assertDoesNotExist()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun qualifiers(): Collection<Array<Any>> = defaultQualifiers()
    }
}
