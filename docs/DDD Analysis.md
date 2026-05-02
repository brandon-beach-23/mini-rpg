## Character Builder Context
The system that is responsible for creating a character based on user input.
- Character Name
- Class
- Inventory
- Stats
- Attributes
- Status

After the character is created data will be available on the UI regarding the character and the data will be exported via DTO to display on the UI and import into other contexts as needed.
## Battle Context
The system that is responsible for simulating battle scenarios between a character and enemy.
- Combatant
- Turn
- Combat Log
## Enemy Context
The system responsible for generating enemies for characters to battle.
- EnemyName
- EnemyTemplate
- EnemyInstance
- Stats
- Inventory
- Status
- Attributes

## Aggregates (Entities that belong together)
**Character Aggregate**
- Character (root entity)
- Stats
- Inventory
- Attributes
- Status
- **Battle Aggregate**
- Battle (root entity)
- CharacterCombatant
- EnemyCombatant
- Turn - CombatLog
- **Enemy Aggregate**
- Enemy (root entity)
- EnemyStats
- Inventory

## Design Patterns

### Builder Pattern
Used for: Character creation with flexible attribute allocation
Why: Allows clean construction of complex Character aggregate

### Strategy Pattern
Used for: Class-based damage calculation
Why: Each class (Warrior, Rogue, Mage) calculates damage differently

### State Pattern
Used for: Character status effects and battle turn states
Why: Encapsulates different behavior based on current state

### Template Method Pattern
Used for: Enemy AI decision-making
Why: Each enemy type has a different combat strategy
## Value Objects
Used for: Stats, Damage, etc
Why: Used to ensure deterministic values for battles.


## Package Structure
src/main/java/com/brandonbeach/mini_rpg/
├── character/
│ ├── domain/
│ │ ├── Character.java (aggregate root)
│ │ ├── CharacterClass.java (enum)
│ │ ├── Stats.java (value object)
│ │ └── Attributes.java (value object)
│ ├── application/
│ │ └── CreateCharacterService.java
│ ├── infrastructure/
│ │ └── CharacterRepository.java
│ └── presentation/
│ └── CharacterController.java
├── battle/
│ ├── domain/
│ │ ├── Battle.java (aggregate root)
│ │ ├── Turn.java (entity)
│ │ ├── CombatLog.java (value object)
│ │ └── CombatAction.java (enum)
│ ├── application/
│ │ └── BattleService.java
│ └── presentation/
│ └── BattleController.java
├── enemy/
│ ├── domain/
│ │ ├── Enemy.java (aggregate root)
│ │ ├── EnemyTemplate.java (value object)
│ │ └── EnemyAI.java (strategy)
│ ├── infrastructure/
│ │ └── EnemyRepository.java
│ └── presentation/
│ └── EnemyController.java


## Stat System Design

### STRENGTH
- Increases: Damage, Vitality
- Warrior: +3 damage, +2 vitality per point
- Rogue: +2 damage, +1 vitality per point
- Mage: +1 damage, +2 vitality per point

### DEXTERITY
- Increases: Critical Strike Chance, Speed
- Warrior: +0.5% crit chance, +1 speed per point
- Rogue: +2% crit chance, 3 speed per point
- Mage: +1% crit chance, 1 speed per point

### INTELLIGENCE
- Increases: Ability Damage, Critical Damage
- Warrior: +2 Ability Damage, +2 Critical Damage
- Rogue: +1 Ability Damage, +3 Critical Damage
- Mage: +3 Ability Damage, +2 Critical Damage


## Database Schema

### Character Table
- id (PK)
- name (VARCHAR 25)
- class (ENUM: Warrior, Rogue, Mage)
- created_at (TIMESTAMP)

### CharacterStats Table
- id (PK)
- character_id (FK)
- strength (INT)
- dexterity (INT)
- intelligence (INT)
- armor (INT)
- vitality (INT)
- speed (INT)

### CharacterInventory Table
- id (PK)
- character_id (FK)
- weapon (VARCHAR) - equipped
- armor (VARCHAR) - equipped
- health_potions (INT)

### Battle Table
- id (PK)
- character_id (FK)
- enemy_id (FK)
- battle_result (ENUM: Win, Loss, InProgress)
- created_at (TIMESTAMP)

### Turn Table
- id (PK)
- battle_id (FK)
- turn_number (INT)
- combatant_type (ENUM: Character, Enemy)
- action_taken (VARCHAR)
- damage_dealt (INT)

### Enemy Table
- id (PK)
- enemy_type (VARCHAR) - template reference
- name (VARCHAR)
- strength (INT)
- dexterity (INT)
- intelligence (INT)
- armor (INT)
- vitality (INT)
- speed (INT)


## REST API Endpoints

### Character Management
- `POST /api/characters` - Create new character
    - Request: { name, class, attributes: { strength, dexterity, ... } }
    - Response: { characterId, stats, inventory }

- `GET /api/characters/{id}` - Get character details

### Battle System
- `POST /api/battles` - Start new battle
    - Request: { characterId, enemyId }
    - Response: { battleId, initialState }

- `POST /api/battles/{battleId}/action` - Player takes action
    - Request: { action: "Attack|Block|UseSkill|UsePotion", target }
    - Response: { turnResult, combatLog, battleStatus }

- `GET /api/battles/{battleId}` - Get battle state and log

### Enemy System
- `GET /api/enemies` - Get all available enemies (5 battles)
    - Response: [ { id, name, type, difficulty }, ... ]

## Implementation Strategy

### Phase 1: Foundation (Core Entities)
1. Create domain entities (Character, Enemy, Battle)
2. Implement value objects (Stats, Attributes, Damage)
3. Create repositories and database schema
4. Write unit tests for domain logic

### Phase 2: Business Logic
1. Implement damage calculation (Strategy pattern)
2. Implement status effects (State pattern)
3. Implement character creation (Builder pattern)
4. Write integration tests

### Phase 3: API Layer
1. Create REST controllers
2. Create DTOs for data transfer
3. Implement error handling
4. Test endpoints with REST client

### Phase 4: Frontend
1. Character creation UI
2. Battle simulator UI with combat log
3. Enemy selection UI
4. Results and statistics

### Phase 5: Polish & Testing
1. Add comprehensive unit tests (target 80% coverage)
2. Integration tests for API endpoints
3. Refactor for code quality
4. Performance optimization

## Refactoring Strategy

### Code Quality Principles
- **Single Responsibility:** Each class has one reason to change
- **DRY (Don't Repeat Yourself):** Reuse calculation logic
- **SOLID Principles:** Already embedded in architecture

### Planned Refactoring Points
1. **Damage Calculation:** Extract to separate service to avoid duplication
2. **Status Effect Logic:** Keep in State pattern, avoid switch statements
3. **Enemy AI:** Use Template Method to avoid code duplication between enemy types
4. **Battle State:** Use State pattern to avoid large conditional blocks

### Code Smells to Avoid
- Feature Envy (classes knowing too much about others)
- Divergent Change (modifying multiple places for one reason)
- Long Methods (keep methods focused and small)
- Magic Numbers (use named constants)
