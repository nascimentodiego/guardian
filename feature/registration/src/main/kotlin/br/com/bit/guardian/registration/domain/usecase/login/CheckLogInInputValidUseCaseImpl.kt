package br.com.bit.guardian.registration.domain.usecase.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckLogInInputValidUseCaseImpl @Inject constructor() : CheckLogInInputValidUseCase {
    override fun invoke(email: String, password: String): Flow<Boolean> = flow {
        emit(email.isNotEmpty() && password.isNotEmpty())
    }
}