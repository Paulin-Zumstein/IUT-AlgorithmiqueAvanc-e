package fr.umontpellier.iut.algogen.fabriques;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.individus.GDBHSmartCrossing;
import fr.umontpellier.iut.algogen.individus.IndividuGDBH;

public class CreationIndividuGDBHSmartCrossing extends ICreator<GDBHSmartCrossing> {

    @Override
    protected GDBHSmartCrossing create(Instance in) {
        GDBHSmartCrossing gdbhSmartCrossing=new GDBHSmartCrossing(in);
        return gdbhSmartCrossing;
    }

    /*
    test d'affichage
    public static void main(String[] args) {
    boolean[][] p = {
                {true, false, false, false, true},
                {false, false, false, false, false},
                {false, false, false, true, false},
        };


        Coord sp = new Coord(2, 2);
        int k = 4;
        Instance in1 = new Instance(p, sp, k);
        CreationIndividuGDBH creationIndividuGDBH= new CreationIndividuGDBH();
        IndividuGDBH individuGDBH=creationIndividuGDBH.create(in1);
        System.out.println(individuGDBH);
    }*/
}
