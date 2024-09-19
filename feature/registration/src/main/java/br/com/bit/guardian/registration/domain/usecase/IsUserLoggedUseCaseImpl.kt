package br.com.bit.guardian.registration.domain.usecase

import br.com.bit.guardian.registration.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsUserLoggedUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : IsUserLoggedUseCase {
    override fun invoke(): Flow<Boolean> {
        return repository.isUserLogged()
    }
}