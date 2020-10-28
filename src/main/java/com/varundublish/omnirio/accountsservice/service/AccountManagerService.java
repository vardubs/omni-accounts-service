package com.varundublish.omnirio.accountsservice.service;

import com.varundublish.omnirio.accountsservice.client.CustomerServiceFeignClient;
import com.varundublish.omnirio.accountsservice.exceptions.AccountServiceException;
import com.varundublish.omnirio.accountsservice.model.entities.Account;
import com.varundublish.omnirio.accountsservice.model.request.AccountOpeningRequest;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.model.response.UserResponse;
import com.varundublish.omnirio.accountsservice.repository.AccountRepository;
import com.varundublish.omnirio.accountsservice.util.AccountUtility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Log
public class AccountManagerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    CustomerServiceFeignClient customerServiceClient;


    public UserResponse getUserInfoFromCustomerService(String authToken, Long customerId){
        UserResponse user = customerServiceClient.getExistingCustomer(authToken, customerId);
        return user;
    }


    public Account openAccount(String authToken, AccountOpeningRequest request){

        if(request.isExistingCustomer()){
            //Todo Validate Input Payload for Existing Customer
            //get Existing Customer Details

            UserResponse user = customerServiceClient.getExistingCustomer(authToken, request.getCustomerId());
            log.info("Existing Customer Details from Customer Service :" + user);

            //update Account Payload with the required customer details
            Account account = request.getAccount();
            account.setCustomerId(user.getUserId());
            account.setCustomerName(user.getUsername());
            account.setMinorIndicator(AccountUtility.getMinorFlag(user.getDateOfBirth()));
            account.setOpenDate(LocalDate.now());
            log.info("Opening Account with :" + account);
            return accountRepository.save(request.getAccount());
        }
        else {
            //New Customer
            //Todo Validate Input Payload as per New Customer
            UserResponse user = customerServiceClient.addNewCustomer(authToken, request.getNewCustomer());
            //update Account Payload with the new Customer Id, Name etc
            Account account = request.getAccount();
            account.setCustomerId(user.getUserId());
            account.setCustomerName(user.getUsername());
            account.setMinorIndicator(AccountUtility.getMinorFlag(user.getDateOfBirth()));
            account.setOpenDate(LocalDate.now());
            return  accountRepository.save(account);
        }
    }


    /**
     * Get Account Details
     * @param accountId
     * @return
     */
    public AccountResponse getAccountDetails(String authToken, Long accountId) {
        AccountResponse response = new AccountResponse();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountServiceException("Account not found for Id :!" + accountId));
        response.setAccount(account);
        response.setCustomer(getUserInfoFromCustomerService(authToken, account.getCustomerId()));
        return response;
    }
}


