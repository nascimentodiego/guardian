package br.com.bit.guardian.registration.ui.register.mappers

import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import br.com.bit.guardian.registration.ui.register.model.PasswordRuleItem

fun PasswordError.toUiPasswordError() = when (type) {
    PasswordErrorType.MIN_MAX_CHARACTER -> {
        PasswordRuleItem(R.string.login_register_rule_min_character, isValid)
    }

    PasswordErrorType.CAPITAL_LETTER -> {
        PasswordRuleItem(R.string.login_register_rule_min_one_capital_letter, isValid)
    }

    PasswordErrorType.NUMBER_CHARACTER -> {
        PasswordRuleItem(R.string.login_register_rule_min_one_number, isValid)
    }

    PasswordErrorType.SPECIAL_CHARACTER -> {
        PasswordRuleItem(R.string.login_register_rule_special_character, isValid)
    }

    PasswordErrorType.EQUALS_PASSWORD -> {
        PasswordRuleItem(R.string.login_register_rule_confirm_pass, isValid)
    }

    PasswordErrorType.DIFFERENT_FROM_EMAIL -> {
        PasswordRuleItem(R.string.login_register_rule_different_from_email, isValid)
    }
}