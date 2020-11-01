package com.varundublish.omnirio.accountsservice.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class AccountsUtility {


    public static String generateAccountId(String branch) {
        return "ACC-" + branch + "-" + UUID.randomUUID().toString();
    }

    public static Character getMinorFlag(LocalDate dob){

        LocalDate today = LocalDate.now();

        Period period = Period.between(dob, today);

        System.out.print(period.getYears() + " years,");//TODO to be removed

        if(period.getYears()<18){
            return Character.valueOf('Y');
        }
        return Character.valueOf('N');
    }

}
