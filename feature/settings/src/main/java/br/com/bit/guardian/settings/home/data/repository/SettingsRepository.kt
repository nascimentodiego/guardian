package br.com.bit.guardian.settings.home.data.repository

import br.com.bit.guardian.settings.home.data.repository.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun fetchSettings(): Flow<Settings>
    fun saveAvatar(avatarId: Int): Flow<Unit>
    fun saveNickName(nickname: String): Flow<Unit>
    fun signOut(): Flow<Unit>
}