package org.example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();

    @Test void givenAmountBelowMaxCreditForNormalCustomerWhenWithdrawThenSubtractFromBalance() {
        // Arrange
        customer.setBalance(100);

        // Act
        String result = accountManager.withdraw(customer, 80);

        // Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals(20, expectedBalance);
        Assertions.assertEquals("success", result);
    }

    @Test void TestWithdraw_whenCustomerNotIsCreditAllowed_ThenReturnInsufficientBalance (){

//        Arrange
        customer.setBalance(200);

//        Act
        String result = accountManager.withdraw(customer,400);

//        Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals("insufficient account balance",result);
        Assertions.assertEquals(200,expectedBalance);


    }
    @Test void testWithdraw_whenCustomerIsVip_ThenSuccess(){
//        Arrange
        customer.setBalance(5000);
        customer.setVip(true);

//        Act
        String result = accountManager.withdraw(customer,6000);

//        Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(-1000,expectedBalance);

    }

    @Test void testWithdraw_whenCustomerWantToTakeMoreThanMaxCridetAndNotvip_ThenReturnmaximumCreditExceeded(){
//        Arrange

        customer.setBalance(5000);
        customer.setVip(false);
        customer.setCreditAllowed(true);

        //    Act
        String result = accountManager.withdraw(customer,6000);

        //        Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals("maximum credit exceeded",result);
        Assertions.assertEquals(5000,expectedBalance);
    }

    @Test void testWithDraw_whenCustomerWantToTakeMoreThanMaxCridetAndCreditNotAllowed_ThenReturnmaximumCreditExceeded(){
//        Arrange

        customer.setBalance(5000);
        customer.setVip(true);
        customer.setCreditAllowed(false);

        //    Act
        String result = accountManager.withdraw(customer,6000);


        //        Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals("maximum credit exceeded",result);
        Assertions.assertEquals(5000,expectedBalance);
    }
}
