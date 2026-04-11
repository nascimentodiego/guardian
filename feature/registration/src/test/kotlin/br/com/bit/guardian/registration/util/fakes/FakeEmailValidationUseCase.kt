package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.domain.usecase.register.EmailValidationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeEmailValidationUseCase : EmailValidationUseCase {
    var result: Boolean = true

    override fun invoke(email: String): Flow<Boolean> = flowOf(result)
}
