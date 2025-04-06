package co.za.markxh.ka_chingthing.feature.landing.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.za.markxh.ka_chingthing.R
import co.za.markxh.ka_chingthing.ui.theme.Dimens

@Composable
fun LandingScreen(viewModel: LandingViewModel = hiltViewModel()) {
    val state by viewModel.state

    Column {
        TopSection(inputAmount = state.inputAmount)
        KeyPad(onKeyPressed = viewModel::onKeyPressed)
        BottomSection(state = state)
    }
}

@Composable
fun TopSection(inputAmount: Double) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = Dimens.TopSectionVerticalPadding)
    ) {
        InputAmountText(inputAmount = inputAmount)
    }
}

@Composable
fun InputAmountText(inputAmount: Double) {
    Text(
        text = stringResource(R.string.amount_with_currency, "%.2f".format(inputAmount)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.headlineLarge.copy( // Use theme typography
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
fun KeyPad(onKeyPressed: (String) -> Unit) {
    val buttons = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "DEL", "0", "ADD")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.chunked(3).forEach { rowButtons ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.KeyPadButtonHeight)
            ) {
                rowButtons.forEach { button ->
                    KeyPadButton(
                        button,
                        { onKeyPressed(button) },
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
fun KeyPadButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surface)
            .border(BorderStroke(Dimens.KeyPadButtonBorderWidth, MaterialTheme.colorScheme.outline)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun BottomSection(state: LandingState) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(vertical = Dimens.BottomSectionVerticalPadding)
    ) {
        item {
            state.itemAmounts.forEach { amount ->
                ItemAmountText(amount = amount)
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                modifier = Modifier.padding(horizontal = Dimens.BottomSectionHorizontalPadding)
            )
            TotalAmountText(totalAmount = state.totalAmount)
        }
    }
}

@Composable
fun ItemAmountText(amount: Double) {
    Text(
        text = stringResource(R.string.amount_with_currency, amount),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimens.ItemAmountEndPadding, bottom = Dimens.ItemAmountBottomPadding),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
    )
}

@Composable
fun TotalAmountText(totalAmount: Double) {
    Text(
        text = stringResource(R.string.amount_with_currency, totalAmount),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimens.ItemAmountEndPadding, top = Dimens.TotalAmountTopPadding),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
    )
}