package br.com.bit.guardian.login.domain.usecase

import br.com.bit.guardian.login.data.repository.LoginRepository
import br.com.bit.guardian.login.domain.entities.User
import br.com.bit.guardian.login.domain.mappers.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : CreateUserUseCase {
    override fun invoke(name: String, email: String): Flow<User> {
        return repository.createUser(name, email).map {
            it.toUser()
        }
    }
}
