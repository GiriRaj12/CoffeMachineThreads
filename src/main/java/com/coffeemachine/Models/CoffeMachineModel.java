package com.coffeemachine.Models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeMachineModel {
    private OutLets outlets;

    private ConcurrentHashMap<String, Integer> total_items_quantity;

    private Map<String, Map<String, Integer>> beverages;

    public OutLets getOutlets() {
        return outlets;
    }

    public void setOutlets(OutLets outlets) {
        this.outlets = outlets;
    }

    public ConcurrentHashMap<String, Integer> getTotal_items_quantity() {
        return total_items_quantity;
    }

    public void setTotal_items_quantity(ConcurrentHashMap<String, Integer> total_items_quantity) {
        this.total_items_quantity = total_items_quantity;
    }

    public Map<String, Map<String, Integer>> getBeverages() {
        return beverages;
    }

    public void setBeverages(Map<String, Map<String, Integer>> beverages) {
        this.beverages = beverages;
    }
}
