package core.basesyntax.strategy;

import core.basesyntax.model.Fruit;
import core.basesyntax.model.FruitTransfer;
import core.basesyntax.storage.Storage;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupplyOperationHandlerTest {
    private static OperationHandler supplyOperationHandler;

    @BeforeClass
    public static void setUp() {
        supplyOperationHandler = new SupplyOperationHandler();
    }

    @Test
    public void process_emptyStorageOk() {
        FruitTransfer fruit = new FruitTransfer(FruitTransfer.Operation.BALANCE,
                new Fruit("banana"), 100);
        supplyOperationHandler.process(fruit);
        Integer expected = 100;
        Assert.assertEquals(expected, Storage.fruits.get(fruit.getFruit()));
    }

    @Test
    public void process_ok() {
        Storage.fruits.put(new Fruit("banana"), 1);
        FruitTransfer fruit = new FruitTransfer(FruitTransfer.Operation.BALANCE,
                new Fruit("banana"), 100);
        supplyOperationHandler.process(fruit);
        Integer expected = 101;
        Assert.assertEquals(expected, Storage.fruits.get(fruit.getFruit()));
    }

    @After
    public void clearStorage() {
        Storage.fruits.clear();
    }
}