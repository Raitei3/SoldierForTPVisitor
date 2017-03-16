/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package soldier.core;

public abstract class UnitRider extends UnitSimple {

	public UnitRider(String name, BehaviorSoldier behavior) {
		super(name, behavior);
	}
	
	public void accept(Visitor v){
		//super.accept(v);
		v.visit(this);
	}

}
