package core.basesyntax.handlers;

import core.basesyntax.db.Storage;
import core.basesyntax.exception.InvalidDataException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class BalanceOperationHandler implements OperationHandler {
    @Override
    public void calculateOperation(FruitTransaction transaction) {
        if (transaction.getQuantity() < 0) {
            throw new InvalidDataException("Negative quantity");
        } else if (transaction.getFruit() == null || transaction.getOperation() == null) {
            throw new NullPointerException("Invalid transaction or fruit type");
        }
        Storage.STORAGE.put(transaction.getFruit(), transaction.getQuantity());
    }
}
