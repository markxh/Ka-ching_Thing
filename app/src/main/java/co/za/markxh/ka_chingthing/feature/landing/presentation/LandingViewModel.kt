package co.za.markxh.ka_chingthing.feature.landing.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class LandingViewModel : ViewModel() {

    private val _state = mutableStateOf(LandingState())
    val state: State<LandingState> = _state

    init {
        _state.value = LandingState(
            inputAmount = BigDecimal.ZERO,
            totalAmount = BigDecimal.ZERO,
            itemAmounts = arrayListOf()
        )
    }

    fun onKeyPressed(key: String) {
        val currentAmount = _state.value.inputAmount

        when (key) {
            "DEL" -> {
                val newAmount = (currentAmount.movePointLeft(1)).setScale(2, RoundingMode.DOWN)
                _state.value = _state.value.copy(inputAmount = newAmount)
            }

            "ADD" -> {
                val currentItems = ArrayList(_state.value.itemAmounts)
                currentItems.add(currentAmount)

                val total = currentItems.fold(BigDecimal.ZERO) { acc, next ->
                    acc.add(next)
                }.setScale(2, RoundingMode.HALF_UP)

                _state.value = _state.value.copy(
                    inputAmount = BigDecimal.ZERO,
                    itemAmounts = currentItems,
                    totalAmount = total
                )
            }

            else -> {
                if (key.length == 1 && key[0].isDigit()) {
                    val digit = key.toInt()
                    val newAmount = currentAmount
                        .movePointRight(1)
                        .add(BigDecimal(digit).movePointLeft(2))
                        .setScale(2, RoundingMode.DOWN)

                    if (newAmount < BigDecimal("1000000.00")) {
                        _state.value = _state.value.copy(inputAmount = newAmount)
                    }
                }
            }
        }
    }
}