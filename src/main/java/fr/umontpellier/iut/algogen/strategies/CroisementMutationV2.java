package fr.umontpellier.iut.algogen.strategies;

import fr.umontpellier.iut.algogen.individus.IIndividu;

import java.util.ArrayList;

public class CroisementMutationV2<T extends IIndividu<T>> extends StrategieCalculNextGen<T> {


    private double pmutation;

    public CroisementMutationV2(double pmutation) {
        this.pmutation = pmutation;
    }

    /**
     * @param pop : Une population
     * @return une nouvelle generation qui contient les meilleurs individu de pop
     * ainsi que des individu fils.
     *
     **/

    @Override
    public ArrayList<T> calculerNextGen(ArrayList<T> pop) {

        ArrayList<T> nextGen = new ArrayList<>();
        //ArrayList<T> parentsFecondation;
        T parent1, parent2;
        T fils;
        int taillePop = pop.size();
        int lesParentsParfaits = 2;

        for(int i= 0; i<taillePop-lesParentsParfaits;i++) {
            parent1 = selectionRoulette(pop);
            parent2 = selectionRoulette(pop);
            //parentsFecondation = selectionParents(pop);
            if (Math.random()<=pmutation) {
                parent1 = (parent1.calculerMutation());
            }
            if (Math.random()<=pmutation) {
                parent2 = (parent2.calculerMutation());
            }
            fils = parent1.calculerCroisement(parent2);
            nextGen.add(fils.calculerMutation());

        }
        nextGen.add(meilleurIndiv(pop));
        pop.remove(meilleurIndiv(pop));
        nextGen.add(meilleurIndiv(pop));
        return nextGen;
    }



}