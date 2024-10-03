package com.billing.application.presentation.common.date

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.billing.application.R
import com.billing.application.presentation.common.text.text
import com.billing.application.ui.theme.blackColor
import com.billing.application.ui.theme.blueColorFaded
import com.billing.application.ui.theme.grayColor
import com.billing.application.ui.theme.lightGrayColor
import com.billing.application.ui.theme.whiteColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun DateRow(date : (String)-> Unit) {
    val list = DateListFromTodayToSixMonths(LocalDate.now())
    var index by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit) {
        date(list[index])
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(blueColorFaded)
        .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            painter = painterResource(id = R.drawable.arrow_left_),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .background(lightGrayColor, CircleShape)
                .padding(4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    index = index + 1
                    date(list[index])
                },
            tint = blackColor
        )
        text( value = list.get(index), fontSize = 18, fontWeight = FontWeight.SemiBold)
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .background(lightGrayColor, CircleShape)
                .padding(4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (index == 0) {
                        date(list[index])
                    } else {
                        index = index - 1
                        date(list[index])
                    }
                },
            tint = if(index == 0) grayColor else blackColor
        )
    }
}
@SuppressLint("NewApi")
@Composable
fun DateListFromTodayToSixMonths(today: LocalDate): List<String> {
    // Define the date format
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // Create a list to store dates
    val dateList = mutableListOf<String>()

    // Loop from 0 to 12 months back
    dateList.add(today.plusDays(1).format(dateFormatter))
    for (i in 0..30) {
        val pastDate = today.minusDays(i.toLong())
        dateList.add(pastDate.format(dateFormatter))
    }

    return dateList
}