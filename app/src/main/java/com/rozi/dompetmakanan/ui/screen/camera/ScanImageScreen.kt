package com.rozi.dompetmakanan.ui.screen.camera

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rozi.dompetmakanan.ui.components.TopBar
import com.rozi.dompetmakanan.utils.ComposeFileProvider
import java.net.URI
import java.net.URLEncoder

@Composable
fun ScanImageScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onSuccess: (String) -> Unit
) {
    var hasImage by remember {
        mutableStateOf(false)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )

    if(hasImage && imageUri != null){
        val uri = Uri.encode(imageUri.toString())
        LaunchedEffect(key1 = Unit){
            onSuccess(uri!!)
        }
    }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                imagePicker.launch("image/*")
            }) {
                Text(text = "Select Image")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    val uri = ComposeFileProvider.getImageUri(context = context)
                    imageUri = uri
                    cameraLauncher.launch(uri)
                },
            ) {
                Text(
                    text = "Take photo"
                )
            }
        }
    }
}