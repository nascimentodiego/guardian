package br.com.bit.guardian.feature.reports.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.ui.viewmodel.Event
import br.com.bit.guardian.core.ui.viewmodel.ViewModel
import br.com.bit.guardian.feature.reports.domain.usecase.ReportsUseCase
import br.com.bit.guardian.feature.reports.ui.mappers.toActivityLog
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: ReportsUseCase
) : ViewModel<ReportsUiState, Event>(savedStateHandle) {
    init {
        if (!restoreState()) {
            publish(ReportsUiState.Loading)
        } else {
            fetchReports()
        }
    }

    private fun fetchReports() {
        uiState.withData { data ->
            viewModelScope.launch {
                useCase()
                    .asResult()
                    .map { result ->
                        when (result) {
                            is Result.Success -> {
                                val response = result.data.map { it.toActivityLog() }
                                publish(ReportsUiState.Success(response))
                            }

                            is Result.Loading -> {
                                publish(ReportsUiState.Loading)
                            }

                            is Result.Error -> {
                                publish(ReportsUiState.Error)
                            }
                        }
                    }.collect()
            }
        }
    }
}