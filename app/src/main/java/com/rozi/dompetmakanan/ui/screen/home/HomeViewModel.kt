package com.rozi.dompetmakanan.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.repository.FoodRepository
import com.rozi.dompetmakanan.data.repository.UserRepository
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.model.User
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FoodRepository) : ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
//    val uiState : StateFlow<UiState<User>> get() = _uiState
//
//
//    fun getUserById(){
//        viewModelScope.launch {
//            _uiState.value = UiState.Loading
//            _uiState.value = UiState.Success(repository.getUserWithId())
//        }
//    }

    private val _uiState : MutableStateFlow<UiState<List<Food>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Food>>> get() = _uiState

    fun getAllFoods(){
        viewModelScope.launch {
            repository.getAllFoods()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ foods ->
                    _uiState.value = UiState.Success(foods)
                }
        }
    }
}