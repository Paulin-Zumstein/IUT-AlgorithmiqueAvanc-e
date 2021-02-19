package fr.umontpellier.iut.algogen.strategies;

import fr.umontpellier.iut.algogen.individus.IIndividu;

import java.util.ArrayList;
import java.util.Random;

public class CroisementMutationV1<T extends IIndividu<T>> extends StrategieCalculNextGen<T> {

    private double pmutation;

    public CroisementMutationV1(double pmut) {
        this.pmutation = pmut;
    }

    /**
     * @param pop : Une population
     * @return une nouvelle generation qui contient les meilleurs individu de pop
     * ainsi que des individu fils.
     *
     **/
    @Override
    public ArrayList<T> calculerNextGen(ArrayList<T> pop) {
        //selectionRoulette(pop); ca y était deja de base mais j'sais pas quoi en faire
        ArrayList<T> nextGen = new ArrayList<>();
        ArrayList<T> parentsFecondation;
        T fils;
        int taillePop = pop.size();
        int lesParentsParfaits = 2;
        for(int i= 0; i<taillePop-lesParentsParfaits;i++) {
            parentsFecondation = selectionParents(pop);
            fils = parentsFecondation.get(0).calculerCroisement(parentsFecondation.get(1));
            if (Math.random()<=pmutation) {
                nextGen.add(fils.calculerMutation());
            }
            else {
                nextGen.add(fils.calculerMutation());
            }
        }
        nextGen.add(meilleurIndiv(pop));
        pop.remove(meilleurIndiv(pop));
        nextGen.add(meilleurIndiv(pop));
        return nextGen;
    }




}