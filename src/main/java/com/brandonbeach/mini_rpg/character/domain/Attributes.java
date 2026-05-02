package com.brandonbeach.mini_rpg.character.domain;

import lombok.Value;

@Value
public class Attributes {
    int strength;
    int dexterity;
    int intelligence;

    public Attributes(int strength, int dexterity, int intelligence) {
        int totalPoints = strength + dexterity + intelligence;
        if  (totalPoints != 10) {
            throw new  IllegalArgumentException("Total attribute points should be 10");
        }

        if (strength < 0 || dexterity < 0 || intelligence < 0) {
            throw new  IllegalArgumentException("Strength or dexterity or intelligence cannot be negative");
        }

        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }


}


