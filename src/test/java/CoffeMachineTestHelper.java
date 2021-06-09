import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeMachineTestHelper {

    public Map<String, Integer> getHotTea(int hot_water, int hot_milk, int ginger_syrup, int sugar_syrup, int tea_leaves_syrup){
        Map<String, Integer> hottea = new HashMap<>();
        hottea.put("hot_water", hot_water);
        hottea.put("hot_milk", hot_milk);
        hottea.put("ginger_syrup", ginger_syrup);
        hottea.put("sugar_syrup", sugar_syrup);
        hottea.put("tea_leaves_syrup", tea_leaves_syrup);
        return hottea;
    }

    public Map<String, Integer> getHotCoffee(int hot_water, int ginger_syrup, int hot_milk,  int sugar_syrup, int tea_leaves_syrup){
        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put("hot_water", hot_water);
        hotCoffee.put("ginger_syrup", ginger_syrup);
        hotCoffee.put("hot_milk", hot_milk);
        hotCoffee.put("sugar_syrup", sugar_syrup);
        hotCoffee.put("tea_leaves_syrup", tea_leaves_syrup);
        return hotCoffee;
    }

    public Map<String, Integer> getBlackTea(int hot_water, int ginger_syrup, int sugar_syrup, int tea_leaves_syrup){
        Map<String, Integer> balckTea = new HashMap<>();
        balckTea.put("hot_water", hot_water);
        balckTea.put("ginger_syrup", ginger_syrup);
        balckTea.put("sugar_syrup", sugar_syrup);
        balckTea.put("tea_leaves_syrup", tea_leaves_syrup);
        return balckTea;
    }

    public Map<String, Integer> getGreenTea(int hot_water, int ginger_syrup, int sugar_syrup, int green_mixture){
        Map<String, Integer> greenTea = new HashMap<>();
        greenTea.put("hot_water", hot_water);
        greenTea.put("ginger_syrup", ginger_syrup);
        greenTea.put("sugar_syrup", sugar_syrup);
        greenTea.put("green_mixture", green_mixture);
        return greenTea;
    }

    public ConcurrentHashMap<String, Integer> getTotalItemsAfterSetting(int hot_water, int hot_milk, int ginger_syrup, int sugar_syrup, int tea_leaves_syrup ){
        ConcurrentHashMap<String, Integer> totalItems =  new ConcurrentHashMap<>();
        totalItems.put("hot_water", hot_water);
        totalItems.put("hot_milk", hot_milk);
        totalItems.put("ginger_syrup", ginger_syrup);
        totalItems.put("sugar_syrup", sugar_syrup);
        totalItems.put("tea_leaves_syrup", tea_leaves_syrup);
        return totalItems;
    }
}
