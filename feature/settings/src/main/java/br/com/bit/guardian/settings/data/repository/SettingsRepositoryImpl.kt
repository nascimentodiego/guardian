package br.com.bit.guardian.settings.data.repository

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.settings.data.datasource.SettingsDataSource
import br.com.bit.guardian.settings.data.repository.mappers.toSettings
import br.com.bit.guardian.settings.data.repository.model.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: SettingsDataSource
) : SettingsRepository {
    override fun fetchSettings(): Flow<Settings> = flow {
        dataSource.fetchSettings().collect { response ->
            emit(response.toSettings())
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun saveAvatar(avatarId: Int): Flow<Unit> = flow {
        dataSource.saveAvatar(avatarId).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun saveNickName(nickname: String): Flow<Unit> = flow {
        dataSource.saveNickName(nickname).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun signOut(): Flow<Unit> = flow {
        dataSource.signOut().collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()
}
