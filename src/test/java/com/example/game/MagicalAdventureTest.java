package com.example.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * テストクラス - ゲームの基本機能をテスト
 */
public class MagicalAdventureTest {
    
    @Test
    public void testPlayerCreation() {
        Player player = new Player("テスト勇者", 100, 20);
        assertEquals("テスト勇者", player.getName());
        assertEquals(100, player.getHp());
        assertEquals(100, player.getMaxHp());
        assertEquals(20, player.getAttack());
        assertTrue(player.isAlive());
    }
    
    @Test
    public void testPlayerDamage() {
        Player player = new Player("テスト勇者", 100, 20);
        player.takeDamage(30);
        assertEquals(70, player.getHp());
        assertTrue(player.isAlive());
        
        player.takeDamage(80);
        assertEquals(0, player.getHp());
        assertFalse(player.isAlive());
    }
    
    @Test
    public void testPlayerHealing() {
        Player player = new Player("テスト勇者", 100, 20);
        player.takeDamage(50);
        assertEquals(50, player.getHp());
        
        player.heal(30);
        assertEquals(80, player.getHp());
        
        // Max HP を超えて回復できないことを確認
        player.heal(50);
        assertEquals(100, player.getHp());
    }
    
    @Test
    public void testItemUsage() {
        Player player = new Player("テスト勇者", 100, 20);
        Item sword = new Item("魔法の剣", "攻撃力+10", 10);
        
        int baseAttack = player.getTotalAttack();
        player.addItem(sword);
        int enhancedAttack = player.getTotalAttack();
        
        assertEquals(baseAttack + 10, enhancedAttack);
    }
    
    @Test
    public void testEnemyCreation() {
        Enemy goblin = new Enemy("ゴブリン", 30, 8);
        assertEquals("ゴブリン", goblin.getName());
        assertEquals(30, goblin.getHp());
        assertEquals(8, goblin.getAttack());
        assertTrue(goblin.isAlive());
    }
    
    @Test
    public void testRoomConnections() {
        Room village = new Room("村", "平和な村");
        Room forest = new Room("森", "暗い森");
        
        village.addConnection("north", forest);
        forest.addConnection("south", village);
        
        assertEquals(forest, village.getConnection("north"));
        assertEquals(village, forest.getConnection("south"));
        assertNull(village.getConnection("east"));
    }
    
    @Test
    public void testRoomItems() {
        Room room = new Room("テストルーム", "テスト用の部屋");
        Item potion = new Item("ポーション", "HP回復", 30);
        
        room.addItem(potion);
        assertTrue(room.getItems().containsKey("ポーション"));
        
        Item retrieved = room.removeItem("ポーション");
        assertEquals(potion, retrieved);
        assertFalse(room.getItems().containsKey("ポーション"));
    }
}