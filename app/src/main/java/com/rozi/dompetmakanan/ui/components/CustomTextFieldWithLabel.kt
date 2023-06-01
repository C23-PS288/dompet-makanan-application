package com.rozi.dompetmakanan.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rozi.dompetmakanan.R

@Composable
fun CustomTextFieldWithLabel(
    label: String,
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    icon : Painter
) {
    Text(
        text = label,
        style = MaterialTheme.typography.h3,
        modifier = modifier.padding(start = 30.dp)
    )
    CustomTextField(
        value = value,
        placeHolder = placeHolder,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 6.dp),

        ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
    }
}