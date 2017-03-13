package soldier.core;

public interface Visitor {
    void visit(UnitGroup o);
    void visit(UnitInfantry o);
    void visit(UnitRider o);
    void visit(EquipmentAttack o);
    void visit(EquipmentDefense o);
    void visit(EquipmentToy o);
}
