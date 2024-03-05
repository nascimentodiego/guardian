package br.com.bit.guardian.login.data.repository
import br.com.bit.guardian.login.data.repository.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun createUser(email:String,password:String): Flow<User>
}