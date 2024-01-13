package br.com.bit.guardian.core.data.repository.shared.device

import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import kotlinx.coroutines.flow.Flow

interface  DeviceRepository {
    fun getReports(): Flow<List<ReportItemData>>
}