package soldier.core;

public class CounterAttackWeaponVisitor implements Visitor {
	
	public void visit(UnitGroup o) {
		o.accept(this);
	}

	public void visit(UnitInfantry o) {}
	
	public void visit(UnitRider o) {}

	
	public void visit(EquipmentAttack o) {
		System.out.println(o);
	}

	public void visit(EquipmentDefense o) {}
	
	public void visit(EquipmentToy o) {}

}
