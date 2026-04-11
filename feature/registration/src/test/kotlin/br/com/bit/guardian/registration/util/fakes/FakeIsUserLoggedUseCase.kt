package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.domain.usecase.login.IsUserLoggedUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeIsUserLoggedUseCase : IsUserLoggedUseCase {
    var result: Flow<Boolean> = flowOf(false)

    override fun invoke(): Flow<Boolean> = result
}
