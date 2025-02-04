package co.za.markxh.ka_chingthing.feature.landing.presentation

import java.math.BigDecimal

data class LandingState(
    var inputAmount: BigDecimal = BigDecimal.ZERO,
    var totalAmount: BigDecimal = BigDecimal.ZERO,
    var itemAmounts: ArrayList<BigDecimal> = arrayListOf()
)
