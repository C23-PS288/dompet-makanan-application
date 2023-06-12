package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    menu: Food,
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .padding(5.dp)
            .clickable {  },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column {
            SubcomposeAsyncImage(
                model = menu.menuPic,
                contentDescription = "Menu Image",
                loading = {CircularProgressIndicator()},
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menu.menu?:"",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = menu.namaWarung?:"",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomCardPreview() {
    DompetMakananTheme {
//        CustomCard(
////            menu = Food()
//        )
    }
}