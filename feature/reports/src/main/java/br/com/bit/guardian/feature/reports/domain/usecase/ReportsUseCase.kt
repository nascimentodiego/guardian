package br.com.bit.guardian.feature.reports.domain.usecase

import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import kotlinx.coroutines.flow.Flow

interface ReportsUseCase {
    operator fun invoke(): Flow<List<ReportEntity>>
}