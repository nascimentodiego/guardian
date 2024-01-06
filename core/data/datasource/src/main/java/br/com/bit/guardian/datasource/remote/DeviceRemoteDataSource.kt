package br.com.bit.guardian.datasource.remote

import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse

interface DeviceRemoteDataSource {
    suspend fun getReports(deviceTypes: List<String>? = null): List<ReportItemResponse>
}