package br.com.bit.guardian.feature.reports.util.fakes

import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import br.com.bit.guardian.feature.reports.domain.usecase.ReportsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeReportsUseCase : ReportsUseCase {

    var result: Flow<List<ReportEntity>> = flowOf(emptyList())

    override fun invoke(): Flow<List<ReportEntity>> = result
}
