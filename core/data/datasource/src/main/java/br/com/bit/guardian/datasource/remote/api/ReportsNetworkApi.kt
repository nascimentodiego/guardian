package br.com.bit.guardian.datasource.remote.api

import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportsNetworkApi {
    @GET(value = "report")
    suspend fun getReports(
        @Query("device_type") deviceTypes: List<String>? = null
    ): List<ReportItemResponse>
}