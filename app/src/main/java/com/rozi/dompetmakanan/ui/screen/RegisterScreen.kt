package com.rozi.dompetmakanan.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.rozi.dompetmakanan.ui.components.BannerAuth
import com.rozi.dompetmakanan.ui.components.CustomButton
import com.rozi.dompetmakanan.ui.components.CustomTextFieldWithLabel
import com.rozi.dompetmakanan.ui.components.CustomTextFieldWithLabelEndIcon
import com.rozi.dompetmakanan.ui.theme.DompetMakananTheme

@Composable
fun RegisterScreen(){
    Column {
        BannerAuth()
        Menu(Modifier.offset(y = (-40).dp))
    }
}

@Composable
private fun Menu(modifier: Modifier = Modifier) {
    var textNama by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp
                )
            )
    ) {
        Spacer(modifier = modifier.padding(top = 56.dp))
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
            endIcon = painterResource(id = R.drawable.ic_show)
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
        CustomButton(text = "Register", modifier = modifier.padding(top = 43.dp)) {}
        Row(modifier = modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 26.dp)){
            Text(text = "Sudah memiliki akun?", style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF9F9D9D)
            ))
            Text(text = "Masuk", style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ))
        }
    }
}



@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterScreenPreview(){
    DompetMakananTheme {
        RegisterScreen()
    }
}