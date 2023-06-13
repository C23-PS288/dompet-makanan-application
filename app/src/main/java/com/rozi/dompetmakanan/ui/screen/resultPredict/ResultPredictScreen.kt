package com.rozi.dompetmakanan.ui.screen.resultPredict

import android.app.Application
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rozi.dompetmakanan.ui.components.ProgressBarLoading
import com.rozi.dompetmakanan.utils.UiState
import com.rozi.dompetmakanan.utils.ViewModelFactory
import com.rozi.dompetmakanan.utils.uriToFile

@Composable
fun ResultPredictScreen(
    application: Application,
    uri: String,
    viewModel: ResultPredictViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState ->
        when (uiState){
            is UiState.Loading -> {
                val imageUri= Uri.decode(uri)
                val image = Uri.parse(imageUri)
                val file = uriToFile(image, application)
                viewModel.postPredict(file)
                Loading()
            }
            is UiState.Success -> {
                ResultPredictContent(nama = uiState.data)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun Loading(){
    ProgressBarLoading(isLoading = true)
}

@Composable
fun ResultPredictContent(nama: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = nama)
    }
}