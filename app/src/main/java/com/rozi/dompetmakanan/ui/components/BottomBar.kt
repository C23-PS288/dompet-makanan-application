package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.ui.navigation.Destination
import com.rozi.dompetmakanan.ui.screen.home.ItemBotNavBar

@Composable
fun BottomBar(
) {

    val NavigationItems= listOf(
        ItemBotNavBar(
            screen = Destination.Home,
            title = "Home",
            image = R.drawable.home
        ),
        ItemBotNavBar(
            screen = Destination.Camera,
            title = "Camera",
            image = R.drawable.camera
        ),
        ItemBotNavBar(
            screen = Destination.Profile,
            title = "Profile",
            image = R.drawable.iconamoon_profile
        )
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
        NavigationItems.map{item->
            Box(
                modifier = Modifier
                    .clickable {  }
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(item.image),
                    contentDescription = item.title,
                )
            }
        }
    }
}