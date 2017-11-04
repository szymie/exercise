package org.szymie.exercise.boundaries.adapters;

import java.util.function.BooleanSupplier;

public interface TransactionExecutor {
    void execute(BooleanSupplier transactionCode);
}
