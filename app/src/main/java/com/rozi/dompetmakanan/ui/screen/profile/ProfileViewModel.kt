package com.rozi.dompetmakanan.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.repository.UserRepository
import com.rozi.dompetmakanan.model.User
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<User>> get() = _uiState

    fun getUser(){
        viewModelScope.launch {
            repository.getUserWithId()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{user ->
                    _uiState.value = UiState.Success(user)
                }
        }
    }
}