/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package soldier.core;

import java.util.Iterator;

public abstract class UnitInfantry extends UnitSimple {

	public UnitInfantry(String name, BehaviorSoldier behavior) {
		super(name, behavior);
	}

	public void accept(Visitor v){
		v.visit(this);
		/*for(Iterator<Equipment> it = getEquipments(); it.hasNext();it.next()){
			((Equipment) it).accept(v);
		}*/
	}
}
