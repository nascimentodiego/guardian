package br.com.bit.guardian.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.app.model.ReportState
import br.com.bit.guardian.app.model.ReportsUiState
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.domain.usecase.GetReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetReportsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ReportsUiState?>(null)
    val uiState: StateFlow<ReportsUiState?> = _uiState.asStateFlow()

    fun getReport() {
        viewModelScope.launch {
            useCase()
                .asResult()
                .map { result ->
                    _uiState.emit(
                        when (result) {
                            is Result.Success -> {
                                val list = result.data.map { ReportState(it) }
                                ReportsUiState.Success(list)
                            }

                            is Result.Loading -> ReportsUiState.Loading
                            is Result.Error -> ReportsUiState.Error
                        }
                    )
                }.collect()
        }
    }
}
