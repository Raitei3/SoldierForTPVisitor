package soldier.core;

public class CounterSoliderVisitor implements Visitor {
	private double counter;
	
	@Override
	public void visit(UnitGroup o) {
		for(Unit u: (UnitGroup)o.iterator()){
			u.accept(this);//préciser dans le rapport qu'on a prit cette technique
		}
	}

	@Override
	public void visit(UnitInfantry o) {
		this.counter++;
	}

	@Override
	public void visit(UnitRider o) {
		this.counter++;
	}

	@Override
	public void visit(EquipmentAttack o) {		
	}

	@Override
	public void visit(EquipmentDefense o) {		
	}

	@Override
	public void visit(EquipmentToy o) {
		// TODO Auto-generated method stub
		
	}
	 

}
