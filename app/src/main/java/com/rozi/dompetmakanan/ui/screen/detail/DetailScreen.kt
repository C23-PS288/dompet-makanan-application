package com.rozi.dompetmakanan.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.rozi.dompetmakanan.ui.components.TopBar

@Preview
@Composable
fun DetailScreen(){
    Box(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TopBar(title = "Detail")
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Food Image")
            Box(
                modifier = Modifier
                    .offset(y = (-25).dp)
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .padding(top = 10.dp)
                ) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Nama Makanan", textAlign = TextAlign.Left)
                        Text(
                            text = "Rp. 99.9999",
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}