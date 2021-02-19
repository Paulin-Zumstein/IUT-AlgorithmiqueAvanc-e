package fr.umontpellier.iut.algogen.individus;

import fr.umontpellier.iut.algogen.Instance;

import java.util.ArrayList;
import java.util.Random;

public class PermutSimple extends IndividuPermut<PermutSimple> {
    public PermutSimple(Instance in, ArrayList<Integer> p) {
        super(in, p);
    }

    public PermutSimple(Instance in) {
        super(in);
    }

    /**
     * @param i2 : Un deuxieme individu
     * Execute le croisement entre this et i2 comme dans le texte.
     * @return un individu fils de type GDBHSimple.
     * 
    **/

    public PermutSimple calculerCroisement(PermutSimple i2) {
        PermutSimple permutSimple=new PermutSimple(inst);

        //tirer au hasard 2 int
        Random r = new Random();
        int d = r.nextInt(inst.getK());
        int f = r.nextInt(inst.getK());
        if(d>f){ int u=f; f=d; d=f; }

        //on prend les elements de ind1 qui sont dans l'interval
        //et compléter avec ceux de ind2
        for (int i=0; i<d; i++){
            permutSimple.permut.add(i2.permut.get(i));
        }
        for (int i=d; i<=f; i++){
            permutSimple.permut.add(permut.get(i));
        }
        if(f<inst.getK()){
            for (int i=f+1; i<inst.getK();i++){
                permutSimple.permut.add(i2.permut.get(i));
            }
        }

	    return permutSimple;
    }

    /**
     * @param x : indice du premier mouvement
     * @param y : indice du deuxieme mouvement
     * faire un echange entre les valeurs des indices x y
     * 
    **/
    private void mutationAux(int x, int y) {
        int stock=permut.get(x);
        permut.set(x,permut.get(y));
        permut.set(y,stock);
    }
/**
    * Faire la mutation de this comme dans le texte.
    * @return un individu fils muté de type GDBHSimple.
    * 
   **/
    public PermutSimple calculerMutation() {
        PermutSimple permutSimple=new PermutSimple(inst);
        for (int i=0; i<inst.getK();i++){
            permutSimple.permut.add(permut.get(i));
        }

        //tirer au hasard 2 int
        Random r = new Random();
        int p1 = r.nextInt(inst.getK());
        int p2 = r.nextInt(inst.getK());
        permutSimple.mutationAux(p1,p2);

        return permutSimple;
    }


}
