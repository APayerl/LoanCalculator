package se.payerl.loancalculator

import java.math.BigDecimal

class Loan(
    val amount: BigDecimal,
    val rate: BigDecimal,
    val months: BigDecimal,
    var fee: BigDecimal?
) {
    init{
        if(fee == null) {
            this.fee = BigDecimal.ZERO
        }
    }
}