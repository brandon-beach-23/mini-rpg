package com.brandonbeach.mini_rpg.battle.domain;

import com.brandonbeach.mini_rpg.shared.domain.Status;
import lombok.Getter;

import java.util.Optional;

@Getter
public class TurnResult {
    private final CombatAction action;
    private final int damageDealt;
    private final boolean isCritical;
    private final Optional<Status> statusApplied;
    private final int targetRemainingHP;
    private final String logMessage;

    // Constructor with status
    public TurnResult(CombatAction action, int damageDealt, boolean isCritical,
                      Status statusApplied, int targetRemainingHP, String logMessage) {
        this.action = action;
        this.damageDealt = damageDealt;
        this.isCritical = isCritical;
        this.statusApplied = Optional.ofNullable(statusApplied);
        this.targetRemainingHP = targetRemainingHP;
        this.logMessage = logMessage;
    }

    // Constructor without status (delegates to the above)
    public TurnResult(CombatAction action, int damageDealt, boolean isCritical,
                      int targetRemainingHP, String logMessage) {
        this(action, damageDealt, isCritical, null, targetRemainingHP, logMessage);
    }
}

