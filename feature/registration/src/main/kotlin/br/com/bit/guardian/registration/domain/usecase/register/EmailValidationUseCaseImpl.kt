package br.com.bit.guardian.registration.domain.usecase.register

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmailValidationUseCaseImpl @Inject constructor() : EmailValidationUseCase {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    override fun invoke(email: String) = flow {
        emit(email.matches(emailRegex.toRegex()))
    }
}