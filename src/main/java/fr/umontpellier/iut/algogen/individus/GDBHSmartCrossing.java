package fr.umontpellier.iut.algogen.individus;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;

import java.util.ArrayList;
import java.util.Random;


public class GDBHSmartCrossing extends IndividuGDBH<GDBHSmartCrossing> {

    public GDBHSmartCrossing(Instance in, ArrayList<Character> t) {
        super(in, t);
    }

    public GDBHSmartCrossing(Instance in) {
        super(in);
    }

    public GDBHSmartCrossing(Instance in, Solution s) {
        super(in, s);
    }

    /**
     * @param i2 : Un deuxieme individu
     *           Faire le croisement intelligent entre this et i2 comme dans le texte.
     * @return un individu fils de type GDBHSimple.
     **/
    public GDBHSmartCrossing calculerCroisement(GDBHSmartCrossing i2) { //à vérifier quand meme parce que c'est vraiment pas sûr

        // on tire aléatoirement un nombre de pas p ∈ [0, k/2]
        Random r = new Random();
        int p = r.nextInt((this.inst.getK()/2)+1);
        GDBHSmartCrossing gdbhSmartCrossing=new GDBHSmartCrossing(inst);
        gdbhSmartCrossing.trajet.clear();
        Coord c1 = this.inst.getStartingP();
        Coord c2 = i2.getInstance().getStartingP();
        // on avance p pas dans les deux individus et on récupère les points d’arrivé c1 , c2 .
        for (int i =0; i<p; i++){
            gdbhSmartCrossing.trajet.add(this.trajet.get(i));
            c1 = calculerNextCoord(c1,this.trajet.get(i));
            c2 = calculerNextCoord(c2,i2.trajet.get(i));
        }

        System.out.println("p = " + p);
        //on avance de c1 vers c2 en prenant le plus cours chemin et en faisant k − p pas ou
        //moins.
        genererChemin(gdbhSmartCrossing, c1, c2);
        //s'il reste des pas on ajoute les pas de indiv 2 jusqu’à avoir fini les pas.
        if(this.inst.getK()>gdbhSmartCrossing.trajet.size()){
            int diffdepas = this.inst.getK()-gdbhSmartCrossing.trajet.size();
            for (int i=0; i< diffdepas;i++){
                gdbhSmartCrossing.trajet.add(i2.trajet.get(i+p));
            }
        }
        // le fils est normalisé afin qu’il reste dans la grille.
        //gdbhSmartCrossing.normaliseTrajet();
        return gdbhSmartCrossing;
    }


    public void genererChemin(GDBHSmartCrossing gdbhSmartCrossing, Coord c1, Coord c2) {
        if(c1.getC()>c2.getC()){
            // c2 est à gauche de c1
            for(int i=0; i<c1.getC()-c2.getC();i++){
                gdbhSmartCrossing.trajet.add('g');
            }
        }
        else {
            // c2 est à droite de c1
            for(int i=0; i<c2.getC()-c1.getC();i++){
                gdbhSmartCrossing.trajet.add('d');
            }
        }
        if(c1.getL()>c2.getL()){
            // c2 est en haut de c1
            for(int i=0; i<c1.getL()-c2.getL();i++){
                gdbhSmartCrossing.trajet.add('h');
            }
        }
        else{
            // c2 est en bas de c1
            for(int i=0; i<c2.getL()-c1.getL();i++){
                gdbhSmartCrossing.trajet.add('b');
            }
        }
    }

    /**
     * Execute la mutation de this comme dans le texte.
     *
     * @return un individu fils muté de type GDBHSimple.
     **/
    public GDBHSmartCrossing calculerMutation() {
        GDBHSmartCrossing gdbhSimple = new GDBHSmartCrossing(inst);
        gdbhSimple.trajet.clear();
        for (int i = 0; i < getInstance().getK(); i++) {
            gdbhSimple.trajet.add(trajet.get(i));
        }

        // on tire aléatoirement deux valeurs p1 , p2 ∈ [0, k − 1]
        Random r = new Random();
        int p1 = r.nextInt(getInstance().getK());
        int p2 = r.nextInt(getInstance().getK());

        // on fait une permutation entre c p1 et c p2
        gdbhSimple.trajet.set(p1, trajet.get(p2));
        gdbhSimple.trajet.set(p2, trajet.get(p1));

        // l’individu muté doit être normalisé afin qu’il reste dans la grille
        gdbhSimple.normaliseTrajet();
        return gdbhSimple;
    }
}