package com.billing.application.presentation.home_screen.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.billing.application.common.SharedViewModel
import com.billing.application.data.model.User
import com.billing.application.presentation.common.button.RoundedCornerButton
import com.billing.application.presentation.common.text.text
import com.billing.application.presentation.common.textField.AddressInput
import com.billing.application.presentation.home_screen.ViewModels
import com.billing.application.ui.theme.blueColor
import com.billing.application.ui.theme.whiteColor

@Composable
fun InsertUserScreen(navController: NavController, innerPadding: PaddingValues) {
    val viewModels = hiltViewModel<ViewModels>()
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    // State variables to hold user input
    var up by remember { mutableStateOf(sharedViewModel.getUp()) }
    var down by remember { mutableStateOf(sharedViewModel.getDown()) }
    var put by remember { mutableStateOf(sharedViewModel.getPut()) }
    var call by remember { mutableStateOf(sharedViewModel.getCall()) }

    var upListText by remember { mutableStateOf("") }
    var downListText by remember { mutableStateOf("") }
    var upList by remember { mutableStateOf(listOf<String>()) }
    var downList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteColor)
            .padding(top = innerPadding.calculateTopPadding(), start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        text(value = "Insert User Data", fontSize = 24, fontWeight = FontWeight.Bold)

        // up
        AddressInput(
            data = up,
            placeHolder = "enter up",
            label = "To Up",
            onValueChange = {
                up = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        // uplist input
        AddressInput(
            data = upListText,
            placeHolder = "Enter values for uplist (comma-separated)",
            label = "Up List",
            onValueChange = { input ->
                upListText = input
                upList = input.split(",")
            },
            limit = 100,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        // down
        AddressInput(
            data = down,
            placeHolder = "enter down",
            label = "To Down",
            onValueChange = {
                down = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )
        // uplist input
        AddressInput(
            data = downListText,
            placeHolder = "Enter values for downList (comma-separated)",
            label = "Down List",
            onValueChange = { input ->
                downListText = input
                downList = input.split(",")
            },
            limit = 100,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        // put
        AddressInput(
            data = put,
            placeHolder = "enter put",
            label = "Put",
            onValueChange = {
                put = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        // call
        AddressInput(
            data = call,
            placeHolder = "enter call",
            label = "Call",
            onValueChange = {
                call = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
        )

        // Button to save the data
        RoundedCornerButton(
            label = "Insert",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            fontSize = 30,
            background = blueColor,
            color = whiteColor,
            enabled = true
        ) {
            if (it) {
                val request = User(
                    up = up,
                    down = down,
                    put = put,
                    call = call,
                    date = sharedViewModel.getDate(),
                    uplist = upList, // Populate with your data
                    downList = downList // Populate with your data
                )
                if (sharedViewModel.getEditType()){
                    viewModels.updateUsers(request)
                navController.popBackStack() // Navigate back after inserting
                }else {
                    viewModels.insertUser(request)
                navController.popBackStack() // Navigate back after inserting
                }
            }
        }
    }
}
