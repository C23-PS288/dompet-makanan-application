package com.rozi.dompetmakanan.ui.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickRegister: (name: String, email: String, password: String) -> Unit,
    loadingProgressBar: Boolean
) {
    Column(modifier = modifier.background(Color.White)) {
        BannerAuth()
        var textNama by remember { mutableStateOf("") }
        var textEmail by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }
        val isValidate by derivedStateOf { textPassword.isNotBlank() && textEmail.isNotBlank() && textPassword.isNotBlank() }
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
                text = "Register",
                style = MaterialTheme.typography.h2,
                modifier = modifier.padding(top = 40.dp, start = 30.dp)
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
                label = "Email or Phone Number", value = textEmail,
                placeHolder = "Masukkan email or phone number",
                onValueChange = { newText ->
                    textEmail = newText
                },
                modifier = modifier,
                icon = painterResource(id = R.drawable.ic_call)
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
                text = "Register",
                modifier = modifier.padding(top = 43.dp),
                enabled = isValidate
            ) { onClickRegister(textNama, textEmail, textPassword) }
            Row(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 26.dp)
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
        ProgressBarLoading(isLoading = loadingProgressBar)
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterScreenPreview() {
    DompetMakananTheme {
    }
}