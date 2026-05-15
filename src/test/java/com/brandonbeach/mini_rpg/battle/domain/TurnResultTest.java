package com.brandonbeach.mini_rpg.battle.domain;

import com.brandonbeach.mini_rpg.shared.domain.Status;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TurnResultTest {

    @Test
    public void testTurnResultCreation_WithStatus() {
        // Arrange
        CombatAction action = new CombatAction(
                CombatAction.ActionType.ATTACK,
                "Aethan",
                "Voldemort",
                "Aethan attacks Voldemort"
        );
        int damageDealt = 25;
        boolean isCritical = false;
        Status statusApplied = Status.BURNING;
        int targetRemainingHP = 125;
        String logMessage = "Aethan hit Voldemort for 25 damage and applied BURNING";

        // Act
        TurnResult result = new TurnResult(action, damageDealt, isCritical, statusApplied, targetRemainingHP, logMessage);

        // Assert
        assertEquals(action, result.getAction());
        assertEquals(25, result.getDamageDealt());
        assertFalse(result.isCritical());
        assertTrue(result.getStatusApplied().isPresent());
        assertEquals(Status.BURNING, result.getStatusApplied().get());
        assertEquals(125, result.getTargetRemainingHP());
        assertEquals(logMessage, result.getLogMessage());
    }

    @Test
    public void testTurnResultCreation_WithoutStatus() {
        // Arrange
        CombatAction action = new CombatAction(
                CombatAction.ActionType.ATTACK,
                "Aethan",
                "Voldemort",
                "Aethan attacks Voldemort"
        );
        int damageDealt = 30;
        boolean isCritical = true;
        int targetRemainingHP = 120;
        String logMessage = "Aethan critically hit Voldemort for 30 damage";

        // Act
        TurnResult result = new TurnResult(action, damageDealt, isCritical, targetRemainingHP, logMessage);

        // Assert
        assertEquals(action, result.getAction());
        assertEquals(30, result.getDamageDealt());
        assertTrue(result.isCritical());
        assertFalse(result.getStatusApplied().isPresent());
        assertEquals(120, result.getTargetRemainingHP());
    }
}
