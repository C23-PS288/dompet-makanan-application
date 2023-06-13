package com.rozi.dompetmakanan.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            .background(Color.White)
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
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Deskripsi")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, se d do eiusmod tempor incididunt ut labore et dolore magna a liqua. Ut enim ad minim veniam, quis nostrud exercitation ulla mco laboris nisi ut aliquip ex ea  commodo consequat.",
                            textAlign = TextAlign.Justify
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Customization(title = "Kustomisasi 1") {
                        LazyCustom(modifier = Modifier, customItem1)
                    }

                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Customization(title = "Kustomisasi 2") {
                        LazyCustom(modifier = Modifier, customItem2)
                    }
                }
            }
        }
    }
}

@Composable
fun Customization(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(text = title, modifier)
        content()
    }
}

@Composable
fun LazyCustom(
    modifier: Modifier = Modifier,
    list: List<ContentCustomize>
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ){
        items(list){ item ->
            itemCustom(item)
        }
    }
}

@Composable
fun itemCustom(customize: ContentCustomize){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ){
        Text(text = customize.customize, modifier = Modifier.padding(6.dp))
    }
}