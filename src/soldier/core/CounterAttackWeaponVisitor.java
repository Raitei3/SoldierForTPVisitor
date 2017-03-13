package soldier.core;

public class CounterAttackWeaponVisitor implements Visitor {
	
	private int counter;

	public void visit(UnitGroup o) {}
	
	public void visit(UnitInfantry o) {}
	
	public void visit(UnitRider o) {}

	
	public void visit(EquipmentAttack o) {
		counter++;
	}

	public void visit(EquipmentDefense o) {}
	
	public void visit(EquipmentToy o) {}
	
	
	public int getNbAttackWeapon(){
		return counter;
	}
	
	public void resetCounter(){
		counter =0;
	}
}
