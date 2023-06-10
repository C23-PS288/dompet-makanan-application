package com.rozi.dompetmakanan.ui.screen.home

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.utils.UiState
import com.rozi.dompetmakanan.utils.ViewModelFactory


@Composable
fun HomeScreen(
    application: Application,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application = application)
    ),
    onClickLogOut: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getUserById()
            }
            is UiState.Success -> {
                val data = uiState.data
                HomeContent(
                    onClickLogOut = onClickLogOut,
                    nama = data.name?:"Kosong"
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(nama: String, onClickLogOut: () -> Unit) {
    val listColorBackground = listOf(
        Color(238, 113, 0, 255),
        Color(101, 0, 126, 255),
        Color(0, 47, 187),
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listColorBackground
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Icon Home",
            modifier = Modifier
                .size(400.dp)
                .clickable {
                    onClickLogOut()
                }
        )
        Text(text = nama)
    }
}