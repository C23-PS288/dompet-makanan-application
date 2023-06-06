package com.rozi.dompetmakanan.ui.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickRegister: (name: String, email: String, phone: String, password: String, confirmPass: String) -> Unit,
    loadingProgressBar: Boolean
) {
    Box {
        var textNama by remember { mutableStateOf("") }
        var textEmail by remember { mutableStateOf("") }
        var textPhone by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }
        var textConfirmPassword by remember { mutableStateOf("") }
        val isValidate by derivedStateOf {
            textPassword.isNotBlank()
                    && textEmail.isNotBlank()
                    && textPhone.isNotBlank()
                    && textPassword.length > 8
                    && textConfirmPassword.length > 8
            }
        val passwordVisibility = remember { mutableStateOf(false) }
        val confirmPasswordVisibility = remember { mutableStateOf(false) }
        val configuration = LocalConfiguration.current
        val height = configuration.screenHeightDp

        Column(modifier = modifier.background(Color.White)) {
            BannerAuth()

            Column(
                modifier = modifier
                    .offset(y = (-40).dp)
                    .fillMaxWidth()
                    .height(height.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = modifier.padding(top = 16.dp))
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = modifier
                        .height(6.dp)
                        .width(90.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            shape = RoundedCornerShape(
                                topStart = 40.dp,
                                topEnd = 40.dp
                            ),
                            color = MaterialTheme.colors.primary,
                        )
                )
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.h2,
                    modifier = modifier.padding(top = 16.dp, start = 30.dp)
                )
                Spacer(modifier = modifier.padding(top = 20.dp))
                CustomTextFieldWithLabel(
                    label = "nama", value = textNama,
                    placeHolder = "Masukkan nama",
                    onValueChange = { newText ->
                        textNama = newText
                    },
                    modifier = modifier,
                    icon = painterResource(id = R.drawable.ic_person)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))
                CustomTextFieldWithLabel(
                    label = "Email", value = textEmail,
                    placeHolder = "Masukkan email",
                    onValueChange = { newText ->
                        textEmail = newText
                    },
                    modifier = modifier,
                    icon = painterResource(id = R.drawable.ic_email)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))
                CustomTextFieldWithLabel(
                    label = "Phone Number", value = textPhone,
                    placeHolder = "Masukkan phone number",
                    onValueChange = { newText ->
                        textPhone = newText
                    },
                    modifier = modifier,
                    icon = painterResource(id = R.drawable.ic_call)
                )
                Spacer(modifier = modifier.padding(top = 16.dp))
                CustomTextFieldWithLabelEndIcon(
                    label = "Password", value = textPassword,
                    placeHolder = "Masukkan password",
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
                Spacer(modifier = modifier.padding(top = 16.dp))
                CustomTextFieldWithLabelEndIcon(
                    label = "Konfirmasi Password", value = textConfirmPassword,
                    placeHolder = "Masukkan konfirmasi password",
                    onValueChange = { newText ->
                        textConfirmPassword = newText
                    },
                    modifier = modifier,
                    startIcon = painterResource(id = R.drawable.ic_lock),
                    endIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                            }
                        ) {
                            val visibilityIcon = if (confirmPasswordVisibility.value) {
                                painterResource(id = R.drawable.ic_hide)
                            } else {
                                painterResource(id = R.drawable.ic_show)
                            }
                            Icon(
                                painter = visibilityIcon,
                                contentDescription = if (confirmPasswordVisibility.value) "Hide password" else "Show password"
                            )
                        }
                    },
                    passwordVisibility = confirmPasswordVisibility.value
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
            }
        }
        ProgressBarLoading(isLoading = loadingProgressBar)
        Column(
            modifier = modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
        ) {
            CustomButton(
                text = "Register",
                modifier = modifier,
                enabled = isValidate
            ) { onClickRegister(textNama, textEmail, textPhone, textPassword, textConfirmPassword) }
            Row(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Sudah memiliki akun?", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF9F9D9D)
                    )
                )
                Text(
                    text = "Masuk", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    ),
                    modifier = modifier.clickable { onClickLogin() }
                )
            }
        }


    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterScreenPreview() {
    DompetMakananTheme {
    }
}