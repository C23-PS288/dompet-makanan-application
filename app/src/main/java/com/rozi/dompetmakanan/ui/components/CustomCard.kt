package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.ui.screen.home.Menu
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    menu: Menu,
){
    Card(
        modifier = modifier.width(140.dp).padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column {
            Image(
                painter = painterResource(menu.image),
                contentDescription = "Menu Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menu.Title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = menu.descripton,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun CustomCardPreview () {
    DompetMakananTheme {
        CustomCard(
            menu = Menu(R.drawable.banner, "Makanan 1", "Deskripsi", "Rp. 90.000")
        )
    }
}