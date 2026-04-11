package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.domain.usecase.login.CheckLogInInputValidUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCheckLogInInputValidUseCase : CheckLogInInputValidUseCase {
    var result: Boolean = false

    override fun invoke(email: String, password: String): Flow<Boolean> = flowOf(result)
}
