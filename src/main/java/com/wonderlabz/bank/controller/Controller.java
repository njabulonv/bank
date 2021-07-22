package com.wonderlabz.bank.controller;


import com.wonderlabz.bank.constants.Constants;
import com.wonderlabz.bank.constants.ResponseMessage;
import com.wonderlabz.bank.dao.AccountRepository;
import com.wonderlabz.bank.dao.ClientRepository;
import com.wonderlabz.bank.model.BankAccount;
import com.wonderlabz.bank.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9091")
@RestController
    @RequestMapping("/api")
public class Controller {

    @Autowired
    ClientRepository daoInterface;

    @Autowired
    AccountRepository accInterface;

    @PostMapping("/addclient")
    public ResponseEntity<ResponseMessage> newClient(@RequestBody Client client) {
        try {
            Client clientObj = new Client(client.getName(), client.getSurname(), client.getAddress(),
                    client.getId_Number(), client.getEmailAddress(),client.getGender(),client.getPassword(), client.getAccountNumber());

            for(BankAccount bankAcc : client.getBankAccountList()) {

                if (bankAcc.getAccountType().contains("SAVINGS")) {

                    if (bankAcc.getDeposit() <= 1000) {
                        BankAccount bankAccount =
                                new BankAccount(bankAcc.getAccountNumber(), bankAcc.getAccountType(), bankAcc.getDeposit());
                        accInterface.save(bankAccount);
                        daoInterface.save(clientObj);
                        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(Constants.ADDCLIENT_SUCCESS_MSG + client.getName()));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage(Constants.ADDCLIENT_FAILURE_MSG));
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(Constants.ADDCLIENT_SUCCESS_MSG+client.getName()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(Constants.LOADED_FAILURE_MSG+e.getLocalizedMessage()));
        }
    }


}
