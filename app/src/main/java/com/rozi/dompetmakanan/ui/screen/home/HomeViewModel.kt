package com.rozi.dompetmakanan.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.repository.UserRepository
import com.rozi.dompetmakanan.model.User
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<User>> get() = _uiState

    fun getUserById(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getUserWithId())
        }
    }
}