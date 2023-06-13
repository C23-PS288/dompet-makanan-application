package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.ui.screen.home.Menu
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

@Composable
fun CustomCardList(
    modifier: Modifier = Modifier,
    menu: Food
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AsyncImage(
                model = menu.menuPic,
                contentDescription = menu.menu,
                modifier = modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .width(120.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 5.dp, top = 5.dp)
            ) {
                Text(text = menu.menu ?: "")
                Text(
                    text = menu.namaWarung ?: "",
                    modifier = modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomCardListPreview() {
    DompetMakananTheme {
    }
}