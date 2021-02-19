package fr.umontpellier.iut.algogen.fabriques;

import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.individus.GDBHSmartCrossingSmartMut;
import fr.umontpellier.iut.algogen.individus.IndividuPermut;
import fr.umontpellier.iut.algogen.individus.PermutSimple;

public class CreationIndividuGDBHSmartCrossingSmartMutViaPermut extends ICreator<GDBHSmartCrossingSmartMut> {

    @Override
    protected GDBHSmartCrossingSmartMut create(Instance in) {
        /*CreationIndividuPermut creationIndividuPermut= new CreationIndividuPermut();
        IndividuPermut individuPermut= creationIndividuPermut.create(in);
        a reprendre*/
        GDBHSmartCrossingSmartMut gdbhSmartCrossingSmartMut=new GDBHSmartCrossingSmartMut(in);
        return gdbhSmartCrossingSmartMut;
    }
}