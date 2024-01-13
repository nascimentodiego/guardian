package br.com.bit.guardian.datasource.remote.shared.device

import br.com.bit.guardian.datasource.remote.api.ReportsNetworkApi
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse
import javax.inject.Inject

class DeviceRemoteDataSourceImpl @Inject constructor(
    private val api: ReportsNetworkApi
) : DeviceRemoteDataSource {
    override suspend fun getReports(deviceTypes: List<String>?): List<ReportItemResponse> {
        return api.getReports(deviceTypes)
    }
}