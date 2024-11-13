package co.za.markxh.ka_chingthing.feature.landing.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.za.markxh.ka_chingthing.ui.theme.*

@Composable
fun LandingScreen(viewModel: LandingViewModel = hiltViewModel()) {

    val state by viewModel.state

    Column {
        TopSection(state.inputAmount)
        KeyPad(viewModel)
        BottomSection(state)
    }
}

@Composable
fun TopSection(inputAmount: Double) {
    Column(modifier = Modifier.background(Grey)) {
        Spacer(modifier = Modifier.height(height = 64.dp))
        InputAmountText(inputAmount)
        Spacer(modifier = Modifier.height(height = 34.dp))
    }
}

@Composable
fun InputAmountText(inputAmount: Double) {
    Text(
        text = "R ${"%.2f".format(inputAmount)}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.End,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 38.sp,
            color = SuperDarkGrey
        )
    )
}

@Composable
fun KeyPad(viewModel: LandingViewModel) {
    fun onKeyPressed(key: String) {
        viewModel.onKeyPressed(key)
    }

    val buttons = listOf(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9",
        "DEL", "0", "ADD"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in buttons.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                for (button in i) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clickable {
                                onKeyPressed(button)
                            }
                            .background(LightGrey)
                            .border(BorderStroke(1.dp, DarkGrey)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = button,
                            fontSize = 24.sp,
                            color = SuperDarkGrey
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSection(state: LandingState) {
    LazyColumn(
        modifier = Modifier
            .background(DarkGrey)
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(height = 64.dp))
            for (i in state.itemAmounts) {
                Text(
                    text = "R ${i}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 100.dp, bottom = 12.dp),
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    color = LightGrey
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = LightGrey,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "R ${state.totalAmount}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 100.dp, top = 12.dp),
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                color = LightGrey
            )
            Spacer(modifier = Modifier.height(height = 64.dp))
        }
    }
}