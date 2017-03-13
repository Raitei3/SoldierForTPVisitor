/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Université Bordeaux.
 */
package soldier.core;


public abstract class EquipmentDefense extends EquipmentAbstract {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
