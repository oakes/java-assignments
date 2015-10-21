import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zach on 10/19/15.
 */
public class CharacterTest {

    @Test
    public void testBattle() throws Exception {
        Weapon playerWeapon = new Weapon();
        playerWeapon.damage = 50;
        Player player = new Player();
        player.name = "Test Player";
        player.weapon = playerWeapon;

        Weapon enemyWeapon = new Weapon();
        enemyWeapon.damage = 10;
        Enemy enemy = new Enemy("Test Enemy", 50, 10, enemyWeapon);

        player.battle(enemy);

        assertTrue(player.health > 0);
        assertTrue(enemy.health <= 0);
    }
}