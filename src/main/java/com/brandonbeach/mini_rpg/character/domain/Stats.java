package com.brandonbeach.mini_rpg.character.domain;

import lombok.Value;

@Value
public class Stats {
    int damage;
    int vitality;
    double critChance;
    int speed;
    int abilityDamage;
    double critDamageMultiplier;
}
