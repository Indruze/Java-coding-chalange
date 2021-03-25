package com.inventi.bankAccountManager.services;

import com.inventi.bankAccountManager.account.Account;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.inventi.bankAccountManager.repository.AccountRepository;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class CSVService {

    public static void writeToCSVFile(HttpServletResponse response,List<Account> listOfAccounts) throws IOException {

        ICsvListWriter csvListWriter = new CsvListWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "accountNumber", "operationDate", "beneficiary", "comment", "amount", "currency" };

        csvListWriter.writeHeader(header);
        for (Account account : listOfAccounts) {
            csvListWriter.write(Arrays.asList(
                    account.getAccountNumber(),
                    String.valueOf(account.getDate()),
                    account.getBeneficiary(),
                    account.getComment(),
                    String.valueOf(account.getAmount()),
                    account.getCurrency()
            ));
        }
        csvListWriter.close();
    }

    public static void saveCSVFile(MultipartFile file, AccountRepository repository) throws IOException {
        ICsvMapReader listReader = new CsvMapReader(new InputStreamReader(file.getInputStream()), CsvPreference.STANDARD_PREFERENCE);

        final String[] headers = listReader.getHeader(true);
        final CellProcessor[] processors =  new CellProcessor[] {
                new NotNull(),
                new NotNull(new ParseDate("yyyy-MM-dd HH:mm:ss.SSS")),
                new NotNull(),
                new Optional(),
                new Optional(new ParseBigDecimal()),
                new NotNull()
        };

        Map<String, Object> fieldsInCurrentRow;
        while ((fieldsInCurrentRow = listReader.read(headers, processors)) != null) {
            Account newAccount = new Account(fieldsInCurrentRow);
            repository.save(newAccount);
        }
        listReader.close();
    }
}
