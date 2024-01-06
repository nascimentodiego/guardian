package br.com.bit.guardian.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.app.model.ReportState
import br.com.bit.guardian.core.network.retrofit.GuardianNetwork
import br.com.bit.guardian.datasource.remote.DeviceRemoteDataSourceImpl
import br.com.bit.guardian.datasource.remote.api.ReportsNetworkApi
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val api = GuardianNetwork().retrofit.create(ReportsNetworkApi::class.java)
    private val datasource = DeviceRemoteDataSourceImpl(api)

    private val _uiState = MutableStateFlow<ReportState?>(null)
    val uiState: StateFlow<ReportState?> = _uiState.asStateFlow()


    fun getReport(){
        viewModelScope.launch {
            val response = datasource.getReports().first()



            _uiState.emit(ReportState(response))
        }
    }

}