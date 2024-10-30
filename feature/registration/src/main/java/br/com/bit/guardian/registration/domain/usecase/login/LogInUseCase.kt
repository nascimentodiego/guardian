package br.com.bit.guardian.registration.domain.usecase.login

import br.com.bit.guardian.registration.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface LogInUseCase {
    operator fun invoke(email: String, password: String): Flow<User>
}