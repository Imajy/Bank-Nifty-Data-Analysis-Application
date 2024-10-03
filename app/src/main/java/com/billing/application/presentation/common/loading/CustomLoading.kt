package com.billing.application.presentation.common.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.billing.application.ui.theme.blueColor

@Composable
fun LoadingBlueBox(visible: Boolean = true) {
    if (visible) {
        Dialog(
            onDismissRequest = {  },
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (visible) {
                    CircularProgressIndicator(
                        color = blueColor, strokeWidth = 4.dp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}