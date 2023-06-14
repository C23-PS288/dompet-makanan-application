package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    text: String
){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        placeholder = { Text(text = text)},
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.iconamoon_search),
                contentDescription = "Icon Search" )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.mdi_camera),
                contentDescription = "Icon Camera",
                modifier = iconModifier
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(73,73,73),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}