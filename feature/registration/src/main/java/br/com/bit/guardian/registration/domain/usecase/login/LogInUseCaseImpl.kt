package br.com.bit.guardian.registration.domain.usecase.login

import br.com.bit.guardian.registration.data.repository.LoginRepository
import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.domain.mappers.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LogInUseCase {
    override fun invoke(email: String, password: String): Flow<User> {
      return repository.signIn(email, password).map {
          it.toUser()
      }
    }
}