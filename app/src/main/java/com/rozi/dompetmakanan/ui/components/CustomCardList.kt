package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

@Composable
fun CustomCardList(
    modifier: Modifier = Modifier,
    menu: Food
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(bottom = 5.dp).clickable {  },
        elevation = CardDefaults.elevatedCardElevation()
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
                    .width(100.dp),
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