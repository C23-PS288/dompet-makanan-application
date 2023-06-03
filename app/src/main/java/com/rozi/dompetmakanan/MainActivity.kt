package com.rozi.dompetmakanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

class MainActivity : ComponentActivity() {
//    private val viewModel : LoginViewModel by viewModels{ViewModelFactory.getInstance(application)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DompetMakananTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DompetMakananApp(application = application)
                }
            }
        }
    }
}
