package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.ui.screen.home.ItemBotNavBar

@Composable
fun BottomBar(
    selectedRoute:String,
    onItemSelected:(ItemBotNavBar)->Unit
) {
//    val selectedRoute=ItemBotNavBar.Home.route

    val items= listOf(
        ItemBotNavBar.Home,
        ItemBotNavBar.Favorite,
        ItemBotNavBar.Camera
    )

    val navShape= RoundedCornerShape(30.dp)

    Row(
        modifier = Modifier
            .shadow(10.dp)
            .fillMaxWidth()
            .clip(navShape)
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {


        items.forEach {

            val isSelected=it.route==selectedRoute

            IconButton(onClick = {
                if(!isSelected)
                    onItemSelected(it)
            }) {
                Image(painter = painterResource(it.image), contentDescription = it.title)
            }

        }


    }
}