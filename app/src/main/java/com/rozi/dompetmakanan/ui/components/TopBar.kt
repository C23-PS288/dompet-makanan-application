package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R
@Composable
fun TopBar(
    title: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.mingcute_left),
            contentDescription = "Back Button",
        )
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    TopBar(title = "Pindai Gambar")
}