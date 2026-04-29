# RPG Character Builder and Battle Simulator

---
A pre-planning document to establish a baseline vision of the
application and how the base systems behave on an individual level.
This document is subject to change and will live in the projects repository with
version numbers applied to the bottom of the document. (E.g. Version 1.0.0)

1. Character System

    -What is the core purpose of a character?
    -What data defines a character?
    -What operations can be performed on a character?
    -What rules must a character follow?

2. Battle System
     -Can a character battle another character?
     -What determines the outcome?
     -Is the battle replayable or one-time?

3. Stat System
     -What stats exist?
     -How many points do players allocate at creation?
     -Are there min/max limits?

4. Enemy System
     -Are enemies pre-determined or generated?
     -Can you fight multiple enimies or one at a time?
     -Do enemies scale with player progression?

5. Tech Stack
     -Java, Spring Boot, Angular, WebSocket, possibly REST APIs.

---
## Character System
The main purpose of a character is to provide a user with a limited customizable avatar that will be
tested in a battle simulator against various enemies.

### Character Data:
---
-Name
-Class
-Stats
-Status
-HitPoints
-ResourcePoints(Mana/Stamina)
-Inventory
-AllocatedAttributes


### Character Operations:
---
-createCharacter
-applyAttribute
-takeDamage
-mitigateDamage
-useItem
-applyStatueEffect
-cureStatusEffect
-useSkill
-useAttack

### Character Rules:
-They must have a name consisting of A-Z a-z, no symbols or numbers with a 25 character maximum.
-Hit points cannot be negative.
-They must be able to take damage.
-They must be able to damage opponents.
-Stats must update based on allocated attributes.

## Battle System
The battle system will simulate battle between a character and enemy. Two characters cannot battle each other.
The outcome will be determined by a turn base combat that will use input from the user such as attack, block, use skill, use item.
The enemy will also be granted turns in the battle to use the same choices but they are determined by the system.
The player can choose to replay a battle as many times as they wish. Damage from battle actions will be calculated
based on the hit, damage mitigation, critical hit, status effect.

## Stat System
The main purpose of a character is to provide a user with a limited customizable avatar that will be
tested in a battle simulator against various enemies.

### Available Stats:
---
-Strength
-Dexterity
-Intelligence
-Armor
-Vitality

A user will be able to apply 10 attributes upon character creation without limits on any given stat. A character will not
level up or acquire more attribute points after character creation.

## Enemy System
Enemies will be predefined. There will be 5 battles the user will be able to simulate with varying difficulty for the user.
Some battles will include multiple weaker enemies and others will be harder solo enemies. There will not be any player progression
so the enemies will not scale to harder difficulties.

## Tech Stack
This project will use Java and Spring Boot for the backend of the project. Angular will be used for the frontend. The database will be
MySQL for production(H2 in memory for development). Communication from the backend to the frontend will be completed via REST APIs that I design.

**Version 1.0.0**

