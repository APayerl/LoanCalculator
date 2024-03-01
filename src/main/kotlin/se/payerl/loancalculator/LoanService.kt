package se.payerl.loancalculator

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class LoanService {
    private fun calculateMonthlyInterest(amount: BigDecimal, rate: BigDecimal): BigDecimal {
        return amount.multiply(
            rate.divide(BIG_DECIMAL_12, 10, RoundingMode.HALF_DOWN).divide(BIG_DECIMAL_100, 10, RoundingMode.HALF_DOWN)
        )
    }

    fun calculateRepayments(loan: Loan): List<Repayment> {
        val repayments: MutableList<Repayment> = ArrayList()
        var amortization: BigDecimal = loan.amount.divide(loan.months, 10, RoundingMode.HALF_UP)
        var totalAmortized = BigDecimal.ZERO

        for (i in 0 until loan.months.intValueExact()) {
            if (i == loan.months.intValueExact() - 1) {
                amortization = loan.amount.subtract(totalAmortized)
            }
            val interest = calculateMonthlyInterest(loan.amount.subtract(totalAmortized), loan.rate)
            repayments.add(Repayment(amortization, interest, loan.fee!!, i + 1))
            totalAmortized = totalAmortized.add(amortization)
        }
        return repayments
    }

    companion object {
        private val BIG_DECIMAL_12: BigDecimal = BigDecimal.valueOf(12)
        private val BIG_DECIMAL_100: BigDecimal = BigDecimal.valueOf(100)
    }
}
