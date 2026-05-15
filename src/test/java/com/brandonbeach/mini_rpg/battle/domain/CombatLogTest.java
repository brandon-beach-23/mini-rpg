package com.brandonbeach.mini_rpg.battle.domain;

import com.brandonbeach.mini_rpg.shared.domain.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatLogTest {

    @Test
    public void testCombatLogCreation_Empty() {
        // Arrange & Act
        CombatLog log = new CombatLog();

        // Assert
        assertEquals(0, log.getTurnCount());
        assertEquals(0, log.getAllTurnResults().size());
    }

    @Test
    public void testCombatLogAddTurnResult() {
        // Arrange
        CombatLog log = new CombatLog();
        CombatAction action = new CombatAction(
                CombatAction.ActionType.ATTACK,
                "Aethan",
                "Voldemort",
                "Aethan attacks"
        );
        TurnResult result = new TurnResult(action, 25, false, 125, "Aethan hit for 25 damage");

        // Act
        log.addTurnResult(result);

        // Assert
        assertEquals(1, log.getTurnCount());
        assertEquals(1, log.getAllTurnResults().size());
        assertEquals(result, log.getAllTurnResults().get(0));
    }

    @Test
    public void testCombatLogMultipleTurns() {
        // Arrange
        CombatLog log = new CombatLog();
        CombatAction action1 = new CombatAction(CombatAction.ActionType.ATTACK, "Aethan", "Voldemort", "Attack 1");
        CombatAction action2 = new CombatAction(CombatAction.ActionType.DEFEND, "Voldemort", "Aethan", "Defend");
        TurnResult result1 = new TurnResult(action1, 25, false, 125, "Aethan hit for 25");
        TurnResult result2 = new TurnResult(action2, 0, false, 150, "Voldemort defended");

        // Act
        log.addTurnResult(result1);
        log.addTurnResult(result2);

        // Assert
        assertEquals(2, log.getTurnCount());
        assertEquals(2, log.getAllTurnResults().size());
    }

    @Test
    public void testCombatLogImmutability() {
        // Arrange
        CombatLog log = new CombatLog();
        CombatAction action = new CombatAction(CombatAction.ActionType.ATTACK, "Aethan", "Voldemort", "Attack");
        TurnResult result = new TurnResult(action, 25, false, 125, "Hit");
        log.addTurnResult(result);

        // Act & Assert
        // Verify that getAllTurnResults() returns an unmodifiable list
        assertThrows(UnsupportedOperationException.class, () -> {
            log.getAllTurnResults().add(new TurnResult(action, 10, false, 100, "Another hit"));
        });
    }
}

