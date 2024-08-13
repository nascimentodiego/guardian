package br.com.bit.guardian.registration.ui.register.model

import androidx.annotation.StringRes
import br.com.bit.guardian.feature.registration.R

sealed class RegisterUiState(val ruleState: RegistrationRuleState) {
    data class Idle(val state: RegistrationRuleState) : RegisterUiState(state)
    data class Success(val state: RegistrationRuleState) : RegisterUiState(state)
    data class Error(val state: RegistrationRuleState) : RegisterUiState(state)
    data class Loading(val state: RegistrationRuleState) : RegisterUiState(state)
}

data class RegistrationRuleState(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val invalidEmail: Boolean,
    val invalidPass: Boolean,
    val invalidConfPass: Boolean,
    val enableButton: Boolean,
    val listOfPassError: List<PasswordRuleItem>
) {

    fun mustEnableButton() = copy(
        enableButton = !invalidEmail
                && !invalidPass
                && !invalidConfPass
                && listOfPassError.firstOrNull { !it.isSuccess }
            ?.let { false } ?: run { true }
    )


    companion object {
        val Empty = RegistrationRuleState(
            email = "",
            password = "",
            passwordConfirm = "",
            enableButton = false,
            invalidEmail = false,
            invalidPass = false,
            invalidConfPass = false,
            listOfPassError = listOf(
                PasswordRuleItem(R.string.login_register_rule_different_from_email, false),
                PasswordRuleItem(R.string.login_register_rule_min_character, false),
                PasswordRuleItem(R.string.login_register_rule_min_one_capital_letter, false),
                PasswordRuleItem(R.string.login_register_rule_min_one_number, false),
                PasswordRuleItem(R.string.login_register_rule_special_character, false),
                PasswordRuleItem(R.string.login_register_rule_confirm_pass, false)
            )
        )
    }
}

data class PasswordRuleItem(@StringRes val textRes: Int, val isSuccess: Boolean)







