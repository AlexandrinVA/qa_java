package com.example;

import java.util.List;

public class Lion {

    private final boolean hasMane;
    private final KittensProvider kittensProvider;
    private final Predator predator;

    public Lion(String sex, KittensProvider kittensProvider, Predator predator) throws Exception {
        if ("Самец".equals(sex)) {
            this.hasMane = true;
        } else if ("Самка".equals(sex)) {
            this.hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.kittensProvider = kittensProvider;
        this.predator = predator;
    }

    public int getKittens() {
        return kittensProvider.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}
