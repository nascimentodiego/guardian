package br.com.bit.guardian.settings.home.data.datasource.di

import br.com.bit.guardian.settings.home.data.datasource.SettingsDataSource
import br.com.bit.guardian.settings.home.data.datasource.remote.firebase.SettingsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsDataSourceModule {
    @Binds
    abstract fun bindsSettingsDataSource(
        dataSourceImpl: SettingsDataSourceImpl
    ): SettingsDataSource
}
