package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rozi.dompetmakanan.R

@Composable
fun BannerAuth(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF333333), Color.Black),
                    startX = 0f,
                    endX = 1200f
                )
            )
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.offset(y = (-20).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(width = 42.dp, height = 52.dp)
                    .padding(end = 8.dp),
                painter = painterResource(id = R.drawable.logo_black_white),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = Color.White
                )
            )
        }
    }
}