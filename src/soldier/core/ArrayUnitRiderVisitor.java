package soldier.core;

import java.util.ArrayList;

public class ArrayUnitRiderVisitor implements Visitor {
	
	public double health;
	public ArrayList<Unit> array;
	
	public ArrayUnitRiderVisitor(double health) {
		this.health=health;
		array = new ArrayList<Unit>();
	}

	public void visit(UnitGroup o) {}

	public void visit(UnitInfantry o) {}

	public void visit(UnitRider o) {
		if (o.getHealthPoints()<= health) {
			array.add(o);
		}
	}

	public void visit(EquipmentAttack o) {}

	public void visit(EquipmentDefense o) {}

	public void visit(EquipmentToy o) {}
	
	public ArrayList<Unit> getListUnitRider(){
		return array;
	}
	public void resetListUnitRider(){
		array.clear();
	}
	public void setHealth(double health){
		this.health = health;
	}

}
