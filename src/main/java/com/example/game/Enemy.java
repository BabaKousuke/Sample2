package com.example.game;

/**
 * 敵クラス
 */
public class Enemy {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    
    public Enemy(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
    }
    
    public boolean isAlive() {
        return hp > 0;
    }
    
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }
    
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    
    @Override
    public String toString() {
        return name + " (HP: " + hp + "/" + maxHp + ", 攻撃力: " + attack + ")";
    }
}