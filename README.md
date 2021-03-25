# Coding Challenge (Inventi)

I assume that .csv file holds just one account data at a time and that credit/debit operations are indicated by the amount being negative/positive.

## How to run
To run execute command in project root:

### First step
Run application (BankAccountManagerApplication file). App runs on port 8080

### Post .csv file
```
POST /post-account
```

In Postman, "Body" section, choose form-data, set:
- KEY: type file name,
- type: file,
- value: select .csv file

Allowed CSV format example:
```
accountNumber,date,beneficiary,comment,amount,currency
LT02100001569895597,2021-01-20 14:12:54.934,245934,transfer,2.00,LTL
LT02100001569895597,2021-01-29 20:07:54.934,386435,transfer,-6.00,LTL
LT02100001569895597,2021-02-29 12:35:22.821,569123,transfer,13.22,LTL
LT02100001569895597,2021-03-25 12:52:41.682,996583,transfer,54.95,LTL
```

### Get .csv file
```
GET /get-account
```
Outputs CSV file
Possibility to get .csv for given dates period. Specify date to URL as a request param: `/get-account?from=2020-02-20&to=2020-02-22`\
Both `from` and `to` are optional.

### Calculate balance
```
GET /balance
```
Returns balance

Possibility to get calculated balance for given dates period. Specify date to URL as a request param: `/balance?from=2020-02-20&to=2020-02-22`\
Both `from` and `to` are optional.

## To-Do
- [ ] Implement tests with
- [ ] Refactor GET /balance endpoint
- [ ] Implement response messages
- [ ] Add frontend part
