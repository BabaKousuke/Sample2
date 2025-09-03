# 🏮 魔改造テキストアドベンチャー (Magical Adventure Game)

A creative Japanese-themed text-based adventure game - a "magical modification" (魔改造) of a simple repository!

## 🎮 ゲームについて (About the Game)

This is a text-based adventure game written in Java featuring:
- Japanese text and atmosphere
- Turn-based combat system
- Item collection and usage
- Multiple rooms to explore
- Epic quest to defeat the final boss (魔王)

## 🚀 実行方法 (How to Run)

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Building and Running
```bash
# Build the project
mvn clean compile

# Run the game
mvn exec:java

# Or run with Maven's exec plugin
mvn exec:java -Dexec.mainClass="com.example.game.MagicalAdventure"

# Run tests
mvn test
```

## 🎯 ゲームプレイ (Gameplay)

### Commands (コマンド)
- `look` / `l` - Look around the current room
- `go <direction>` / `g <direction>` - Move in a direction (north, south, east, west)
- `take <item>` / `t <item>` - Pick up an item
- `use <item>` / `u <item>` - Use an item
- `fight` / `f` - Fight enemies in the current room
- `inventory` / `i` - Check your inventory
- `help` / `h` - Show help
- `quit` / `q` - Exit the game

### Game World
- **村 (Village)** - Starting point, peaceful village
- **魔法の森 (Magic Forest)** - Mysterious forest with goblins
- **洞窟 (Cave)** - Dark cave with orcs and treasure
- **魔王の城 (Demon Lord's Castle)** - Final boss location

### Items
- **魔法の剣 (Magic Sword)** - Increases attack power by 10
- **体力ポーション (Health Potion)** - Restores 30 HP
- **パン (Bread)** - Restores 5 HP

### Enemies
- **ゴブリン (Goblin)** - Weak enemy in the forest
- **オーク (Orc)** - Stronger enemy in the cave
- **魔王 (Demon Lord)** - Final boss in the castle

## 🏗️ プロジェクト構造 (Project Structure)

```
src/
├── main/java/com/example/game/
│   ├── MagicalAdventure.java  # Main game class
│   ├── Player.java            # Player character
│   ├── Enemy.java             # Enemy entities
│   ├── Item.java              # Game items
│   └── Room.java              # Game locations
└── test/java/com/example/game/
    └── MagicalAdventureTest.java # Unit tests
```

## 🌟 Features (特徴)

1. **Japanese Localization** - Full Japanese text with emojis for visual appeal
2. **Object-Oriented Design** - Clean separation of concerns with dedicated classes
3. **Combat System** - Turn-based fighting with damage calculation
4. **Inventory Management** - Collect and use items strategically
5. **Room Exploration** - Navigate through connected rooms
6. **Win Condition** - Defeat the final boss to win the game

## 🧪 Testing

The project includes comprehensive unit tests covering:
- Player creation and stats
- Damage and healing mechanics
- Item usage and effects
- Enemy behavior
- Room connections and item management

Run tests with: `mvn test`

## 🔮 魔改造ポイント (Magical Modifications)

This started as an empty repository with just a .gitignore file and transformed into:
- A complete Java application with Maven build system
- Interactive gameplay with Japanese localization
- Object-oriented design with proper separation of concerns
- Comprehensive test coverage
- Rich user experience with emojis and formatted output

## 📝 License

This project is created as a demonstration of "魔改造" (magical modification) and is available for educational purposes.

---

**楽しいゲームライフを！(Enjoy your gaming life!)**