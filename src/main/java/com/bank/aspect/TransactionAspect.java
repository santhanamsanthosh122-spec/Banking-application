
package com.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

    @Before(
        "execution(* com.bank.service.BankService.deposit(..)) || " +
        "execution(* com.bank.service.BankService.withdraw(..))"
    )
    public void logBeforeTransaction(JoinPoint joinPoint) {
        System.out.println("\n[AOP BEFORE] Starting transaction: "
                + joinPoint.getSignature().getName());
    }

    @AfterReturning(
        "execution(* com.bank.service.BankService.deposit(..)) || " +
        "execution(* com.bank.service.BankService.withdraw(..))"
    )
    public void logAfterSuccessfulTransaction(
            JoinPoint joinPoint) {
        System.out.println("[AOP AFTER RETURNING] Transaction completed "
                + "successfully: "
                + joinPoint.getSignature().getName());
    }

    @AfterThrowing(
        pointcut =
            "execution(* com.bank.service.BankService.deposit(..)) || " +
            "execution(* com.bank.service.BankService.withdraw(..))",
        throwing = "exception"
    )
    public void logTransactionException(
            JoinPoint joinPoint,
            Throwable exception) {

        System.out.println("[AOP AFTER THROWING] Transaction failed: "
                + joinPoint.getSignature().getName());

        System.out.println("[AOP ERROR] "
                + exception.getMessage());
    }
}
