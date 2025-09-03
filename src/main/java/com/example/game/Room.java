package com.example.game;

import java.util.HashMap;
import java.util.Map;

/**
 * ルーム（部屋・場所）クラス
 */
public class Room {
    private String name;
    private String description;
    private Map<String, Room> connections;
    private Map<String, Item> items;
    private Map<String, Enemy> enemies;
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.connections = new HashMap<>();
        this.items = new HashMap<>();
        this.enemies = new HashMap<>();
    }
    
    public void addConnection(String direction, Room room) {
        connections.put(direction, room);
    }
    
    public Room getConnection(String direction) {
        return connections.get(direction);
    }
    
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }
    
    public Item removeItem(String name) {
        return items.remove(name);
    }
    
    public void addEnemy(Enemy enemy) {
        enemies.put(enemy.getName(), enemy);
    }
    
    public Enemy getEnemy(String name) {
        return enemies.get(name);
    }
    
    public void removeEnemy(String name) {
        enemies.remove(name);
    }
    
    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Map<String, Room> getConnections() { return new HashMap<>(connections); }
    public Map<String, Item> getItems() { return new HashMap<>(items); }
    public Map<String, Enemy> getEnemies() { return new HashMap<>(enemies); }
}