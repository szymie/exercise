package org.szymie.exercise.boundaries;

import java.util.function.BooleanSupplier;

public interface TransactionExecutor {
    void execute(BooleanSupplier transactionCode);
}
