package br.com.bit.guardian.registration.ui.register.composable

import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import br.com.bit.guardian.core.test.ParameterizedComposeScreenTest
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationIntent
import br.com.bit.guardian.registration.ui.register.model.RegistrationRuleState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [34])
class RegisterScreenTest(qualifier: String) : ParameterizedComposeScreenTest(qualifier) {

    private fun setContent(
        uiState: RegisterUiState? = RegisterUiState.Idle(RegistrationRuleState.Empty),
        intent: (RegistrationIntent) -> Unit = {},
        onBackPressClick: () -> Unit = {}
    ) {
        setScreenContent {
            RegisterScreen(
                uiState = uiState,
                intent = intent,
                onBackPressClickListener = onBackPressClick
            )
        }
    }

    // — Idle state —

    @Test
    fun `RegisterScreen displays email field when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("E-mail").assertExists()
    }

    @Test
    fun `RegisterScreen displays password field when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Senha").assertExists()
    }

    @Test
    fun `RegisterScreen displays confirm password field when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Confirmar senha").assertExists()
    }

    @Test
    fun `RegisterScreen displays title when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Criar nova conta").assertExists()
    }

    @Test
    fun `RegisterScreen displays password rules section when state is Idle`() {
        setContent()
        composeRule.onNodeWithText("Regras para uma senha forte:").assertExists()
    }

    // — Interactions —

    @Test
    fun `RegisterScreen email input triggers InputEmail intent`() {
        var captured: RegistrationIntent? = null
        setContent(intent = { captured = it })
        composeRule.onNodeWithText("E-mail").performTextInput("user@example.com")
        assertEquals(RegistrationIntent.InputEmail("user@example.com"), captured)
    }

    @Test
    fun `RegisterScreen password input triggers InputPassword intent`() {
        var captured: RegistrationIntent? = null
        setContent(intent = { captured = it })
        composeRule.onNodeWithText("Senha").performTextInput("Password1!")
        assertEquals(RegistrationIntent.InputPassword("Password1!"), captured)
    }

    @Test
    fun `RegisterScreen confirm password input triggers ConfirmPassword intent`() {
        var captured: RegistrationIntent? = null
        setContent(intent = { captured = it })
        composeRule.onNodeWithText("Confirmar senha").performTextInput("Password1!")
        assertEquals(RegistrationIntent.ConfirmPassword("Password1!"), captured)
    }

    @Test
    fun `RegisterScreen back button click triggers callback`() {
        var clicked = false
        setContent(onBackPressClick = { clicked = true })
        composeRule.onNodeWithContentDescription("Localized description").performClick()
        assertTrue(clicked)
    }

    // — Error states —

    @Test
    fun `RegisterScreen shows invalid email error when invalidEmail is true`() {
        setContent(uiState = RegisterUiState.Idle(RegistrationRuleState.Empty.copy(invalidEmail = true)))
        composeRule.onNodeWithText("E-mail inválido").assertExists()
    }

    @Test
    fun `RegisterScreen shows invalid password error when invalidPass is true`() {
        setContent(uiState = RegisterUiState.Idle(RegistrationRuleState.Empty.copy(invalidPass = true)))
        composeRule.onNodeWithText("Senha inválida!").assertExists()
    }

    // — Loading state —

    @Test
    fun `RegisterScreen does not display form when state is Loading`() {
        setContent(uiState = RegisterUiState.Loading(RegistrationRuleState.Empty))
        composeRule.onNodeWithText("E-mail").assertDoesNotExist()
    }

    // — Null state —

    @Test
    fun `RegisterScreen does not display form when state is null`() {
        setContent(uiState = null)
        composeRule.onNodeWithText("E-mail").assertDoesNotExist()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun qualifiers(): Collection<Array<Any>> = defaultQualifiers()
    }
}
