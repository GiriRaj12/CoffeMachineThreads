import com.coffeemachine.Controller.CoffeeMachineController;
import com.coffeemachine.Models.CoffeMachineModel;
import com.coffeemachine.Models.OutLets;
import com.coffeemachine.Services.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachineTest {
    private CoffeMachineModel coffeMachineModel;
    private CoffeMachineTestHelper helper;

    @Before
    public void createCoffeeMachine() {
        helper = new CoffeMachineTestHelper();
    }

    @Test
    public void createBeverageTestForTwoItemsToPass() throws InterruptedException {
        coffeMachineModel = new CoffeMachineModel();
        OutLets outLets = new OutLets();
        outLets.setCount_n(3);
        coffeMachineModel.setOutlets(outLets);

        coffeMachineModel.setTotal_items_quantity(helper.getTotalItemsAfterSetting(500, 500, 100, 100, 100));
        Map<String, Map<String, Integer>> beverages = new HashMap<>();
        beverages.put("hot_tea", helper.getHotTea(100, 100, 10, 10, 30));
        beverages.put("hot_coffee", helper.getHotCoffee(100, 30, 400, 50, 30));
        beverages.put("black_tea", helper.getBlackTea(300, 30, 50, 30));
        beverages.put("green_tea", helper.getGreenTea(100, 30, 50, 30));
        coffeMachineModel.setBeverages(beverages);

        List<String> createdBeverages = CoffeeMachineController.makeCoffee(JSON.toJSON(coffeMachineModel));

        Thread.sleep(3000);
        System.out.println(createdBeverages);
        Assert.assertEquals(2, createdBeverages.size());
    }

    @Test
    public void createOnlyTwoBeverages() throws InterruptedException {
        coffeMachineModel = new CoffeMachineModel();
        OutLets outLets = new OutLets();
        outLets.setCount_n(3);
        coffeMachineModel.setOutlets(outLets);

        coffeMachineModel.setTotal_items_quantity(helper.getTotalItemsAfterSetting(500, 500, 100, 100, 100));
        Map<String, Map<String, Integer>> beverages = new HashMap<>();
        beverages.put("hot_tea", helper.getHotTea(100, 100, 10, 10, 30));
        beverages.put("hot_coffee", helper.getHotCoffee(100, 30, 400, 50, 30));
        coffeMachineModel.setBeverages(beverages);


        List<String> createdBeverages = CoffeeMachineController.makeCoffee(JSON.toJSON(coffeMachineModel));

        Thread.sleep(3000);
        System.out.println(createdBeverages);
        Assert.assertEquals(2, createdBeverages.size());
    }

    @Test
    public void createTenBeverageOnlyTheLastShouldPass() throws InterruptedException {
        coffeMachineModel = new CoffeMachineModel();
        OutLets outLets = new OutLets();
        outLets.setCount_n(2);
        coffeMachineModel.setOutlets(outLets);

        coffeMachineModel.setTotal_items_quantity(helper.getTotalItemsAfterSetting(100, 100, 10, 10, 10));
        Map<String, Map<String, Integer>> beverages = new HashMap<>();
        beverages.put("black_tea_normal", helper.getBlackTea(120, 10, 10, 10));
        beverages.put("black_tea_normal_extra_sugar", helper.getBlackTea(100, 10, 40, 10));
        beverages.put("black_tea_extreme_hot", helper.getBlackTea(300, 10, 10, 10));
        beverages.put("black_tea_extreme_sugar", helper.getBlackTea(100, 10, 100, 10));
        beverages.put("black_tea_light", helper.getBlackTea(100, 10, 10, 10));
        beverages.put("black_tea_heavy_ginger", helper.getBlackTea(100, 100, 10, 10));

        coffeMachineModel.setBeverages(beverages);
        List<String> createdBeverages = CoffeeMachineController.makeCoffee(JSON.toJSON(coffeMachineModel));

        Thread.sleep(3000);
        System.out.println(createdBeverages);
        Assert.assertEquals("black_tea_light", createdBeverages.get(0));
    }
}
