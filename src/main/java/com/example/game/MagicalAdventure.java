package com.example.game;

import java.util.*;

/**
 * 魔改造版テキストアドベンチャーゲーム
 * A magical text-based adventure game with Japanese flavor!
 */
public class MagicalAdventure {
    
    private Scanner scanner;
    private Player player;
    private Room currentRoom;
    private Map<String, Room> rooms;
    private boolean gameRunning;
    private Random random;
    
    public MagicalAdventure() {
        scanner = new Scanner(System.in);
        random = new Random();
        gameRunning = true;
        initializeGame();
    }
    
    private void initializeGame() {
        // Initialize player
        player = new Player("勇者", 100, 20);
        
        // Create rooms
        rooms = new HashMap<>();
        
        Room village = new Room("村", "平和な村です。ここから冒険が始まります。");
        Room forest = new Room("魔法の森", "神秘的な森です。何かが潜んでいそうです...");
        Room cave = new Room("洞窟", "暗い洞窟です。宝物があるかもしれません。");
        Room castle = new Room("魔王の城", "邪悪なオーラが漂う城です。最終決戦の場所！");
        
        // Add items to rooms
        forest.addItem(new Item("魔法の剣", "攻撃力+10の剣", 10));
        cave.addItem(new Item("体力ポーション", "HP+30回復", 30));
        village.addItem(new Item("パン", "HP+5回復", 5));
        
        // Add connections
        village.addConnection("north", forest);
        forest.addConnection("south", village);
        forest.addConnection("east", cave);
        forest.addConnection("west", castle);
        cave.addConnection("west", forest);
        castle.addConnection("east", forest);
        
        // Add enemies
        forest.addEnemy(new Enemy("ゴブリン", 30, 8));
        cave.addEnemy(new Enemy("オーク", 50, 12));
        castle.addEnemy(new Enemy("魔王", 100, 25));
        
        rooms.put("村", village);
        rooms.put("魔法の森", forest);
        rooms.put("洞窟", cave);
        rooms.put("魔王の城", castle);
        
        currentRoom = village;
    }
    
    public void start() {
        System.out.println("=".repeat(50));
        System.out.println("🏮 魔改造テキストアドベンチャー 🏮");
        System.out.println("=".repeat(50));
        System.out.println("ようこそ、勇者よ！");
        System.out.println("コマンド: look, go <方向>, take <アイテム>, use <アイテム>, fight, inventory, quit");
        System.out.println();
        
        showCurrentRoom();
        
        while (gameRunning && player.isAlive()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            processCommand(input);
        }
        
        if (!player.isAlive()) {
            System.out.println("💀 ゲームオーバー... また挑戦してください！");
        }
        
        scanner.close();
    }
    
    private void processCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String arg = parts.length > 1 ? parts[1] : "";
        
        switch (command) {
            case "look":
            case "l":
                showCurrentRoom();
                break;
            case "go":
            case "g":
                move(arg);
                break;
            case "take":
            case "t":
                takeItem(arg);
                break;
            case "use":
            case "u":
                useItem(arg);
                break;
            case "fight":
            case "f":
                fight();
                break;
            case "inventory":
            case "i":
                showInventory();
                break;
            case "quit":
            case "q":
                gameRunning = false;
                System.out.println("さようなら、また会いましょう！");
                break;
            case "help":
            case "h":
                showHelp();
                break;
            default:
                System.out.println("分からないコマンドです。'help'でヘルプを表示します。");
        }
    }
    
    private void showCurrentRoom() {
        System.out.println("\n📍 " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());
        
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("💎 アイテム: " + currentRoom.getItems().keySet());
        }
        
        if (!currentRoom.getEnemies().isEmpty()) {
            System.out.println("👹 敵: " + currentRoom.getEnemies().keySet());
        }
        
        if (!currentRoom.getConnections().isEmpty()) {
            System.out.println("🚪 出口: " + currentRoom.getConnections().keySet());
        }
        
        System.out.println("❤️ HP: " + player.getHp() + "/" + player.getMaxHp() + 
                          " ⚔️ 攻撃力: " + player.getAttack());
    }
    
    private void move(String direction) {
        Room nextRoom = currentRoom.getConnection(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("🚶 " + direction + "に移動しました。");
            showCurrentRoom();
            
            // Random encounter
            if (random.nextDouble() < 0.3 && !currentRoom.getEnemies().isEmpty()) {
                System.out.println("⚠️ 突然敵が現れた！");
            }
        } else {
            System.out.println("その方向には行けません。");
        }
    }
    
    private void takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);
        if (item != null) {
            player.addItem(item);
            System.out.println("📦 " + item.getName() + "を手に入れました！");
        } else {
            System.out.println("そのアイテムは見当たりません。");
        }
    }
    
    private void useItem(String itemName) {
        Item item = player.getItem(itemName);
        if (item != null) {
            if (item.getName().contains("ポーション") || item.getName().contains("パン")) {
                player.heal(item.getValue());
                player.removeItem(itemName);
                System.out.println("✨ " + item.getName() + "を使用しました！HP+" + item.getValue());
            } else if (item.getName().contains("剣")) {
                System.out.println("⚔️ " + item.getName() + "を装備しています。攻撃力が上がっています！");
            } else {
                System.out.println("このアイテムは使用できません。");
            }
        } else {
            System.out.println("そのアイテムを持っていません。");
        }
    }
    
    private void fight() {
        if (currentRoom.getEnemies().isEmpty()) {
            System.out.println("戦う相手がいません。");
            return;
        }
        
        String enemyName = currentRoom.getEnemies().keySet().iterator().next();
        Enemy enemy = currentRoom.getEnemy(enemyName);
        
        System.out.println("⚔️ " + enemyName + "との戦闘開始！");
        
        while (enemy.isAlive() && player.isAlive()) {
            // Player attack
            int damage = player.getTotalAttack();
            enemy.takeDamage(damage);
            System.out.println("🗡️ " + damage + "のダメージを与えました！");
            
            if (!enemy.isAlive()) {
                System.out.println("🎉 " + enemyName + "を倒しました！");
                currentRoom.removeEnemy(enemyName);
                
                if (enemyName.equals("魔王")) {
                    System.out.println("🏆 おめでとうございます！魔王を倒し、世界を救いました！");
                    gameRunning = false;
                }
                break;
            }
            
            // Enemy attack
            int enemyDamage = enemy.getAttack();
            player.takeDamage(enemyDamage);
            System.out.println("💥 " + enemyDamage + "のダメージを受けました！");
            
            System.out.println("❤️ HP: " + player.getHp() + " 👹 敵HP: " + enemy.getHp());
        }
    }
    
    private void showInventory() {
        System.out.println("🎒 所持品:");
        if (player.getItems().isEmpty()) {
            System.out.println("何も持っていません。");
        } else {
            for (Item item : player.getItems()) {
                System.out.println("  - " + item.getName() + ": " + item.getDescription());
            }
        }
    }
    
    private void showHelp() {
        System.out.println("📖 コマンド一覧:");
        System.out.println("  look/l - 周囲を見回す");
        System.out.println("  go/g <方向> - 指定した方向に移動");
        System.out.println("  take/t <アイテム> - アイテムを取る");
        System.out.println("  use/u <アイテム> - アイテムを使う");
        System.out.println("  fight/f - 敵と戦う");
        System.out.println("  inventory/i - 所持品を確認");
        System.out.println("  help/h - このヘルプを表示");
        System.out.println("  quit/q - ゲーム終了");
    }
    
    public static void main(String[] args) {
        new MagicalAdventure().start();
    }
}