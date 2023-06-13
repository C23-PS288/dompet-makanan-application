package com.rozi.dompetmakanan.ui.screen.camera

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.utils.ComposeFileProvider

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
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(10.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Ambil Gambar / Pilih Gambar dari Penyimpanan",
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
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
}