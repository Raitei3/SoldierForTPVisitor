/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Université Bordeaux.
 */
package soldier.core;

import java.util.Iterator;

public abstract class UnitInfantry extends UnitSimple {

	public UnitInfantry(String name, BehaviorSoldier behavior) {
		super(name, behavior);
	}

	public void accept(Visitor v){
		Iterator<Equipment> e = this.getEquipments();
		while (e.hasNext())
			e.next().accept(v);
		v.visit(this);
	}
}
