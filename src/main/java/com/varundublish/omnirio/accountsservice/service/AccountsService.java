package com.varundublish.omnirio.accountsservice.service;

import com.varundublish.omnirio.accountsservice.client.CustomerServiceFeignClient;
import com.varundublish.omnirio.accountsservice.exceptions.AccountServiceException;
import com.varundublish.omnirio.accountsservice.model.entities.Account;
import com.varundublish.omnirio.accountsservice.model.request.AccountOpeningRequest;
import com.varundublish.omnirio.accountsservice.model.response.AccountAndCustomerResponse;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.model.response.UserResponse;
import com.varundublish.omnirio.accountsservice.repository.AccountsRepository;
import com.varundublish.omnirio.accountsservice.util.AccountsUtility;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    CustomerServiceFeignClient customerServiceClient;

    @Autowired
    ModelMapper modelMapper;


    public UserResponse getUserInfoFromCustomerService(String authToken, String customerId){
        UserResponse user = customerServiceClient.getExistingCustomer(authToken, customerId);
        return user;
    }


    public AccountResponse openAccount(String authToken, AccountOpeningRequest request){

        UserResponse user;
        if(request.isExistingCustomer()){
            //Todo Validate Input Payload for Existing Customer
            //get Existing Customer Details from Customer Service
            user = customerServiceClient.getExistingCustomer(authToken, request.getCustomerId());
            log.info("Existing Customer Details from Customer Service :" + user);
        }
        else {
            //New Customer
            //Todo Validate Input Payload as per New Customer
            //Adding new Customer in Customer Service
            user = customerServiceClient.addNewCustomer(authToken, request.getNewCustomer());
        }

        //update Account Payload with the required customer details
        Account account = prepareAccountObject(request, user);
        log.info("Opening Account with :" + account);
        return modelMapper.map(accountsRepository.save(account), AccountResponse.class);
    }

    /**
     * Create the Account Entity Before its persisted
     * @param request
     * @param user
     * @return
     */
    private Account prepareAccountObject(AccountOpeningRequest request, UserResponse user) {
        Account account = request.getAccount();
        account.setAccountId(AccountsUtility.generateAccountId(account.getBranch()));
        account.setCustomerId(user.getUserId());
        account.setCustomerName(user.getFirstName()+ " " + user.getLastName());
        account.setMinorIndicator(AccountsUtility.getMinorFlag(user.getDateOfBirth()));
        account.setOpenDate(LocalDate.now());
        return account;
    }


    /**
     * Get Account Details
     * @param accountId
     * @return
     */
    public AccountAndCustomerResponse getCustomerAccountDetails(String authToken, String accountId) {
        AccountAndCustomerResponse response = new AccountAndCustomerResponse();
        List<Account> account = accountsRepository.findByAccountId(accountId).orElseThrow(() -> new AccountServiceException("Account not found for Id :!" + accountId));
        response.setAccount(account.stream()
                .map((a) -> modelMapper.map(a, AccountResponse.class))
                .collect(Collectors.toList()));
        response.setCustomer(getUserInfoFromCustomerService(authToken, account.get(0).getCustomerId()));
        return response;
    }


    public UserResponse getMyDetailsFromCustomerService(String authToken){
        UserResponse user = customerServiceClient.getMyDetails(authToken);
        return user;
    }

    /**
     * Get My Account Details
     * @param accountId
     * @return
     */
    public AccountAndCustomerResponse getMyAccountDetails(String authToken) {
        UserResponse user = getMyDetailsFromCustomerService(authToken);
        AccountAndCustomerResponse response = new AccountAndCustomerResponse();
        List<Account> account = accountsRepository.findByCustomerId(user.getUserId()).orElse(new ArrayList<>());
                //.orElseThrow(()->new AccountServiceException("Accounts Not Found for this Id" + user.getUserId()));
        response.setAccount(account.stream()
                .map((a) -> modelMapper.map(a, AccountResponse.class))
                .collect(Collectors.toList()));
        response.setCustomer(user);
        return response;
    }

}


