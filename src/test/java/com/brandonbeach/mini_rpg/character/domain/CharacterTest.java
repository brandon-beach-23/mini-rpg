package com.brandonbeach.mini_rpg.character.domain;

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
}
