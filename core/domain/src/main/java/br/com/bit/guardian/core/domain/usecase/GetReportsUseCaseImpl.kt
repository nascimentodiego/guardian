package br.com.bit.guardian.core.domain.usecase

import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import br.com.bit.guardian.core.data.repository.shared.device.DeviceRepository
import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import br.com.bit.guardian.core.domain.mappers.toReportItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetReportsUseCaseImpl @Inject constructor(
    private val repository: DeviceRepository
) : GetReportsUseCase {
    override fun invoke(): Flow<List<DeviceReportItem>> {
        return repository.getReports().map {
            it.map(ReportItemData::toReportItem)
        }
    }
}