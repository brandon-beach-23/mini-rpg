package com.brandonbeach.mini_rpg.enemy.domain;

import com.brandonbeach.mini_rpg.shared.domain.Stats;
import com.brandonbeach.mini_rpg.shared.domain.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy {
    private String enemyName;
    private EnemyType enemyType;
    private Stats stats;
    private int hitPoints;
    private Status status;

    public Enemy(String enemyName, EnemyType enemyType) {
        validateEnemyName(enemyName);
        this.enemyName = enemyName;
        this.enemyType = enemyType;
        this.stats = new Stats(
                enemyType.getBaseDamage(),
                enemyType.getBaseVitality(),
                enemyType.getBaseCritChance(),
                enemyType.getBaseSpeed(),
                enemyType.getBaseAbilityDamage(),
                enemyType.getBaseCritDamageMultiplier()
        );
        this.status = Status.HEALTHY;
        this.hitPoints = stats.getVitality() * 10;
    }

    private void validateEnemyName(String enemyName) {
        if (enemyName == null || enemyName.isEmpty()) {
            throw new IllegalArgumentException("Enemy name cannot be null or empty");
        }
        if (enemyName.length() > 25) {
            throw new IllegalArgumentException("Enemy name cannot exceed 25 characters");
        }
        if (!enemyName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Enemy name must contain only letters (A–Z, a–z)");
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
            throw new IllegalArgumentException("Cannot apply status effects to a dead enemy");
        }
        if (effect == Status.DEAD) {
            throw new IllegalArgumentException("Use takeDamage() to kill an enemy, not applyStatusEffect()");
        }
        this.status = effect;
    }

    public void cureCondition() {
        if (this.status == Status.DEAD) {
            throw new IllegalArgumentException("Cannot cure a dead enemy");
        }
        this.status = Status.HEALTHY;
    }

    public boolean isAlive() {
        return this.hitPoints > 0 && this.status != Status.DEAD;
    }
}
