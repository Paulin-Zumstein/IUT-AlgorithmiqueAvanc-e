package fr.umontpellier.iut.algogen;

import fr.umontpellier.iut.algogen.fabriques.ICreator;
import fr.umontpellier.iut.algogen.individus.IIndividu;
import fr.umontpellier.iut.algogen.strategies.CroisementMutationV2;
import fr.umontpellier.iut.algogen.strategies.StrategieCalculNextGen;

import java.util.ArrayList;

class AlgoGenetique<T extends IIndividu<T>> {

    private Instance inst;
    private StrategieCalculNextGen<T> strat;
    private ICreator<T> creator;
    /**
     * @param inn : instance ,
     * @param utt : strategie de caclcul des nouvelles generations
     * @param crr : createur d'individus
     *
     */
    public AlgoGenetique(Instance inn, StrategieCalculNextGen<T> utt, ICreator<T> crr) {
        this.inst = inn;
        this.strat = utt;
        this.creator = crr;
    }

    /**
     * @param inn : instance ,
     * @param crr : createur d'individus
     *
     */
    public AlgoGenetique(Instance inn, ICreator<T> crr) {
        this.inst = inn;
        this.creator = crr;
    }
    /**
     * @param taillePop : taille de la Population ,
     * @param nbGen : nombre de generations
     * Créer population initiale 
     * Lancer Strategie de calcule de nouvelles generations nbGen fois
     * @return  retourne la meilleur solution
     *
     */

    public Solution run(int taillePop, int nbGen) {
        ArrayList<T> pop = creator.creerPopInit(inst,taillePop);
        if(strat==null){
            return inst.greedySolver();
        }
        else{
            for(int i=0;i<nbGen;i++){
                pop = strat.calculerNextGen(pop);
            }
            T leking = strat.meilleurIndiv(pop);
            return leking.calculerSol();
        }
    }



}