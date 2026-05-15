package com.brandonbeach.mini_rpg.battle.domain;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class CombatAction {
    public enum ActionType {
        ATTACK,
        DEFEND,
        CURE_CONDITION,
        USE_ITEM,
        USE_SKILL
    }

    ActionType actionType;
    String actorName;
    String targetName;
    String description;

    public CombatAction(ActionType actionType, String actorName, String targetName, String description) {
        if (actionType == null) {
            throw new IllegalArgumentException("Action type cannot be null");
        }
        if (actorName == null || actorName.isEmpty()) {
            throw new IllegalArgumentException("Actor name cannot be null or empty");
        }
        if (targetName == null || targetName.isEmpty()) {
            throw new IllegalArgumentException("Target name cannot be null or empty");
        }
        this.actionType = actionType;
        this.actorName = actorName;
        this.targetName = targetName;
        this.description = description;
    }
}
