package com.billing.application.presentation.common.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.billing.application.presentation.common.text.text
import com.billing.application.ui.theme.blackColor
import com.billing.application.ui.theme.blueColor
import com.billing.application.ui.theme.blueColorFaded
import com.billing.application.ui.theme.grayOnboard

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BasicTextField(
            modifier = modifier,
            value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
            onValueChange = {
                if (it.text.length <= otpCount) {
                    onOtpTextChange.invoke(it.text, it.text.length == otpCount)
                }
            },
            textStyle = TextStyle(color = blueColor),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = {
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                    repeat(otpCount) { index ->
                        CharView(
                            index = index,
                            text = otpText,
                            width = width,
//                            modifier = Modifier.weight(1f)
                        )
//                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        )
    }

}

@Composable
private fun CharView(
    index: Int,
    text: String,
    width: Dp,
    modifier: Modifier = Modifier
) {
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = blueColorFaded,
            contentColor = grayOnboard
        ),
        modifier = modifier
            .height(width/7)
            .width(width/7)
            .border(1.dp, blueColor, RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),

    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            text(
                value = char,
                fontSize = 20,
                modifier = Modifier
                    .size(width/9)
                    .padding(top = 12.dp)
            )
        }
    }

}