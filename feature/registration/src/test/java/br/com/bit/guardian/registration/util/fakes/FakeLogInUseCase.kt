package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.domain.usecase.login.LogInUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLogInUseCase : LogInUseCase {
    var result: Flow<User> = flowOf(User(name = "Test User", email = "test@example.com"))

    override fun invoke(email: String, password: String): Flow<User> = result
}
