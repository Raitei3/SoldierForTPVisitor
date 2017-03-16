/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universitï¿½ Bordeaux.
 */
package test;

import java.util.ArrayList;

import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AddObserverAllArmy;
import soldier.core.AgeAbstractFactory;
import soldier.core.ArrayUnitRiderVisitor;
import soldier.core.CounterAttackWeaponVisitor;
import soldier.core.CounterSoldierVisitor;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.util.DeadUnitCounterObserver;

public class MainFightTwoAges {

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
		pig.addUnit(fact.riderUnit(prefix + "jenny"));
		pig.addUnit(fact.riderUnit(prefix + "kenny"));
		sg.addUnit(pig);
		pig.addEquipment(fact.defenseWeapon());
		pig.addEquipment(fact.defenseWeapon());
		pig.addEquipment(fact.attackWeapon());
		return sg;
	}
	
	public static void main(String[] args) {

		AgeAbstractFactory age1 = new AgeMiddleFactory();
		AgeAbstractFactory age2 = new AgeFutureFactory();
		
		DeadUnitCounterObserver obsTeam1 = new  DeadUnitCounterObserver();
		DeadUnitCounterObserver obsTeam2 = new  DeadUnitCounterObserver();

		CounterSoldierVisitor cv = new CounterSoldierVisitor();
		CounterAttackWeaponVisitor cwv = new CounterAttackWeaponVisitor();
		ArrayUnitRiderVisitor arrayV = new ArrayUnitRiderVisitor(200);
		AddObserverAllArmy obsAllArmy = new AddObserverAllArmy(obsTeam1);

		Unit team1 = createTeam(age1, "Team1::"); 
		Unit team2 = createTeam(age2, "Team2::"); 
		int round = 0;
		
		//test Visiteur qui compte le nombre de soldat.
		team1.accept(cv);
		System.out.println("Number of soldier team 1 = "+cv.getNbSoldier());
		team2.accept(cv);
		System.out.println("Number of soldier team 1 + team 2 = "+cv.getNbSoldier());
		
		//test Visiteur qui compte le nombre d'arme d'attaque.
		team1.accept(cwv);
		team2.accept(cwv);
		
		//test Visiteur qui retourne un tableau de UnitRider.
		team1.accept(arrayV);
		team2.accept(arrayV);
		ArrayList<Unit> ar = arrayV.getListUnitRider();
		System.out.println("Unit rider in game :");
		for(Unit u : ar){
			System.out.println(u.getName());
		}
		
		//test Vsiteur qui ajoute les observable a toute une armé
		team1.accept(obsAllArmy);
		obsAllArmy.setObserver(obsTeam2);
		team2.accept(obsAllArmy);
		
		/*
		while(team1.subUnits().hasNext())
			team1.subUnits().add
*/

		while(team1.alive() && team2.alive()) {
			System.out.println("Round  #" + round++);
			System.out.println("number of death in team1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team2 " + obsTeam2.getNumberOfDeadUnits());

			float st1 = team1.strike();
			System.out.println(team1.getName() + " attack with force : " + st1);
			team2.parry(st1);
			System.out.println("number of death in team 1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team 2 " + obsTeam2.getNumberOfDeadUnits());

			float st2 = team2.strike();
			System.out.println(team2.getName() + " attack with force : " + st2);
			team1.parry(st2);
			System.out.println("number of death in team 1 " + obsTeam1.getNumberOfDeadUnits());
			System.out.println("number of death in team 2 " + obsTeam2.getNumberOfDeadUnits());

		}
		System.out.println("The end ... " + (team1.alive() ? team1.getName() : team2.getName()) + " won." );
	}

}
