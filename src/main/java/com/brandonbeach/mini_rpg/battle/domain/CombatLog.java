package com.brandonbeach.mini_rpg.battle.domain;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
public class CombatLog {
    List<TurnResult> turnResults;

    public CombatLog() {
        this.turnResults = new ArrayList<>();
    }

    public CombatLog(List<TurnResult> turnResults) {
        this.turnResults = turnResults;
    }

    public void addTurnResult(TurnResult turnResult) {
        this.turnResults.add(turnResult);
    }

    public List<TurnResult> getAllTurnResults() {
        return Collections.unmodifiableList(turnResults);
    }

    public int getTurnCount() {
        return turnResults.size();
    }
}
