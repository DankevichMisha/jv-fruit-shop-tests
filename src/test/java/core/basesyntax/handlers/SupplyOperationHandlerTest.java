package core.basesyntax.handlers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.FruitShop;
import core.basesyntax.db.Storage;
import core.basesyntax.exception.InvalidDataException;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SupplyOperationHandlerTest {
    private static SupplyOperationHandler supplyOperationHandler;

    @BeforeAll
    static void beforeAll() {
        supplyOperationHandler = new SupplyOperationHandler();
    }

    @AfterEach
    void tearDown() {
        Storage.STORAGE.clear();
    }

    @Test
    void supplyHandler_validData_ok() {
        Storage.STORAGE.put("banana", 15);
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN, "banana", 100);
        assertDoesNotThrow(() -> supplyOperationHandler.calculateOperation(fruitTransaction));
        int actualAmount = Storage.STORAGE.get("banana");
        assertEquals(115, actualAmount);
    }

    @Test
    void supplyHandler_NegativeQuantity_notOk() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, "banana", -100);
        assertThrows(InvalidDataException.class,
                () -> supplyOperationHandler.calculateOperation(fruitTransaction));
    }

    @Test
    void supplyHandler_NullFruit_notOK() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, null, 100);
        assertThrows(NullPointerException.class,
                () -> supplyOperationHandler.calculateOperation(fruitTransaction));
    }

    @Test
    void supplyHandler_NullOperationType_notOk() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                null, "banana", 100);
        assertThrows(NullPointerException.class,
                () -> supplyOperationHandler.calculateOperation(fruitTransaction));
    }
}
