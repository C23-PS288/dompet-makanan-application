package com.rozi.dompetmakanan.ui.screen.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.ui.navigation.Destination

@Composable
fun ProfileScreen(navController: NavController){
    val preferences = TokenPreferences(LocalContext.current)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(248,248,248))
            .verticalScroll(rememberScrollState())
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                )
                .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
            elevation = 20.dp
        ) { Box(modifier = Modifier.background(Color.Black))}
        Column(modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(140.dp)
                        .height(140.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(100))
                        .clip(shape = RoundedCornerShape(100)),
                    elevation = 20.dp
                ) {
                    Image(
                        painter = painterResource(R.drawable.profilepict),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Annie",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 30.sp,
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(R.drawable.fluent_location),
                    contentDescription = "Locatoin Symbol",
                    contentScale = ContentScale.Fit)
                Text(
                    text = "Tuban, Jawa Timur",
                    modifier = Modifier.padding(start = 3.dp),
                    style = TextStyle(Color(157,157,157))
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "randomemail@gmail.com",
                    modifier = Modifier.padding(start = 3.dp),
                    style = TextStyle(Color(157,157,157))
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        preferences.setToken("")
                        navController.popBackStack()
                        navController.navigate(route = Destination.Login.route) {
                            popUpTo(Destination.Login.route) {
                                inclusive = true
                            }
                        }
                               },
                elevation = 100.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.solar_logout),
                        contentDescription = "logout icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(20.dp)
                    )
                    Text(
                        text = "Log Out",
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
    }
}