package br.com.bit.guardian.registration.domain.usecase

import br.com.bit.guardian.registration.data.repository.LoginRepository
import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.domain.mappers.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : CreateUserUseCase {
    override fun invoke(email: String, password: String): Flow<User> {
        return repository.createUser(email, password).map {
            it.toUser()
        }
    }
}
