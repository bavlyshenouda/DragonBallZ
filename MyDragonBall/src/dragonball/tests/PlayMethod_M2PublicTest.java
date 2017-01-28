package dragonball.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleListener;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.game.Game;

public class PlayMethod_M2PublicTest {

	@Test(timeout = 10000)
	public void testPlayMethod() throws Exception {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				100, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		superAttacks.add(new SuperAttack("Death Beam", 300));
		strong.setSuperAttacks(superAttacks);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		ultimateAttacks.add(new UltimateAttack("Super Kamehameha", 450));
		strong.setUltimateAttacks(ultimateAttacks);
		int block = 0;
		int physical = 0;
		int superr1 = 0;
		int superr2 = 0;
		int ultimate1 = 0;
		int ultimate2 = 0;
		for (int i = 0; i < 4000; i++) {
			e.setHealthPoints(5000);
			Game g = new Game();
			g.getPlayer().getFighters().add(e);
			g.getPlayer().setActiveFighter(e);
			Battle b = new Battle(e, strong);
			//b.setListener(g);
			invokeSetter(b, BattleListener.class, g);
			strong.setKi(3);
			b.start();
			b.attack(new PhysicalAttack());
			int meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if (e.getHealthPoints() == meHealth - 400)
				superr2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if (e.getHealthPoints() == meHealth - 400)
				superr2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if (e.getHealthPoints() == meHealth - 400)
				superr2++;
			else if (e.getHealthPoints() == meHealth - 600)
				ultimate1++;
			else if (e.getHealthPoints() == meHealth - 550)
				ultimate2++;
			b.attack(new PhysicalAttack());
			meHealth = e.getHealthPoints();
			b.play();
			if (e.getHealthPoints() == meHealth - 150)
				physical++;
			else if (e.getHealthPoints() == meHealth && b.isFoeBlocking())
				block++;
			else if (e.getHealthPoints() == meHealth - 350)
				superr1++;
			else if (e.getHealthPoints() == meHealth - 400)
				superr2++;
			else if (e.getHealthPoints() == meHealth - 600)
				ultimate1++;
			else if (e.getHealthPoints() == meHealth - 550)
				ultimate2++;
		}

		assertTrue("One of the possible actions that the non playable fighter can do in play method is blocking",
				block > 0);
		assertTrue(
				"One of the possible actions that the non playable fighter can do in play method is using a super attack",
				superr1 > 0 || superr2 > 0);
		assertTrue(
				"One of the possible actions that the non playable fighter can do in play method is using an ultimate attack",
				ultimate1 > 0 || ultimate2 > 0);
		assertTrue(
				"One of the possible actions that the non playable fighter can do in play method is using physical attack",
				physical > 0);
		assertTrue(
				"if the non playable fighter is using a super attack in the play method , the super attack should be chosen randomly",
				superr1 > 0 && superr2 > 0);
		assertTrue(
				"if the non playable fighter is using an ultimate attack in the play method , the ultimate attack should be chosen randomly",
				ultimate1 > 0 && ultimate2 > 0);

	}

	// **************************** Helper methods ********************************//
	@SuppressWarnings("rawtypes")
	private static void invokeSetter(Object mainObject, Class variableClass,
			Object variableObject) throws NoSuchMethodException,
					SecurityException {

		int index = classContainsFieldAtIndex(mainObject.getClass(),
				variableClass);

		if (index != -1) {
			Field f = mainObject.getClass().getDeclaredFields()[index];
			String n = f.getName().toUpperCase();
			String methodName = "set" + n.charAt(0) + f.getName().substring(1);
			Method setter = mainObject.getClass().getMethod(methodName,
					variableClass);
			try {
				setter.invoke(mainObject, variableObject);
			} catch (Exception e) {
				fail("The class: " + mainObject.getClass()
						+ " should have a setter method for the listener");
			}
		}
	}

	// returns the index of the variable in a certain class if exists, and -1
	// otherwise
	@SuppressWarnings("rawtypes")
	private static int classContainsFieldAtIndex(Class c, Class contained) {
		Field[] fields = c.getDeclaredFields();
		int i = -1;
		for (int j = 0; j < fields.length; j++) {
			if (fields[j].getType().equals(contained)) {
				i = j;
				break;
			}
		}
		return i;
	}

}
