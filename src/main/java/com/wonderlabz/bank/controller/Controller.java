package com.wonderlabz.bank.controller;


import com.wonderlabz.bank.constants.Constants;
import com.wonderlabz.bank.constants.ResponseMessage;
import com.wonderlabz.bank.dao.AccountRepository;
import com.wonderlabz.bank.dao.ClientRepository;
import com.wonderlabz.bank.dao.StatementRepository;
import com.wonderlabz.bank.model.BankAccount;
import com.wonderlabz.bank.model.BankStatement;
import com.wonderlabz.bank.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9091")
@RestController
    @RequestMapping("/api")
public class Controller {

    @Autowired
    ClientRepository daoInterface;

    @Autowired
    AccountRepository accInterface;

    @Autowired
    StatementRepository stateInterface;

    @PostMapping("/addclient")
    public ResponseEntity<ResponseMessage> newClient(@RequestBody Client client) {
        try {

            Date date = new Date();

           String description = "NEW ACCOUNT OPENED FOR : ";
           String transactionType = "NEW CLIENT";

            Client clientObj = new Client(client.getName(), client.getSurname(), client.getAddress(),
                    client.getId_Number(), client.getEmailAddress(),client.getGender(),client.getPassword(), client.getAccountNumber());

            for(BankAccount bankAcc : client.getBankAccountList()) {
                //new account with initial deposit.
                if (bankAcc.getAccountType().contains("SAVINGS")) {

                    if (bankAcc.getDeposit() >= 1000) {
                        BankAccount bankAccount =
                                new BankAccount(bankAcc.getAccountNumber(), bankAcc.getAccountType(), bankAcc.getDeposit());
                        //Transaction logs entry.
                        description=   description+client.getName()+" "+client.getSurname()+" WITH ACCOUNT NUMBER: "+client.getAccountNumber();
                        BankStatement bankStmt
                                = new BankStatement(client.getName(),client.getSurname(),date,
                                description, transactionType, client.getAccountNumber());

                        stateInterface.save(bankStmt);
                        accInterface.save(bankAccount);
                        daoInterface.save(clientObj);
                        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(Constants.ADDCLIENT_SUCCESS_MSG + client.getName()));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage(Constants.ADDCLIENT_FAILURE_MSG));
                    }
                }else if(bankAcc.getAccountType().contains("CURRENT")){
                    //setting new current initial overdraft limit.
                    double overdraft= 100000;

                    overdraft =   (overdraft + bankAcc.getDeposit());

                    BankAccount bankAccount =
                            new BankAccount(bankAcc.getAccountNumber(), bankAcc.getAccountType(),overdraft);
                    //Transaction logs entry.
                    description=   description+client.getName()+" "+client.getSurname()+" WITH ACCOUNT NUMBER: "+client.getAccountNumber();
                    BankStatement bankStmt
                            = new BankStatement(client.getName(),client.getSurname(),date,
                            description, transactionType, client.getAccountNumber());

                    stateInterface.save(bankStmt);
                    accInterface.save(bankAccount);
                    daoInterface.save(clientObj);

                }
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessage(Constants.ADDCLIENT_SUCCESS_MSG+client.getName()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(Constants.LOADED_FAILURE_MSG+e.getLocalizedMessage()));
        }
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<ResponseMessage> moneyDeposit(@PathVariable("id") String accountNumber, @RequestBody BankAccount bankAcc) {
        Optional<BankAccount> bankAccObj = accInterface.findById(accountNumber);

        Date date = new Date();

        String description = " NEW MONEY IN ";
        String transactionType = "DEPOSIT";

        try {
            if (bankAccObj.isPresent()) {
                //addition of money deposit
                BankAccount bankAccount = bankAccObj.get();
                bankAccount.setAmount(bankAcc.getDeposit() + bankAccount.getAmount());

                //Transaction logs entry
                BankStatement existingBankStatement = stateInterface.findByAccountNumber( bankAccount.getAccountNumber());
                if(existingBankStatement != null) {

                    BankStatement bankStmt
                            = new BankStatement(date,description, transactionType, bankAccount.getAccountNumber());

                    stateInterface.save(bankStmt);
                }

                accInterface.save(bankAccount);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(Constants.DEPOSIT_SUCCESS_MSG + bankAccount.getAmount()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(Constants.DEPOSIT_FAILURE_MSG));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(Constants.LOADED_FAILURE_MSG+e.getLocalizedMessage()));
        }
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<ResponseMessage> moneyWithdraw(@PathVariable("id") String accountNumber, @RequestBody BankAccount bankAcc) {
        Optional<BankAccount> bankAccObj = accInterface.findById(accountNumber);
        double overdraft = 100000;
        Date date = new Date();

        String description = " MONEY OUT ";
        String transactionType = "WITHDRAW";

        try {
            if (bankAccObj.isPresent()) {

                BankAccount bankAccount = bankAccObj.get();
                BankStatement existingBankStatement = stateInterface.findByAccountNumber(bankAccount.getAccountNumber());
                //For saving account withdrawals
                if(bankAcc.getAccountType().equalsIgnoreCase("SAVINGS")){

                    if(bankAccount.getAmount() >= bankAcc.getWithdraw()){
                        bankAccount.setAmount(bankAccount.getAmount() - bankAcc.getWithdraw());
                        accInterface.save(bankAccount);
                    }
                    //Transaction logs entry
                    if(existingBankStatement != null) {

                        BankStatement bankStmt
                                = new BankStatement(date,description, transactionType, bankAccount.getAccountNumber());

                        stateInterface.save(bankStmt);
                    }
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(Constants.WITHDRAW_SUCCESS_MSG + bankAccount.getAmount()));
                //For current account withdrawals
                }else if(bankAcc.getAccountType().equalsIgnoreCase("CURRENT")){
                    //condition check do not withdraw exceeding overdraft and account balance
                    if(bankAccount.getAmount() >= bankAcc.getWithdraw() || bankAcc.getWithdraw() <= overdraft){
                     bankAccount.setAmount(bankAccount.getAmount() - bankAcc.getWithdraw());
                     accInterface.save(bankAccount);
                    }
                    //Transaction logs entry
                    if(existingBankStatement != null) {

                        BankStatement bankStmt
                                = new BankStatement(date,description, transactionType, bankAccount.getAccountNumber());

                        stateInterface.save(bankStmt);
                    }
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(Constants.WITHDRAW_SUCCESS_MSG + bankAccount.getAmount()));

                }
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(Constants.WITHDRAW_INSUFFICIENT_MSG + bankAccount.getAmount()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(Constants.WITHDRAW_FAILURE_MSG));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(Constants.LOADED_FAILURE_MSG+e.getLocalizedMessage()));
        }
    }

    @PostMapping("/moneyTransfer")
    public ResponseEntity<ResponseMessage> moneyTransfer(@RequestParam(value = "senderAccountNumber", required = true) final String sAccountNumber,
                                                         @RequestParam(value = "receiptAccountNumber", required = true) final String rAccountNumber,
         @RequestParam(value = "transfers", required = true) final double transfers) {

        Optional<BankAccount> senderAccount = accInterface.findById(sAccountNumber);
        Optional<BankAccount> receiptAccount = accInterface.findById(rAccountNumber);

        Date date = new Date();

        String description = " MONEY TRANSFER ";
        String transactionType = "TRANSFER";
        try {
            //Checking if both accounts for transfers are existing
            if (senderAccount.isPresent() && receiptAccount.isPresent()) {

                //extract amount from account of send to decrease
                BankAccount bankAccount = senderAccount.get();
                bankAccount.setAmount(bankAccount.getAmount() - transfers);

                //Add amount from account to receiver account
                BankAccount bankAccReceiver = receiptAccount.get();
                bankAccReceiver.setAmount(bankAccReceiver.getAmount() + transfers);

                //Transaction logs entry
                BankStatement existingBankStatement = stateInterface.findByAccountNumber(bankAccount.getAccountNumber());
                if(existingBankStatement != null) {

                    BankStatement bankStmt
                            = new BankStatement(date,description, transactionType, bankAccount.getAccountNumber());

                    stateInterface.save(bankStmt);
                }

                accInterface.save(bankAccount);
                accInterface.save(bankAccReceiver);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(Constants.TRANSFER_SUCCESS_MSG + transfers));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(Constants.TRANSFER_FAILURE_MSG));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(Constants.LOADED_FAILURE_MSG+e.getLocalizedMessage()));
        }
    }


}
