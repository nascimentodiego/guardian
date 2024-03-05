package br.com.bit.guardian.login.domain.usecase

import br.com.bit.guardian.login.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface CreateUserUseCase {
    operator fun invoke(name:String,email:String): Flow<User>
}