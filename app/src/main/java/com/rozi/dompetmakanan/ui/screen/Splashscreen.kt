package com.rozi.dompetmakanan.ui.screen

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.ui.navigation.Destination
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(true) {
        delay(2000) // Wait for 2 seconds
        navController.popBackStack()
        navController.navigate(Destination.Login.route)
    }

    val context = LocalContext.current
    AndroidView(factory = {
        val view = View(context)
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        view
    })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(width = 79.dp, height = 99.dp)
            )
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SplashscreenPreview() {
    DompetMakananTheme {
    }
}