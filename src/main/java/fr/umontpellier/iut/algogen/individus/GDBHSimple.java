package fr.umontpellier.iut.algogen.individus;

import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;
import fr.umontpellier.iut.algogen.fabriques.CreationIndividuGDBH;

import java.util.ArrayList;
import java.util.Random;



public class GDBHSimple extends IndividuGDBH<GDBHSimple> {
    public GDBHSimple(Instance in, ArrayList<Character> t) {
        super(in, t);
    }

    public GDBHSimple(Instance in) {
        super(in);
    }

    public GDBHSimple(Instance in, Solution s) {
        super(in, s);
    }
    /**
     * @param i2 : Un deuxieme individu
     * Faire le croisement entre this et i2 comme dans le texte.
     * @return un individu fils de type GDBHSimple.
     * 
    **/
    public GDBHSimple calculerCroisement(GDBHSimple i2) {
    	// on tire aléatoire un nombre de pas p E [0,k-1]
        Random r = new Random();
        int p = r.nextInt(getInstance().getK());
        GDBHSimple gdbhSimple=new GDBHSimple(inst);

        // on avance p pas dans le premier individu depuis le point de départ c d , on avance k − p pas dans le deuxième individu depuis c p et on récupère les parcours s i1 , s i2 .
        // on colle si1 , si2 pour avoir un fils.
        for (int i =0; i<=p; i++){
            gdbhSimple.trajet.add(trajet.get(i));
        }
        for(int j=p+1; j<getInstance().getK(); j++){
            gdbhSimple.trajet.add(trajet.get(j));
        }

        // le fils est normalisé afin qu’il reste dans la grille.
        gdbhSimple.normaliseTrajet();
        return gdbhSimple;
    }
    /**
     * Faire la mutation de this comme dans le texte.
     * @return un individu fils muté de type GDBHSimple.
     * 
    **/

    public GDBHSimple calculerMutation() {
        GDBHSimple individu=new GDBHSimple(inst);
        for (int i=0; i<inst.getK(); i++){
            individu.trajet.add(trajet.get(i));
        }

        // on tire aléatoirement deux valeurs p1 , p2 ∈ [0, k − 1]
        Random r = new Random();
        int p1 = r.nextInt(inst.getK());
        int p2 = r.nextInt(inst.getK());

        // on fait une permutation entre c p1 et c p2
        individu.trajet.set(p1,trajet.get(p2));
        individu.trajet.set(p2,trajet.get(p1));

        // l’individu muté doit être normalisé afin qu’il reste dans la grille
        individu.normaliseTrajet();
        return individu;
    }

}