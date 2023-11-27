package com.rozi.dompetmakanan.ui.screen.login

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rozi.dompetmakanan.R
import com.rozi.dompetmakanan.ui.components.*
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme
import com.rozi.dompetmakanan.utils.ViewModelFactory


@Composable
fun LoginScreen(
    application: Application,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    ),
    onLoginState: () -> Unit,
    onClickRegister: () -> Unit
) {
    viewModel.authUIState.collectAsState(initial = AuthUIState.OnNotLogin).value.let { authUiStatte ->
        when (authUiStatte) {
            AuthUIState.OnLogin -> {
                LaunchedEffect(key1 = Unit) {
                    onLoginState()
                }
            }
            AuthUIState.OnNotLogin -> {
                LoginContent(
                    onClickRegister = onClickRegister,
                    onClickLogin = viewModel::login,
                    loadingProgressBar = viewModel.progressBar.value
                )
            }
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onClickRegister: () -> Unit,
    onClickLogin: (email: String, password: String) -> Unit,
    loadingProgressBar: Boolean
) {
    Box {
        Column(
            modifier = Modifier
                .background(Color.White)

        ) {
            BannerAuth()
            var textEmail by rememberSaveable { mutableStateOf("") }
            var textPassword by rememberSaveable { mutableStateOf("") }
            val isValidate by derivedStateOf { textEmail.isNotBlank() && textPassword.length > 8 }
            val passwordVisibility = remember { mutableStateOf(false) }
            Column(
                modifier = modifier
                    .offset(y = (-40).dp)
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    )
            ) {
                Spacer(modifier = modifier.padding(top = 20.dp))
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = modifier
                        .height(6.dp)
                        .width(90.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.h2,
                    modifier = modifier.padding(top = 40.dp, start = 30.dp)
                )
                Spacer(modifier = modifier.padding(top = 20.dp))
                CustomTextFieldWithLabel(
                    label = "Username", value = textEmail,
                    placeHolder = "Masukkan Email",
                    onValueChange = { newText ->
                        textEmail = newText
                    },
                    modifier = modifier,
                    icon = painterResource(id = R.drawable.ic_person)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))
                CustomTextFieldWithLabelEndIcon(
                    label = "Password", value = textPassword,
                    placeHolder = "Masukkan Password",
                    onValueChange = { newText ->
                        textPassword = newText
                    },
                    modifier = modifier,
                    startIcon = painterResource(id = R.drawable.ic_lock),
                    endIcon = {
                        IconButton(
                            onClick = { passwordVisibility.value = !passwordVisibility.value }
                        ) {
                            val visibilityIcon = if (passwordVisibility.value) {
                                painterResource(id = R.drawable.ic_hide)
                            } else {
                                painterResource(id = R.drawable.ic_show)
                            }
                            Icon(
                                painter = visibilityIcon,
                                contentDescription = if (passwordVisibility.value) "Hide password" else "Show password"
                            )
                        }
                    },
                    passwordVisibility = passwordVisibility.value
                )
                Text(
                    text = "Lupa Password?",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF888383)
                    ),
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(end = 30.dp, top = 14.dp)
                )
                CustomButton(
                    text = "Login", modifier = modifier.padding(top = 43.dp),
                    onClick = { onClickLogin(textEmail, textPassword) },
                    enabled = isValidate
                )
                Row(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 26.dp)
                ) {
                    Text(
                        text = "Belum memiliki akun?", style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF9F9D9D)
                        )
                    )
                    Text(
                        text = "Daftar", style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        ),
                        modifier = modifier.clickable { onClickRegister() }
                    )
                }
            }
        }
        ProgressBarLoading(isLoading = loadingProgressBar, modifier = modifier)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    DompetMakananTheme {
    }
}


