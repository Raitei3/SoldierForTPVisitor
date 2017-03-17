package soldier.core;

public class CounterSoldierVisitor implements Visitor {
	private int counter;
	
	public CounterSoldierVisitor(){
		counter = 0;
	}
	
	public void visit(UnitGroup o) {}

	public void visit(UnitInfantry o) {
		counter++;
	}

	public void visit(UnitRider o) {
		counter++;
	}

	public void visit(EquipmentAttack o) {}	
	
	public void visit(EquipmentDefense o) {}
	
	public void visit(EquipmentToy o) {}
	
	public int getNbSoldier(){
		return counter;
	}
	
	public void resetCounter(){
		counter =0;
	}
}
