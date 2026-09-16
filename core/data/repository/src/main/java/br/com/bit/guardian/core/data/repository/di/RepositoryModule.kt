package br.com.bit.guardian.core.data.repository.di

import br.com.bit.guardian.core.data.repository.shared.device.DeviceRepositoryImpl
import br.com.bit.guardian.core.domain.repository.DeviceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideDeviceRepository(
        deviceRepositoryImpl: DeviceRepositoryImpl
    ): DeviceRepository
}