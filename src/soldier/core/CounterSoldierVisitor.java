package soldier.core;

public class CounterSoldierVisitor implements Visitor {
	private int counter;
	

	public void visit(UnitGroup o) { //**** a changer ******//
		/*for(Unit u: (UnitGroup)o.iterator()){
			u.accept(this);
		}*/
	}

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
