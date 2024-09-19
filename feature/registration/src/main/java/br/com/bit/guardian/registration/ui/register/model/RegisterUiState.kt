package br.com.bit.guardian.registration.ui.register.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import br.com.bit.guardian.feature.registration.R

sealed class RegisterUiState(val ruleState: RegistrationRuleState) {
    @Parcelize
    data class Idle(val state: RegistrationRuleState) : RegisterUiState(state),Parcelable
    @Parcelize
    data class Success(val state: RegistrationRuleState) : RegisterUiState(state),Parcelable
    @Parcelize
    data class Loading(val state: RegistrationRuleState) : RegisterUiState(state),Parcelable
}

@Parcelize
data class RegistrationRuleState(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val invalidEmail: Boolean,
    val invalidPass: Boolean,
    val invalidConfPass: Boolean,
    val enableButton: Boolean,
    val listOfPassError: List<PasswordRuleItem>
):Parcelable {

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

@Parcelize
data class PasswordRuleItem(@StringRes val textRes: Int, val isSuccess: Boolean):Parcelable





