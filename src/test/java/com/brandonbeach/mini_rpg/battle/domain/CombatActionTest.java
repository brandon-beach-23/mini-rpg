package com.brandonbeach.mini_rpg.battle.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatActionTest {

    @Test
    public void testCombatActionCreation_ValidInputs() {
        // Arrange
        CombatAction.ActionType actionType = CombatAction.ActionType.ATTACK;
        String actorName = "Aethan";
        String targetName = "Voldemort";
        String description = "Aethan attacks Voldemort";

        // Act
        CombatAction action = new CombatAction(actionType, actorName, targetName, description);

        // Assert
        assertEquals(CombatAction.ActionType.ATTACK, action.getActionType());
        assertEquals("Aethan", action.getActorName());
        assertEquals("Voldemort", action.getTargetName());
        assertEquals("Aethan attacks Voldemort", action.getDescription());
    }

    @Test
    public void testCombatActionCreation_NullActionType() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatAction(null, "Aethan", "Voldemort", "Description");
        });
    }

    @Test
    public void testCombatActionCreation_NullActorName() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatAction(CombatAction.ActionType.ATTACK, null, "Voldemort", "Description");
        });
    }

    @Test
    public void testCombatActionCreation_NullTargetName() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatAction(CombatAction.ActionType.ATTACK, "Aethan", null, "Description");
        });
    }
}

