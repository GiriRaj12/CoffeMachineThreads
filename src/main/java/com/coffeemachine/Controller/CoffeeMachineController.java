package com.coffeemachine.Controller;

import com.coffeemachine.Models.CoffeMachineModel;
import com.coffeemachine.Services.JSON;
import com.coffeemachine.Services.ProcessBeverage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class CoffeeMachineController {

    //Coffee Machine Structure
    public static CoffeMachineModel coffeMachineModel = null;

    private static List<String> createdBeverages;


    //Coffe Machine Control starts here
    public static List<String> makeCoffee(String coffeePayload) {
        try {
            createdBeverages = new ArrayList<>();
            coffeMachineModel = JSON.fromJSON(coffeePayload, CoffeMachineModel.class);


            //Taking out beverages to be made from payload
            Map<String, Map<String, Integer>> beverages = coffeMachineModel.getBeverages();

            //Getting maximum parallel process limit from payload
            int allowedNumberOfThreads = coffeMachineModel.getOutlets().getCount_n();

            //Initializing a constant thread executor to process only limited number of threads in thread pool
            ExecutorService executorService = Executors.newFixedThreadPool(allowedNumberOfThreads);
            List<Future<Runnable>> futureServices = new ArrayList<>();

            for (Map.Entry<String, Map<String, Integer>> entry : beverages.entrySet()) {
                //Adding all the beverages to be processed
                Future futureService = executorService.submit(getBeverageProcessingThread(entry.getKey(), entry.getValue()));
                futureServices.add(futureService);
            }

            for (Future<Runnable> serviceToRun : futureServices) {
                //Waiting for threads to complete its action one by one
                serviceToRun.get();
            }

            executorService.shutdownNow();
            return createdBeverages;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong !");
            return new ArrayList<>();
        }
    }

    /**
     *
     * @param beverageName
     * @param ingredsNeeded
     * @return thread
     *
     * This method produces a thread for thread pool to process
     */
    private static Thread getBeverageProcessingThread(String beverageName, Map<String, Integer> ingredsNeeded){
        return new Thread("Processing"+beverageName){
            public void run(){
                new ProcessBeverage().makeBeverage(beverageName, ingredsNeeded, createdBeverages);
            }
        };
    }


    /**
     *
     * @param beverageName
     * @param quantity
     */
    public static void refill(String beverageName, int quantity){
        new Thread() {
            public void run(){
                if(coffeMachineModel.getTotal_items_quantity().containsKey(beverageName)){
                    coffeMachineModel.getTotal_items_quantity().compute(beverageName, (name, present) -> {
                        return present + quantity;
                    });
                }
            }
        }.start();
    }

}
