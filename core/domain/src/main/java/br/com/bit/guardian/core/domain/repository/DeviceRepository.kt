package br.com.bit.guardian.core.domain.repository

import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getReports(): Flow<List<DeviceReportItem>>
}
