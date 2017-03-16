package soldier.core;

import java.util.Iterator;

public class CounterAttackWeaponVisitor implements Visitor {
	
	private int counter;

	public void visit(UnitGroup o) {
		o.accept(this);
	}
	
	private void visitEquipment(UnitSimple u){
		Iterator<Equipment> e = u.getEquipments();
		while (e.hasNext())
			e.next().accept(this);
	}
	public void visit(UnitInfantry o) {
		this.visitEquipment(o);
	}
	
	public void visit(UnitRider o) {
		this.visitEquipment(o);
	}

	
	public void visit(EquipmentAttack o) {
		System.out.println(o);
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
