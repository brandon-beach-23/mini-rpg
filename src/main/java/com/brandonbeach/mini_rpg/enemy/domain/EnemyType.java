package com.brandonbeach.mini_rpg.enemy.domain;

import lombok.Getter;

@Getter
public enum EnemyType {
    GOBLIN(5, 8, 6, 1.5, 3, 1.2),      // low HP, medium dmg, high speed
    ORC(12, 10, 3, 2, 2, 1.5),       // high HP/defense, medium dmg, low speed
    TROLL(15, 14, 2, 1, 1, 1.8);     // highest HP/dmg, low speed, high crit multiplier

    private final int baseDamage;
    private final int baseVitality;
    private final int baseSpeed;
    private final double baseCritChance;
    private final int baseAbilityDamage;
    private final double baseCritDamageMultiplier;

    EnemyType(int baseDamage, int baseVitality, int baseSpeed,
              double baseCritChance, int baseAbilityDamage, double baseCritDamageMultiplier) {
        this.baseDamage = baseDamage;
        this.baseVitality = baseVitality;
        this.baseSpeed = baseSpeed;
        this.baseCritChance = baseCritChance;
        this.baseAbilityDamage = baseAbilityDamage;
        this.baseCritDamageMultiplier = baseCritDamageMultiplier;
    }
}