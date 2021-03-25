package com.inventi.bankAccountManager.controlers;

import com.inventi.bankAccountManager.account.Account;
import com.inventi.bankAccountManager.balance.Balance;
import com.inventi.bankAccountManager.repository.AccountRepository;
import com.inventi.bankAccountManager.services.CSVService;
import com.inventi.bankAccountManager.services.Calculator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class CSVController {

    private final AccountRepository repository;

    CSVController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-account")
    public void exportAccount(HttpServletResponse response,
                                   @RequestParam(name="from", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
                                   @RequestParam (name="to", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate
    ) throws IOException {

        List<Account> listOfAccount;
        if (fromDate != null && toDate != null) {
            listOfAccount = repository.findByDateAfterAndDateBefore(fromDate, toDate);
        } else if (fromDate != null) {
            listOfAccount = repository.findByDateAfter(fromDate);
        } else if (toDate != null) {
            listOfAccount = repository.findByDateBefore(toDate);
        } else {
            listOfAccount = repository.findAll();
        }

        response.setContentType("text/csv");
        String csvFileName = "report.csv";
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        CSVService.writeToCSVFile(response, listOfAccount);
        CSVService.writeToCSVFile(response, listOfAccount);
    }

    @PostMapping("/post-account")
    public void importAccount(@RequestParam("file") MultipartFile file) throws IOException {
        CSVService.saveCSVFile(file, repository);
        // TODO: set a message for successful request
    }

    @GetMapping("/balance")
    Balance balance(@RequestParam (name="from", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
                    @RequestParam (name="to", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate
    ) {
        List<Account> account;
        if (fromDate != null && toDate != null) {
            account = repository.findByDateAfterAndDateBefore(fromDate, toDate);
        } else if (fromDate != null) {
            account = repository.findByDateAfter(fromDate);
        } else if (toDate != null) {
            account = repository.findByDateBefore(toDate);
        } else {
            account = repository.findAll();
        }

        return Calculator.calculateBalance(account);
    }

}
