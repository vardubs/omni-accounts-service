package com.varundublish.omnirio.accountsservice.service;

import com.varundublish.omnirio.accountsservice.client.CustomerServiceFeignClient;
import com.varundublish.omnirio.accountsservice.exceptions.AccountServiceException;
import com.varundublish.omnirio.accountsservice.model.entities.Account;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.model.response.UserResponse;
import com.varundublish.omnirio.accountsservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Customer Service
 */
@Service
public class CustomerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    CustomerServiceFeignClient customerServiceClient;

    public UserResponse getUserInfoFromCustomerService(String authToken, Long customerId){
        UserResponse user = customerServiceClient.getExistingCustomer(authToken, customerId);
        return user;
    }

    /**
     * Get Account Details
     * @param accountId
     * @return
     */
    public AccountResponse getAccountDetails(String authToken, Long accountId) {
        AccountResponse response = new AccountResponse();
        Account account = accountRepository.findById(accountId).orElseThrow(()->new AccountServiceException("Account Not Found for this Id" + accountId));
        response.setAccount(account);
        response.setCustomer(getUserInfoFromCustomerService(authToken, account.getCustomerId()));
        return response;
    }
}
