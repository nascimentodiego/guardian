package br.com.bit.guardian.app.model

sealed interface ReportsUiState {
    data class Success(val reports: List<ReportState>) : ReportsUiState
    data object Error : ReportsUiState
    data object Loading : ReportsUiState
}
