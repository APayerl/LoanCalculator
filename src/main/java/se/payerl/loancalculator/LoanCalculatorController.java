package se.payerl.loancalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/")
public class LoanCalculatorController {
    private final LoanService loanService;

    @Autowired
    public LoanCalculatorController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path = "/calculate", consumes = "application/json", produces = "application/json")
    public List<Repayment> calculate(@RequestBody @NonNull Loan loan) {
        return loanService.calculateRepayments(loan);
    }
}
