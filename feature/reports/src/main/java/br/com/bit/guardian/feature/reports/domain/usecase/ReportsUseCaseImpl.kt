package br.com.bit.guardian.feature.reports.domain.usecase

import br.com.bit.guardian.feature.reports.data.repository.ReportsRepository
import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import br.com.bit.guardian.feature.reports.domain.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportsUseCaseImpl @Inject constructor(
    private val repository: ReportsRepository
) : ReportsUseCase {
    override fun invoke(): Flow<List<ReportEntity>> {
        return repository.fetchReports().map { response ->
            response.map { it.toEntity() }
        }
    }
}