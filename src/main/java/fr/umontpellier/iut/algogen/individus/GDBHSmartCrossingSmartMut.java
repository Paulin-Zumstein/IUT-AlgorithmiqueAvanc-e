package fr.umontpellier.iut.algogen.individus;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;

import java.util.ArrayList;
import java.util.Random;


public class GDBHSmartCrossingSmartMut extends IndividuGDBH<GDBHSmartCrossingSmartMut> {


    public GDBHSmartCrossingSmartMut(Instance in, ArrayList<Character> t) {
        super(in, t);
    }

    public GDBHSmartCrossingSmartMut(Instance in) {
        super(in);
    }

    public GDBHSmartCrossingSmartMut(Instance in, Solution s) {
        super(in, s);
    }
    /**
     * @param c : Un mouvement
     *
     * @return L'inverse du mouvement.
     *
    **/
    private static char inv(char c) {
     return '-';
    }

    /**
     * @param i2 : Un deuxieme individu
     * Faire le croisement intelligent entre this et i2 comme dans le texte.
     * @return un individu fils de type GDBHSimple.
     *
    **/

    public GDBHSmartCrossingSmartMut calculerCroisement(GDBHSmartCrossingSmartMut i2){ //à vérifier quand meme parce que c'est vraiment pas sûr

        // on tire aléatoirement un nombre de pas p ∈ [0, k/2]
        Random r = new Random();
        int p = r.nextInt((this.inst.getK()/2)+1);
        GDBHSmartCrossingSmartMut gdbhSmartCrossingSmartMut=new GDBHSmartCrossingSmartMut(inst);
        gdbhSmartCrossingSmartMut.trajet.clear();
        Coord c1 = this.inst.getStartingP();
        Coord c2 = i2.getInstance().getStartingP();
        // on avance p pas dans les deux individus et on récupère les points d’arrivé c1 , c2 .
        for (int i =0; i<p; i++){
            gdbhSmartCrossingSmartMut.trajet.add(this.trajet.get(i));
            c1 = calculerNextCoord(c1,this.trajet.get(i));
            c2 = calculerNextCoord(c2,i2.trajet.get(i));
        }

        System.out.println("p = " + p);
        //on avance de c1 vers c2 en prenant le plus cours chemin et en faisant k − p pas ou
        //moins.
        genererChemin(gdbhSmartCrossingSmartMut, c1, c2);
        //s'il reste des pas on ajoute les pas de indiv 2 jusqu’à avoir fini les pas.
        if(this.inst.getK()>gdbhSmartCrossingSmartMut.trajet.size()){
            int diffdepas = this.inst.getK()-gdbhSmartCrossingSmartMut.trajet.size();
            for (int i=0; i< diffdepas;i++){
                gdbhSmartCrossingSmartMut.trajet.add(i2.trajet.get(i+p));
            }
        }
        // le fils est normalisé afin qu’il reste dans la grille.
        //gdbhSmartCrossing.normaliseTrajet();
        return gdbhSmartCrossingSmartMut;
    }


    public void genererChemin(GDBHSmartCrossingSmartMut gdbhSmartCrossingSmartMut, Coord c1, Coord c2) {
        if(c1.getC()>c2.getC()){
            // c2 est à gauche de c1
            for(int i=0; i<c1.getC()-c2.getC();i++){
                gdbhSmartCrossingSmartMut.trajet.add('g');
            }
        }else {
            // c2 est à droite de c1
            for(int i=0; i<c2.getC()-c1.getC();i++){
                gdbhSmartCrossingSmartMut.trajet.add('d');
            }
        }if(c1.getL()>c2.getL()){
            // c2 est en haut de c1
            for(int i=0; i<c1.getL()-c2.getL();i++){
                gdbhSmartCrossingSmartMut.trajet.add('h');
            }
        }else{
            // c2 est en bas de c1
            for(int i=0; i<c2.getL()-c1.getL();i++){
                gdbhSmartCrossingSmartMut.trajet.add('b');
            }
        }
    }


    /**
     * @param x : Un indice
     * @param new1 : Un premier Mouvement
     * @param new2 : Un deuxieme Mouvement
     * Mettre new2 dans l'indice x et mettre new1 dans l'indice x+1
     * et enlever deux mouvements a la fin.                                  <------------- CONSIGNE DE FAUSSE
     *
    **/
    public void mutationAux(int x, char new1, char new2) {
        trajet.add(x,new1);
        trajet.add(x+2,new2);
        trajet.remove(inst.getK()+1);
        trajet.remove(inst.getK());
    }
    /**
     * Faire la mutation intelligente de this comme dans le texte.
     * @return un individu fils muté de type GDBHSimple.
     *
    **/

    public GDBHSmartCrossingSmartMut calculerMutation() {
        GDBHSmartCrossingSmartMut individu=new GDBHSmartCrossingSmartMut(inst,trajet);
        individu.trajet.clear();
        for (int i=0; i<getInstance().getK(); i++){
            individu.trajet.add(trajet.get(i));
        }

        // On tire une aléatoirement une valeur p ∈ [1, k − 2], et on prend trois points (c p−1 , c p , c p+1 )
        Random r = new Random();
        int p = r.nextInt(getInstance().getK() - 2);
        p++;
        // si (cp−1 , cp , cp+1 ) sont des mouvements identique alors on créer un ”crochet”
        if (individu.trajet.get(p - 1) == individu.trajet.get(p) && individu.trajet.get(p) == individu.trajet.get(p + 1)) {
            //System.out.println("crochet");
            faireCrochet(individu,p);

        }

        // sinon on prend deux mouvements consécutives différentes et on les inversent
        else{
            //System.out.println("invertion");
            int cp=0;
            while (individu.trajet.get(cp)==individu.trajet.get(cp+1)&&cp+1<getInstance().getK()-1){
                cp++;
            }
            char t=individu.trajet.get(cp);
            individu.trajet.set(cp,trajet.get(cp+1));
            individu.trajet.set(cp+1,t);
        }
        return individu;
    }

    /**
     * Faire un crochet qui reste dans le plateau
     */
    public void faireCrochet(GDBHSmartCrossingSmartMut individu,int p){
        Coord coord=calculerCoordPasP(p);
        if (individu.trajet.get(p)=='h'||individu.trajet.get(p)=='b'){
            if (coord.getC()==0){
                individu.trajet.add(p+2,'g');
                individu.trajet.add(p,'d');
            }else{
                individu.trajet.add(p+2,'d');
                individu.trajet.add(p,'g');
            }
        }else {
            if (coord.getL()==0){
                individu.trajet.add(p+2,'h');
                individu.trajet.add(p,'b');
            }else{
                individu.trajet.add(p+2,'b');
                individu.trajet.add(p,'h');
            }
        }
        individu.trajet.remove(inst.getK()-1);
        individu.trajet.remove(inst.getK());
    }

    /**
     * Calculer les coordonnes du p-ième pas dans le plan
     */
    public Coord calculerCoordPasP(int p){
        Coord coord=getInstance().getStartingP();
        for (int i=0;i<=p;i++){
            coord=calculerNextCoord(coord,trajet.get(i));
        }
        return coord;
    }
}
