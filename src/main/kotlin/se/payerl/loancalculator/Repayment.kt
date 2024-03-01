package se.payerl.loancalculator

import java.math.BigDecimal

data class Repayment(
    val amortization: BigDecimal,
    val interest: BigDecimal,
    val fee: BigDecimal,
    val month: Int = 0
) {
    fun total(): BigDecimal {
        return amortization.add(interest).add(fee)
    }
}
