package com.brandonbeach.mini_rpg.character.domain;

import com.brandonbeach.mini_rpg.shared.domain.Attributes;
import com.brandonbeach.mini_rpg.shared.domain.BaseEntity;
import com.brandonbeach.mini_rpg.shared.domain.Stats;
import com.brandonbeach.mini_rpg.shared.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Character extends BaseEntity {

    private String characterName;
    private CharacterClass characterClass;
    private Stats stats;
    private Attributes attributes;
    private int hitPoints;
    private Status status = Status.HEALTHY;

    public Character(String characterName, CharacterClass characterClass, Attributes attributes) {
        validateCharacterName(characterName);
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.attributes = attributes;
        this.stats = characterClass.calculateStats(attributes);
        this.hitPoints = stats.getVitality() * 10;
    }

    private void validateCharacterName(String characterName) {
        if (characterName == null || characterName.isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be null or empty");
        }
        if (characterName.length() > 25) {
            throw new IllegalArgumentException("Character name cannot exceed 25 characters");
        }
        if (!characterName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Character name must contain only letters (A–Z, a–z)");
        }
    }

    public void takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints <= 0) {
            this.hitPoints = 0;
            this.status = Status.DEAD;
        }
    }

        public void applyStatusEffect(Status effect) {
            if (this.status == Status.DEAD) {
                throw new IllegalArgumentException("Cannot apply status effects to a dead character");
            }
            if (effect == Status.DEAD) {
                throw new IllegalArgumentException("Use takeDamage() to kill a character, not applyStatusEffect()");
            }
            this.status = effect;
        }

        public void cureCondition() {
            if (this.status == Status.DEAD) {
                throw new IllegalArgumentException("Cannot cure a dead character");
            }
            this.status = Status.HEALTHY;
        }

        public boolean isAlive() {
            return this.hitPoints > 0 && this.status != Status.DEAD;
        }




}
