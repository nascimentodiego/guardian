package br.com.bit.guardian.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.login.domain.usecase.CreateUserUseCase
import br.com.bit.guardian.login.ui.model.UserLoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: CreateUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserLoginUiState?>(null)
    val uiState: StateFlow<UserLoginUiState?> = _uiState.asStateFlow()

    fun createNewUser(email:String,password:String) {
        viewModelScope.launch {
            useCase(email,password)
                .asResult()
                .map { result ->
                    _uiState.emit(
                        when (result) {
                            is Result.Success -> {
                                UserLoginUiState.Success(result.data)
                            }
                            is Result.Loading -> UserLoginUiState.Loading
                            is Result.Error -> UserLoginUiState.Error
                        }
                    )
                }.collect()
        }
    }
}
