package com.rozi.dompetmakanan.ui.screen.home

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.ui.components.CustomCard
import com.rozi.dompetmakanan.ui.components.ProgressBarLoading
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme
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
    navController: NavController
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFoods()
                Loading()
            }
            is UiState.Success -> {
                HomeContent(navController = navController, foods = uiState.data)
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navController: NavController,
    foods: List<Food>
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val preferences = TokenPreferences(LocalContext.current)

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(20.dp),
        sheetState = sheetState,
        sheetContent = {
        Box {
            Column(Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .clickable { }
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.gallery),
                        contentDescription = null,
                        modifier = Modifier.width(25.dp).height(25.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text("Get From Gallery")
                }

                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .clickable { }
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.symbols_camera),
                        contentDescription = null,
                        modifier = Modifier.width(25.dp).height(25.dp),
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
                                    modifier = Modifier.clickable {
                                        scope.launch {
                                            sheetState.show()
                                        }
//                                        preferences.setToken("")
//                                        navController.popBackStack()
//                                        navController.navigate(route = Destination.Login.route) {
//                                            popUpTo(Destination.Login.route) {
//                                                inclusive = true
//                                            }
//                                        }
                                    }
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                SearchBar(
                                    modifier = Modifier
                                        .width(320.dp)
                                        .height(50.dp),
                                    query = text,
                                    onQueryChange = { text = it },
                                    onSearch = { active = false },
                                    active = active,
                                    onActiveChange = { active = it },
                                    placeholder = { Text(text = "Cari Makanan") },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.iconamoon_search),
                                            contentDescription = "Search Icon",
                                        )
                                    },
                                    shape = RoundedCornerShape(20.dp),
                                    colors = SearchBarDefaults.colors(
                                        containerColor = Color(73, 73, 73),
                                    ),
                                ) {

                                }
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