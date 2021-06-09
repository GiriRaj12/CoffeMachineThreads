package com.coffeemachine.Services;

import com.coffeemachine.Controller.CoffeeMachineController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessBeverage {

    /**
     *
     * @param beverageName
     * @param beverage
     *
     * This method processes individual beverage
     */
    public void makeBeverage(String beverageName, Map<String, Integer> beverage, List<String> addAllCreatedBeverages){
        try{
            System.out.println("Making -> "+beverageName);

            for(Map.Entry<String, Integer> indiVidualBeverage : beverage.entrySet()){
                if(!(CoffeeMachineController.coffeMachineModel.getTotal_items_quantity().containsKey(indiVidualBeverage.getKey()) &&
                        CoffeeMachineController.coffeMachineModel.getTotal_items_quantity().get(indiVidualBeverage.getKey()) >= indiVidualBeverage.getValue()
                )){
                    throw new Exception(beverageName + " cannot be prepared because " + indiVidualBeverage.getKey() + " is not available ");
                }
            }


            for(Map.Entry<String, Integer> individualBeverage : beverage.entrySet()) {

                //processed boolean check weather in this thread has successfully processed the beverage or not.
                AtomicBoolean processed = new AtomicBoolean(false);

                //Computing logic
                CoffeeMachineController.coffeMachineModel.getTotal_items_quantity().compute(individualBeverage.getKey(), (requestedIngred,availableAmount) -> {
                    if(availableAmount >= individualBeverage.getValue()) {
                        processed.set(false);
                        return availableAmount - individualBeverage.getValue();
                    } else {
                        processed.set(true);
                        return availableAmount;
                    }
                });

                if(processed.get())
                    throw new Exception(beverageName + " cannot be prepared because " + individualBeverage.getKey() + " is not available ");
            }

            addAllCreatedBeverages.add(beverageName);
            System.out.println(beverageName +" is prepared");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
