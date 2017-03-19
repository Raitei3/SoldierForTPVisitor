package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AddObserverAllArmy;
import soldier.core.AgeAbstractFactory;
import soldier.core.ArrayUnitRiderVisitor;
import soldier.core.PrintAttackWeaponVisitor;
import soldier.core.CounterSoldierVisitor;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.util.DeadUnitCounterObserver;

public class VisitorTest {

	AgeAbstractFactory age1;
	AgeAbstractFactory age2;

	DeadUnitCounterObserver obsTeam1;
	DeadUnitCounterObserver obsTeam2;

	CounterSoldierVisitor cv;
	PrintAttackWeaponVisitor cwv;

	ArrayUnitRiderVisitor arrayV;
	AddObserverAllArmy obsAllArmy;

	Unit team1;
	Unit team2;
	int round;
	
	ArrayList<Unit> ar;
	ArrayList<Unit> arTest;
	boolean valid;
	
	@Before
	public void setUp() throws Exception {

		age1 = new AgeMiddleFactory();
		age2 = new AgeFutureFactory();

		obsTeam1 = new  DeadUnitCounterObserver();
		obsTeam2 = new  DeadUnitCounterObserver();

		cv = new CounterSoldierVisitor();
		cwv = new PrintAttackWeaponVisitor();
		arrayV = new ArrayUnitRiderVisitor(200);
		obsAllArmy = new AddObserverAllArmy(obsTeam1);

		team1 = createTeam(age1, "Team1::"); 
		team2 = createTeam(age2, "Team2::"); 
		round = 0;
		
		ar = new ArrayList<Unit>();
		arTest =new ArrayList<Unit>();
		
		
	}





	public static Unit createTeam(AgeAbstractFactory fact, String prefix)  {
		UnitGroup sg = new UnitGroup(prefix + "Animals");
		UnitGroup bl  = new UnitGroup(prefix + "Worms");
		bl.addUnit(fact.infantryUnit(prefix + "nicky"));
		bl.addUnit(fact.infantryUnit(prefix + "tomy"));
		sg.addUnit(bl);
		bl.addEquipment(fact.attackWeapon());
		bl.addEquipment(fact.defenseWeapon());
		bl.addEquipment(fact.attackWeapon());
		UnitGroup pig = new UnitGroup(prefix + "Pigs");
		Unit u1 = fact.riderUnit(prefix + "jenny");
		pig.addUnit(u1); // on r�cup�re les r�f�rence des unitRider pour les comparer arp�s.
		Unit u2 = fact.riderUnit(prefix + "kenny");
		pig.addUnit(u2);
		sg.addUnit(pig);
		pig.addEquipment(fact.defenseWeapon());
		pig.addEquipment(fact.defenseWeapon());
		pig.addEquipment(fact.attackWeapon());
		return sg;
	}


	@Test
	public void test() {
		// test Visiteur qui compte le nombre de soldat.
		team1.accept(cv);
		System.out.println("Number of soldier team 1 = " + cv.getNbSoldier());
		org.junit.Assert.assertEquals(4, cv.getNbSoldier());
		team2.accept(cv);
		System.out.println("Number of soldier team 1 + team 2 = " + cv.getNbSoldier()+"\n");
		org.junit.Assert.assertEquals(8, cv.getNbSoldier());


		// test Visiteur qui compte le nombre d'arme d'attaque.
		System.out.println("weapon team 1 :");
		team1.accept(cwv);
		System.out.println("\nweapon team 2 : ");
		team2.accept(cwv);



		// test Visiteur qui retourne un tableau de UnitRider.
		team1.accept(arrayV);
		team2.accept(arrayV);
		ar = arrayV.getListUnitRider();
		System.out.println("\n Unit rider in game :");
		for (Unit u : ar) {
			System.out.println(u.getName());
			valid = false;
			for(Unit unit : arTest){
				if (u == unit){ //on compare les r�f�rences des units
					valid = true;
				}
				org.junit.Assert.assertFalse(valid);
			}
		}
		System.out.println("");
		

		// test Vsiteur qui ajoute les observable a toute une arm�
		team1.accept(obsAllArmy);
		obsAllArmy.setObserver(obsTeam2);
		team2.accept(obsAllArmy);

		/*
		 * while(team1.subUnits().hasNext()) team1.subUnits().add
		 */

		while (team1.alive() && team2.alive()) {
			System.out.println("Round  #" + round++);
			System.out.println("number of death in team1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team2 " + obsTeam2.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(0, obsTeam1.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(0, obsTeam2.getNumberOfDeadUnits());

			float st1 = team1.strike();
			System.out.println(team1.getName() + " attack with force : " + st1);
			team2.parry(st1);
			System.out.println("number of death in team 1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team 2 " + obsTeam2.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(0,obsTeam1.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(4, obsTeam2.getNumberOfDeadUnits());

			float st2 = team2.strike();
			System.out.println(team2.getName() + " attack with force : " + st2);
			team1.parry(st2);
			System.out.println("number of death in team 1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team 2 " + obsTeam2.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(0, obsTeam1.getNumberOfDeadUnits());
			org.junit.Assert.assertEquals(4, obsTeam2.getNumberOfDeadUnits());

		}
		System.out.println("The end ... " + (team1.alive() ? team1.getName() : team2.getName()) + " won.");

	}

}
