package fr.umontpellier.iut.algogen.strategies;

import fr.umontpellier.iut.algogen.individus.IIndividu;

import java.util.ArrayList;
import java.util.Random;

public abstract class StrategieCalculNextGen<T extends IIndividu<T>> {
    /**
     * @param pop : Une population
     * @return un individu selectinné par roullette.
     *
     * Example :
     * supposons que pop.size()==3, avec S = 6, et
     *pop.get(0).evaluerFitness() == 2 (l'individu 0 a une fitness de 2)
     * pop.get(1).evaluerFitness() == 3 (l'individu 1 a une fitness de 3)
     *pop.get(2).evaluerFitness() == 1 (l'individu 2 a une fitness de 1)
     * dans ce cas, on tire un entier r dans [0,S-1] (=[0,5]), puis :
     * si r est dans {0,1} , on selectionne l'individu 0
     * si r est dans {2,3,4} on selectionne l'individu 1
     * si r est dans {5}, on selectionne l'individu 2
     **/
    protected T selectionRoulette(ArrayList<T> pop) {
        int s1=0,s2=0, aleatoir;
        int size = pop.size();
        for(int i=0; i<size;i++){
            s1 += pop.get(i).evaluerFitness();
        }
        Random random = new Random();
        aleatoir = random.nextInt(s1-1);
        for(int i=0; i<size;i++){
            s2 += pop.get(i).evaluerFitness();
            if(aleatoir<s2) return pop.get(i);
        }
        return pop.get(0); //pas sur de celle ci
    }
    /**
     * @param pop : Une population
     * @return deux individus parents selectinnés par roullette.
     *
     **/
    public ArrayList<T> selectionParents(ArrayList<T> pop) {
        ArrayList<T> les2Parents = new ArrayList<>();
        T parent1 = selectionRoulette(pop);
        les2Parents.add(parent1);
        //pop.remove(parent1);
        T parent2 = selectionRoulette(pop);
        les2Parents.add(parent2);
        return les2Parents;
    }
    public T meilleurIndiv(ArrayList<T> pop){
        T bestIndivCourant = pop.get(0);

        for(int i=1; i<pop.size();i++){
            if (bestIndivCourant.evaluerFitness()<pop.get(i).evaluerFitness()){
                bestIndivCourant = pop.get(i);
            }
        }
        return bestIndivCourant;
    }

    public abstract ArrayList<T> calculerNextGen(ArrayList<T> pop);


}


