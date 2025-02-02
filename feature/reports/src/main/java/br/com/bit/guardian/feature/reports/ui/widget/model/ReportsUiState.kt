package br.com.bit.guardian.feature.reports.ui.widget.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ReportsUiState() {
    @Parcelize
    data object Error : ReportsUiState(), Parcelable

    @Parcelize
    data class Success(val data: List<ActivityLog>) : ReportsUiState(), Parcelable

    @Parcelize
    data object Loading : ReportsUiState(), Parcelable
}