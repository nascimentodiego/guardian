package br.com.bit.guardian.feature.reports.ui.widget.model

import kotlinx.serialization.Serializable

@Serializable
sealed class ReportsUiState {
    @Serializable
    data object Error : ReportsUiState()

    @Serializable
    data class Success(val data: List<ActivityLog>) : ReportsUiState()

    @Serializable
    data object Loading : ReportsUiState()
}
