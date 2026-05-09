package com.brandonbeach.mini_rpg.character.domain;

import com.brandonbeach.mini_rpg.shared.domain.Attributes;
import com.brandonbeach.mini_rpg.shared.domain.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    @Test
    public void testCharacterCreation_ValidInputs(){
        //Arrange
        String characterName = "Aethan";
        CharacterClass characterClass = CharacterClass.WARRIOR;
        Attributes attributes = new Attributes(5, 3, 2);
        //Act
        Character newChar = new Character(characterName, characterClass, attributes);

        //Assert
        assertNotNull(newChar);
        assertEquals("Aethan", newChar.getCharacterName());
        assertEquals(CharacterClass.WARRIOR, newChar.getCharacterClass());
        assertNotNull(newChar.getStats());
        assertTrue(newChar.getHitPoints() > 0);
    }

    @Test
    public void testCharacterCreation_InvalidNameTooLong() {
        // Arrange
        String characterName = "ThisNameIsWayTooLongAndExceeds25Characters";
        CharacterClass characterClass = CharacterClass.WARRIOR;
        Attributes attributes = new Attributes(5, 3, 2);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Character(characterName, characterClass, attributes);
        });
    }

    @Test
    public void testCharacterCreation_InvalidNameNonAlpha() {
        // Arrange
        String characterName = "Aethan123";
        CharacterClass characterClass = CharacterClass.WARRIOR;
        Attributes attributes = new Attributes(5, 3, 2);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Character(characterName, characterClass, attributes);
        });
    }

    @Test
    public void testTakeDamage_ReduceHitPoints(){
        //Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5,3,2));
        int initialHitPoints = character.getHitPoints();
        int damageAmount = 5;
        //Act
        character.takeDamage(damageAmount);
        //Assert
        assertEquals(initialHitPoints - damageAmount, character.getHitPoints());
        assertEquals(Status.HEALTHY, character.getStatus());
        assertTrue(character.isAlive());
    }

    @Test
    public void testTakeDamage_SetStatusToDeadWhenHitPointsReachZero(){
        //Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5,3,2));
        int currentHitPoints = 5;
        character.setHitPoints(currentHitPoints);
        int damageAmount = 5;
        //Act
        character.takeDamage(damageAmount);
        //Assert
        assertEquals(0, character.getHitPoints());
        assertEquals(Status.DEAD, character.getStatus());
        assertFalse(character.isAlive());
    }

    @Test
    public void testApplyStatusEffect_ChangesCharacterStatus(){
        //Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5,3,2));
        //Act
        character.applyStatusEffect(Status.BURNING);
        //Assert
        assertEquals(Status.BURNING, character.getStatus());
    }

    @Test
    public void testApplyStatusEffect_ThrowsExceptionWhenCharacterIsDead() {
        // Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5, 3, 2));
        character.takeDamage(character.getHitPoints());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            character.applyStatusEffect(Status.POISONED);
        });
    }

    @Test
    public void testCureCondition_ResetsStatusToHealthy() {
        //Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5,3,2));
        character.setStatus(Status.BURNING);
        //Act
        character.cureCondition();
        //Assert
        assertEquals(Status.HEALTHY, character.getStatus());
    }

    @Test
    public void testCureCondition_DoesNothingWhenCharacterIsDead() {
        // Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5, 3, 2));
        character.setStatus(Status.BURNING);
        character.takeDamage(character.getHitPoints());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            character.cureCondition();
        });
    }

    @Test
    public void testIsAlive_ReturnsTrueWhenHealthy(){
        //Arrange
        Character character = new Character("Aethan", CharacterClass.WARRIOR, new Attributes(5,3,2));
        //Act & Assert
        assertTrue(character.isAlive());
    }
}
