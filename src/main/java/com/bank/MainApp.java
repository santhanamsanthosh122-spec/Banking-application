
package com.bank;

import com.bank.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.bank")
@EnableAspectJAutoProxy
public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MainApp.class);

        try {
            BankService bank =
                    context.getBean(BankService.class);

            System.out.println("===== BANKING APPLICATION =====");
            System.out.printf("Initial balance: Rs. %.2f%n",
                    bank.getBalance());

            // Test 1: Successful deposit
            try {
                bank.deposit(500);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Test 2: Successful withdrawal
            try {
                bank.withdraw(200);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Test 3: Withdrawal exceeding balance
            try {
                bank.withdraw(2000);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Test 4: Invalid deposit
            try {
                bank.deposit(-50);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Test 5: Invalid withdrawal
            try {
                bank.withdraw(0);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.printf("%nFinal balance: Rs. %.2f%n",
                    bank.getBalance());

            System.out.println("===== APPLICATION ENDED =====");

        } finally {
            context.close();
        }
    }
}
