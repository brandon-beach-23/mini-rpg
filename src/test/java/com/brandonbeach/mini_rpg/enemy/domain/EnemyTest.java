package com.brandonbeach.mini_rpg.enemy.domain;

import com.brandonbeach.mini_rpg.shared.domain.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void testEnemyCreation_ValidInputs(){
        //Arrange
        String enemyName = "Voldemort";
        EnemyType enemyType = EnemyType.GOBLIN;
        //Act
        Enemy newEnemy = new Enemy(enemyName, enemyType);
        //Assert
        assertNotNull(newEnemy);
        assertEquals("Voldemort", newEnemy.getEnemyName());
        assertEquals(EnemyType.GOBLIN, newEnemy.getEnemyType());
        assertNotNull(newEnemy.getStats());
        assertTrue(newEnemy.getHitPoints() > 0);
    }

    @Test
    public void testEnemyCreation_InvalidNameTooLong(){
        //Arrange
        String enemyName = "ThisNameIsWayTooLongAndExceeds25Characters";
        EnemyType enemyType = EnemyType.TROLL;
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Enemy(enemyName, enemyType);
        });
    }

    @Test
    public void testEnemyCreation_InvalidNameNonAlpha(){
        //Arrange
        String enemyName = "Voldemort123";
        EnemyType enemyType = EnemyType.TROLL;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Enemy(enemyName, enemyType);
        });
    }

    @Test
    public void testTakeDamage_ReduceHitPoints() {
        //Arrange
        Enemy enemy =  new Enemy("Voldemort", EnemyType.TROLL);
        int initialHitpoints = enemy.getHitPoints();
        int damageAmount = 5;
        //Act
        enemy.takeDamage(damageAmount);
        //Assert
        assertEquals(initialHitpoints - damageAmount, enemy.getHitPoints());
        assertEquals(Status.HEALTHY, enemy.getStatus());
        assertTrue(enemy.isAlive());
    }

    @Test
    public void testTakeDamage_SetStatusToDeadWhenHitPointsReachZero() {
        //Arrange
        Enemy enemy = new Enemy("Voldemort", EnemyType.TROLL);
        //Act
        enemy.takeDamage(enemy.getHitPoints());
        //Assert
        assertEquals(0, enemy.getHitPoints());
        assertEquals(Status.DEAD, enemy.getStatus());
        assertFalse(enemy.isAlive());
    }

    @Test
    public void testApplyStatusEffect_ChangesEnemyStatus(){
        //Arrange
        Enemy enemy = new Enemy("Voldemort", EnemyType.TROLL);
        //Act
        enemy.applyStatusEffect(Status.BURNING);
        //Assert
        assertEquals(Status.BURNING, enemy.getStatus());
    }

    @Test
    public void testCureCondition_ResetsStatusToHealthy(){
        //Arrange
        Enemy enemy = new Enemy("Voldemort", EnemyType.TROLL);
        enemy.setStatus(Status.BURNING);
        //Act
        enemy.cureCondition();
        //Assert
        assertEquals(Status.HEALTHY, enemy.getStatus());
    }

    @Test
    public void testCureCondition_ThrowsExceptionWhenEnemyIsDead() {
        // Arrange
        Enemy enemy = new Enemy("Voldemort", EnemyType.TROLL);
        enemy.takeDamage(enemy.getHitPoints());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            enemy.cureCondition();
        });
    }

    @Test
    public void testIsAlive_ReturnsTrueWhenHealthy(){
        //Arrange
        Enemy enemy = new Enemy("Voldemort", EnemyType.TROLL);
        //Act & Assert
        assertTrue(enemy.isAlive());
    }
}
