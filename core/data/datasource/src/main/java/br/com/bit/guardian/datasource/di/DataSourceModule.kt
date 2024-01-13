package br.com.bit.guardian.datasource.di

import br.com.bit.guardian.datasource.remote.shared.device.DeviceRemoteDataSource
import br.com.bit.guardian.datasource.remote.shared.device.DeviceRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsDeviceRemoteDataSource(
        dataSourceImpl: DeviceRemoteDataSourceImpl
    ): DeviceRemoteDataSource
}


