package com.rozi.dompetmakanan.ui.screen.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rozi.dompetmakanan.ui.components.TopBar

@Preview
@Composable
fun ScanImageScreen () {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .clickable { }
        ){

        }
    }
}