package org.szymie.exercise.external.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.szymie.exercise.boundaries.adapters.TransactionExecutor;

import java.util.function.BooleanSupplier;

@Component
public class SpringTransactionExecutor implements TransactionExecutor {

    private TransactionTemplate transactionTemplate;

    @Autowired
    public SpringTransactionExecutor(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void execute(BooleanSupplier transactionCode) {

        transactionTemplate.execute(transactionStatus -> {

            boolean commit = transactionCode.getAsBoolean();

            if(!commit) {
                transactionStatus.setRollbackOnly();
            }

            return transactionStatus.isRollbackOnly();
        });

    }
}
