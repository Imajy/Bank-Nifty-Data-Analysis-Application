package com.billing.application.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.billing.application.common.Screen
import com.billing.application.common.SharedViewModel
import com.billing.application.data.model.User
import com.billing.application.presentation.common.button.RoundedCornerButton
import com.billing.application.presentation.common.date.DateRow
import com.billing.application.presentation.common.text.text
import com.billing.application.ui.theme.blackColor
import com.billing.application.ui.theme.blueColor
import com.billing.application.ui.theme.grayColor
import com.billing.application.ui.theme.whiteColor
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val viewModels = hiltViewModel<ViewModels>()
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    var date by remember { mutableStateOf("") }

//    var type by remember { mutableStateOf("Real") }
    var upDownCallPutState by remember { mutableStateOf(UpDownCallPutState()) }

    var filteredUserList by remember { mutableStateOf(listOf<User>()) }

    LaunchedEffect(Unit) {
        viewModels.getUsers({
            filteredUserList = it
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteColor)
            .padding(top = innerPadding.calculateTopPadding())
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //date change and show
        DateRow {
            date = it
            sharedViewModel.setDate(it)
            upDownCallPutState = UpDownCallPutState()
        }

//        DropDownMenuBottom(finalList = listOf("Real", "Prediction"), { type = it }, type)

        LazyColumn {

            items(filteredUserList) { data ->

                if (data.date == date) {
                    LaunchedEffect(Unit) {
                        upDownCallPutState = UpDownCallPutState(
                            up = data.up,
                            down = data.down,
                            call = data.call,
                            put = data.put,
                            upList = data.uplist,
                            downList = data.downList
                        )
                        sharedViewModel.setCall(data.call)
                        sharedViewModel.setPut(data.put)
                        sharedViewModel.setUp(data.up)
                        sharedViewModel.setDown(data.down)
                    }

                    space(10)
                    TargetComp(
                        upDownCallPutState.upList,
                        head = "To Up",
                        value = upDownCallPutState.up
                    )

                   space(15)

                    TargetComp(
                        upDownCallPutState.downList,
                        head = "To down",
                        value = upDownCallPutState.down
                    )

                    space(10)

                    CallPut("Call :", upDownCallPutState.call)

                    CallPut("Put  :", upDownCallPutState.put)
                }
            }
        }

        RoundedCornerButton(
            label = if (upDownCallPutState.hasData()) "Edit" else "Insert",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            fontSize = 30,
            background = blueColor,
            color = whiteColor,
            enabled = true
        ) {
            if (it) {
                if (upDownCallPutState.hasData()) {
                    sharedViewModel.setEditType(true)
                } else {
                    sharedViewModel.setPut("")
                    sharedViewModel.setUp("")
                    sharedViewModel.setCall("")
                    sharedViewModel.setDown("")
                    sharedViewModel.setEditType(false)
                }
                navController.navigate(Screen.EditScreen.route)
            }
        }
    }
}

@Composable
fun CallPut(type: String,value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        text(type, fontSize = 22, fontWeight = FontWeight.Medium)
        text(value, fontSize = 16)

    }
}

@Composable
fun TargetComp(
    list: List<String>,
    head:String,
    value: String
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 500.dp)
            .border(1.dp, grayColor, RoundedCornerShape(6.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                text(head, fontSize = 18, fontWeight = FontWeight.SemiBold)
                text(value, fontSize = 14, fontWeight = FontWeight.SemiBold)
            }
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .height(2.dp), color = blackColor)
        }
        itemsIndexed(list){idx,it->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                text("target${idx + 1}", fontSize = 16)
                text(it, fontSize = 14)
            }
            if (idx != (list.size-1)) {

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp), color = grayColor.copy(.5f)
                )
            }
        }
    }
}

@Composable
fun space(height: Int = 10) {
    Spacer(Modifier.height(height.dp))
}

// Use a data class to handle up, down, call, put and lists in a compact form
data class UpDownCallPutState(
    val up: String = "",
    val down: String = "",
    val call: String = "",
    val put: String = "",
    val upList: List<String> = emptyList(),
    val downList: List<String> = emptyList()
) {
    fun hasData() = up.isNotBlank() || down.isNotBlank() || call.isNotBlank() || put.isNotBlank()
}