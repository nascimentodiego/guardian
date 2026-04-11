package br.com.bit.guardian.app.di

import br.com.bit.guardian.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("AppVersion")
    fun provideAppVersion(): String {
        // Adicione um log para verificar o valor
        println("BuildConfig Version: ${BuildConfig.VERSION_NAME}")
        return BuildConfig.VERSION_NAME
    }
}