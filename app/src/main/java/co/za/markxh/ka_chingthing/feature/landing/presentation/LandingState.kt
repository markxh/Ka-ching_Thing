package co.za.markxh.ka_chingthing.feature.landing.presentation

data class LandingState(
    var inputAmount: Double = 0.00,
    var totalAmount: Double = 0.00,
    var itemAmounts: ArrayList<Double> = arrayListOf()
)
