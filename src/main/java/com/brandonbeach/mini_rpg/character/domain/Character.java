package com.brandonbeach.mini_rpg.character.domain;

import com.brandonbeach.mini_rpg.shared.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
public class Character extends BaseEntity {

    private String characterName;
    private CharacterClass characterClass;
    private Stats stats;
    private Attributes attributes;
    private int hitPoints;

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


}
