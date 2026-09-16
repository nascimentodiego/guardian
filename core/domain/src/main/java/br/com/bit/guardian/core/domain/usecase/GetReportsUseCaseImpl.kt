package br.com.bit.guardian.core.domain.usecase

import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import br.com.bit.guardian.core.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReportsUseCaseImpl @Inject constructor(
    private val repository: DeviceRepository
) : GetReportsUseCase {
    override fun invoke(): Flow<List<DeviceReportItem>> = repository.getReports()
}