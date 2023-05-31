package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    Icon: @Composable (() -> Unit)
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .border(width = 1.5.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(12.dp))
            .size(width = 315.dp, height = 48.dp)
            .background(Color.White)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = Icon,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = textStyle,
            placeholder = { Text(text = placeHolder, style = TextStyle(fontSize = 12.sp)) },
        )
    }
}