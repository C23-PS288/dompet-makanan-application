package com.rozi.dompetmakanan.ui.screen.home

import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.ui.components.CustomCard
import com.rozi.dompetmakanan.ui.components.CustomSearchBar
import com.rozi.dompetmakanan.ui.components.ProgressBarLoading
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme
import com.rozi.dompetmakanan.utils.ComposeFileProvider
import com.rozi.dompetmakanan.utils.UiState
import com.rozi.dompetmakanan.utils.ViewModelFactory
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    application: Application,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(application)
    ),
    onSuccess: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFoods()
                Loading()
            }
            is UiState.Success -> {
                HomeContent(application = application,foods = uiState.data, onSuccess = onSuccess)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun Loading(){
    ProgressBarLoading(isLoading = true)
}

@ExperimentalMaterialApi
@Composable
fun HomeContent(
    application: Application,
    foods: List<Food>,
    onSuccess: (String) -> Unit
) {
//    val preferences = TokenPreferences(LocalContext.current)

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

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

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(20.dp),
        sheetState = sheetState,
        sheetContent = {
        Box {
            Column(Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .clickable {
                            imagePicker.launch("image/*")
                        }
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.gallery),
                        contentDescription = null,
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text("Get From Gallery")
                }

                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .clickable {
                            val uri = ComposeFileProvider.getImageUri(context = application)
                            imageUri = uri
                            cameraLauncher.launch(uri)
                        }
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.symbols_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text("Take A Picture")
                }
                Spacer(modifier = Modifier.padding(bottom = 60.dp))
            }
        }
    }) {
        Scaffold{innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                            .height(240.dp),
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Text(
                                        text = "Lokasi",
                                        color = Color.Gray,
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                        )
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Tuban, Jawa Timur",
                                            color = Color.White,
                                            style = TextStyle(
                                                fontSize = 16.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Image(
                                            painter = painterResource(R.drawable.mingcute_down),
                                            contentDescription = "Drop Down",
                                            contentScale = ContentScale.FillHeight
                                        )
                                    }
                                }
                                Image(
                                    painter = painterResource(R.drawable.setting),
                                    contentDescription = "Profile Icon",
                                    modifier = Modifier.clickable { }
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CustomSearchBar(
                                    text = "Cari Makanan",
                                    modifier = Modifier.padding(start = 43.dp, end = 43.dp),
                                    iconModifier = Modifier.clickable {
                                        scope.launch {
                                            sheetState.show()
                                        }
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(7.dp),
                    ) {
                        items(foods) { menu ->
                            CustomCard(menu = menu)
                        }
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Food Banner",
                modifier = Modifier
                    .padding(start = 45.dp, end = 45.dp)
                    .offset(y = 150.dp)
                    .clip(RoundedCornerShape(15.dp)),
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun HomePreview() {
    DompetMakananTheme {
//        HomeScreen(rememberNavController())
    }
}