/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universitï¿½ Bordeaux.
 */
package soldier.core;

import java.util.Iterator;

public abstract class UnitRider extends UnitSimple {

	public UnitRider(String name, BehaviorSoldier behavior) {
		super(name, behavior);
	}
	
	public void accept(Visitor v){
		Iterator<Equipment> e = this.getEquipments();
		while (e.hasNext())
			e.next().accept(v); //on parcourt les armes des unités 
								// et on leurs donnent le visiteur
		v.visit(this);
	}

}
