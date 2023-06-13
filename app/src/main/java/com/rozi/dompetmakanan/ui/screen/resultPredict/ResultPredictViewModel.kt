package com.rozi.dompetmakanan.ui.screen.resultPredict

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.repository.PredictRepository
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class ResultPredictViewModel(private val repository: PredictRepository) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<Food>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Food>>> get() = _uiState

    fun postPredict(file : File){
        viewModelScope.launch {
            repository.predictImage(file)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString()) }

                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}