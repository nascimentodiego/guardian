package br.com.bit.guardian.datasource.remote

import br.com.bit.guardian.datasource.remote.api.ReportsNetworkApi
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse

class DeviceRemoteDataSourceImpl(private val api:ReportsNetworkApi): DeviceRemoteDataSource {
    override suspend fun getReports(deviceTypes: List<String>?): List<ReportItemResponse> {
        return api.getReports(deviceTypes)
    }
}