package co.za.markxh.ka_chingthing.feature.landing.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class LandingViewModel: ViewModel() {

    private val _state = mutableStateOf(LandingState())
    val state: State<LandingState> = _state

    init {
        _state.value = LandingState(0.00, 0.00, arrayListOf())
    }

    fun onKeyPressed(key: String) {
        when (key) {
            "DEL" -> {
                val newAmount = BigDecimal(_state.value.inputAmount / 10).setScale(2, RoundingMode.DOWN).toDouble()
                _state.value = _state.value.copy(inputAmount = newAmount)
            }
            "ADD" -> {
                val itemList = _state.value.itemAmounts
                itemList.add(_state.value.inputAmount)
                val total = itemList.sum()
                _state.value = _state.value.copy(itemAmounts = itemList, inputAmount = 0.00, totalAmount = total)
            }
            else -> {
                val newAmount = BigDecimal((_state.value.inputAmount * 10) + (key.toDouble() / 100)).setScale(2, RoundingMode.DOWN).toDouble()

                if(newAmount >= 1000000.00) {
                    return
                }

                _state.value = _state.value.copy(inputAmount = newAmount)
            }
        }
    }
}