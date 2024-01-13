package br.com.bit.guardian.core.domain.usecase

import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import kotlinx.coroutines.flow.Flow

interface GetReportsUseCase {
    operator fun invoke(): Flow<List<DeviceReportItem>>
}