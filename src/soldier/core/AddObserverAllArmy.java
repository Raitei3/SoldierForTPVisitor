package soldier.core;

public class AddObserverAllArmy implements Visitor {
	
	public int counter;
	public UnitObserver obs;
	
	public AddObserverAllArmy(UnitObserver obs){
		this.obs = obs;
	}
	
	public void visit(UnitGroup o) {}

	public void visit(UnitInfantry o) {
		o.addObserver(obs);
	}

	public void visit(UnitRider o) {
		o.addObserver(obs);
	}

	public void visit(EquipmentAttack o) {}	
	
	public void visit(EquipmentDefense o) {}
	
	public void visit(EquipmentToy o) {}
}
