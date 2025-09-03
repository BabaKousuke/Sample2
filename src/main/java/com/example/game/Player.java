package com.example.game;

import java.util.ArrayList;
import java.util.List;

/**
 * プレイヤークラス
 */
public class Player {
    private String name;
    private int hp;
    private int maxHp;
    private int baseAttack;
    private List<Item> items;
    
    public Player(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.baseAttack = attack;
        this.items = new ArrayList<>();
    }
    
    public boolean isAlive() {
        return hp > 0;
    }
    
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }
    
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }
    
    public int getTotalAttack() {
        int totalAttack = baseAttack;
        for (Item item : items) {
            if (item.getName().contains("剣")) {
                totalAttack += item.getValue();
            }
        }
        return totalAttack;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public Item getItem(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    
    public void removeItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }
    
    // Getters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return baseAttack; }
    public List<Item> getItems() { return new ArrayList<>(items); }
}