package br.com.bit.guardian.core.data.repository.shared.device

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.core.data.repository.mappers.toReportItemModel
import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse
import br.com.bit.guardian.datasource.remote.shared.device.DeviceRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val dataSource: DeviceRemoteDataSource
) : DeviceRepository {
    override fun getReports(): Flow<List<ReportItemData>> = flow {
        val response = dataSource.getReports().map(ReportItemResponse::toReportItemModel)
        emit(response)
    }.flowOn(Dispatchers.IO).handleNetworkError()
}