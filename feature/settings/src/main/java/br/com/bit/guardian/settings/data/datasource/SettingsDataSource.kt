package br.com.bit.guardian.settings.data.datasource

import br.com.bit.guardian.settings.data.datasource.remote.response.SettingsResponse
import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    suspend fun fetchSettings(): Flow<SettingsResponse>
    suspend fun saveAvatar(avatarId: Int): Flow<Unit>
    suspend fun saveNickName(nickname: String): Flow<Unit>
    suspend fun signOut(): Flow<Unit>
}