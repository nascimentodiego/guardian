package br.com.bit.guardian.registration.domain.usecase

import br.com.bit.guardian.registration.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface CreateUserUseCase {
    operator fun invoke(email: String, password: String): Flow<User>
}