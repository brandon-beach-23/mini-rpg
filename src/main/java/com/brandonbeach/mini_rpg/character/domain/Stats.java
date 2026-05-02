package com.brandonbeach.mini_rpg.character.domain;


import lombok.Value;

@Value
public class Stats {

    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int vitality;
    private final int speed;
    private final int defense;

}
