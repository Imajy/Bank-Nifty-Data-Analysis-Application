package com.billing.application.presentation.common

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.billing.application.presentation.common.text.text
import com.billing.application.ui.theme.blackColor
import com.billing.application.ui.theme.blueColor
import com.billing.application.ui.theme.blueColorFaded
import com.billing.application.ui.theme.lightGrayColor
import com.billing.application.ui.theme.whiteColor

@Composable
fun DropDownMenuBottom(
    finalList: List<String>,
    onItemSelected: (String) -> Unit,
    type: String
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    var isClicked by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(type) }
    var value by rememberSaveable { mutableStateOf(type) }

    val iconVector = if (isClicked) Icons.Sharp.KeyboardArrowUp else Icons.Sharp.KeyboardArrowDown

    Log.e( "DropDownMenuBottom: ","office_name1" )

    Column(modifier = Modifier.padding(horizontal = 6.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isClicked = !isClicked
                }
                .defaultMinSize(minHeight = (width / 9))
                .clip(RoundedCornerShape(8.dp))
                .background(blueColorFaded)
                .border(1.dp, Color.Gray.copy(alpha = 0.6f), RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isClicked = !isClicked
                }
                .weight(3f)) {
                text(
                    value = "   $selectedItem",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            isClicked = !isClicked
                        },
                    fontSize = 16,
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = selectedItem ?: value,
                    modifier = Modifier.clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ){
                        isClicked = !isClicked
                    },
                    tint = blackColor
                )
            }
        }

        AnimatedVisibility(visible = isClicked) {
            Column(modifier = Modifier.border(2.dp, lightGrayColor, RoundedCornerShape(8.dp))) {
                Log.e( "DropDownMenuBottom: ","office_name2" )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                    content = {
                        items(finalList) { item ->
                            Log.e( "DropDownMenuBottom: ","office_name3" )
                            val textColor = if (selectedItem.equals(item)) whiteColor else Color.Black
                            val background = if (selectedItem.equals(item)) blueColor else Color.Transparent

                            Row(
                                modifier = Modifier
                                    .padding(3.dp)
                                    .background(background, RoundedCornerShape(6.dp))
                                    .border(Dp.Hairline, blackColor, RoundedCornerShape(6.dp))
                                    .clickable {
                                        onItemSelected(item) // Update selectedItem state
                                        isClicked = false // Close the dropdown
                                        selectedItem = item
                                    },
                                horizontalArrangement = Arrangement.Center
                            ) {
                                text(
                                    value = item,
                                    color = textColor,
                                    fontSize = 14,
                                    modifier = Modifier
                                        .padding(
                                            top = 8.dp,
                                            bottom = 8.dp,
                                            start = 9.dp,
                                            end = 9.dp
                                        ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}