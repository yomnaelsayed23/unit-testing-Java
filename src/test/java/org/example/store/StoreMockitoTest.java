 package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StoreMockitoTest {

    Store store;

    Product product = new Product();
    Customer customer = new Customer();


    @Test void test1() {
        // Arrange
        product.setQuantity(8);
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        store = new StoreImpl(accountManager);

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
    }


    @Test void givenQuantityZero_whenMakeAnOrder_thenThrowExeption(){
//      Arrange
      product.setQantity(0);
//      Act
      AccountManager accountManager = Mockito.mock(AccountManager.class);
      store = new StoreImpl(accountManager);
      Exception exception = assertThrows(RuntimeException.class,()-> {
        store.buy(product,customer);
      })

//      Assert
      Assertions.assertEquals("Product out of stock", exception.getMessage());


    }

    @Test void returnFailure_whenMakeAnOrder_thenThrowExeption(){
//     Arrange
      product.setPrice(50);
      product.setQantity(5);

//      Act
      AccountManager accountManager = Mockito.mock(AccountManager.class);
      store = new StoreImpl(accountManager);
//      Assert
      when(accountManager.withdraw(customer,50)).thenReturn("insufficient account balance");
      Exeption exeption = assertThrows(RuntimeException.class, () -> {
        store.buy(product,customer);
      })


    }
}
