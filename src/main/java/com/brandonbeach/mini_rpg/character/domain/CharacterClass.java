package com.brandonbeach.mini_rpg.character.domain;


import lombok.Getter;

@Getter
public enum CharacterClass {
    WARRIOR(
            10,
            15,
            3,
            1.5,
            2,
            1.5
    ) {
        @Override
        public Stats calculateStats(Attributes attributes) {
            int finalDamage = getBaseDamage() + (attributes.getStrength() * 3);
            int finalVitality = getBaseVitality() + (attributes.getStrength() * 2);
            double finalCritChance = getBaseCritChance() + (attributes.getDexterity() * 0.5);
            int finalSpeed = getBaseSpeed() + (attributes.getDexterity() * 1);
            int finalAbilityDamage = getBaseAbilityDamage() + (attributes.getIntelligence() * 2);
            double finalCritDamageMultiplier = getBaseCritDamageMultiplier() + (attributes.getIntelligence() * 2);
            return new Stats(finalDamage, finalVitality, finalCritChance, finalSpeed, finalAbilityDamage,finalCritDamageMultiplier);
        }
    },
    ROGUE(
            8,
            12,
            5,
            3.0,
            4,
            1.5
    ){
        @Override
        public Stats calculateStats(Attributes attributes) {
            int finalDamage = getBaseDamage() + (attributes.getStrength() * 2);
            int finalVitality = getBaseVitality() + (attributes.getStrength() * 1);
            double finalCritChance = getBaseCritChance() + (attributes.getDexterity() * 2.0);
            int finalSpeed = getBaseSpeed() + (attributes.getDexterity() * 3);
            int finalAbilityDamage = getBaseAbilityDamage() + (attributes.getIntelligence() * 1);
            double finalCritDamageMultiplier = getBaseCritDamageMultiplier() + (attributes.getIntelligence() * 3);
            return new Stats(finalDamage, finalVitality, finalCritChance, finalSpeed, finalAbilityDamage,finalCritDamageMultiplier);
        }
    },
    MAGE(
            5,
            10,
            4,
            2.0,
            8,
            1.5
    ){
        @Override
        public Stats calculateStats(Attributes attributes) {
            int finalDamage = getBaseDamage() + (attributes.getStrength() * 1);
            int finalVitality = getBaseVitality() + (attributes.getStrength() * 2);
            double finalCritChance = getBaseCritChance() + (attributes.getDexterity() * 1.0);
            int finalSpeed = getBaseSpeed() + (attributes.getDexterity() * 1);
            int finalAbilityDamage = getBaseAbilityDamage() + (attributes.getIntelligence() * 3);
            double finalCritDamageMultiplier = getBaseCritDamageMultiplier() + (attributes.getIntelligence() * 2);
            return new Stats(finalDamage, finalVitality, finalCritChance, finalSpeed, finalAbilityDamage,finalCritDamageMultiplier);
        }
    };

    private final int baseDamage;
    private final int baseVitality;
    private final int baseSpeed;
    private final double baseCritChance;
    private final int baseAbilityDamage;
    private final double baseCritDamageMultiplier;

    CharacterClass(int baseDamage, int baseVitality, int baseSpeed,
                   double critChance, int baseAbilityDamage, double baseCritDamageMultiplier) {
        this.baseDamage = baseDamage;
        this.baseVitality = baseVitality;
        this.baseSpeed = baseSpeed;
        this.baseCritChance = critChance;
        this.baseAbilityDamage = baseAbilityDamage;
        this.baseCritDamageMultiplier = baseCritDamageMultiplier;

    }

    public abstract Stats calculateStats(Attributes attributes);
}