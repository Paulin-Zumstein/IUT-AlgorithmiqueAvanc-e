package fr.umontpellier.iut.algogen.individus;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;

import java.util.ArrayList;
import java.util.Random;

public abstract class IndividuPermut<T extends IndividuPermut<T>> implements IIndividu<T> {
    protected  Instance inst;
    protected ArrayList<Integer> permut;

    public IndividuPermut(Instance in, ArrayList<Integer> p) {
        inst=in;
        permut=new ArrayList<>(p);
    }

    public IndividuPermut(Instance in) {
        inst=in;
        permut=new ArrayList<>();
    }
    /**
     * @param c1 : premiere coordonnée
     * @param c2 : deuxieme coordonnée
     * @return le plus cours chemin entre c1 et c2
     *
     **/
    public static ArrayList<Coord> plusCourtChemin(Coord c1, Coord c2) {
        ArrayList<Coord> coords=new ArrayList<>();
        int i=1;
        if(c1.getL()>c2.getL()){
            // c2 est en haut de c1
            while (i<=c1.getL()-c2.getL()){
                Coord coord=new Coord(c1.getL()-i,c1.getC());
                coords.add(coord);
                i++;
            }
            i--;
            if(c1.getC()>c2.getC()){
                // c2 est à gauche de c1
                for(int j=1; j<=c1.getC()-c2.getC();j++){
                    Coord coord=new Coord(c1.getL()-i,c1.getC()-j);
                    coords.add(coord);
                }
            }else{
                // c2 est à droite de c1
                for(int j=1; j<=c2.getC()-c1.getC();j++){
                    Coord coord=new Coord(c1.getL()-i,c1.getC()+j);
                    coords.add(coord);
                }
            }
        }else{
            // c2 est en bas de c1
            while (i<=c2.getL()-c1.getL()){
                Coord coord=new Coord(c1.getL()+i,c1.getC());
                coords.add(coord);
                i++;
            }
            i--;
            if(c1.getC()>c2.getC()){
                // c2 est à gauche de c1
                for(int j=1; j<=c1.getC()-c2.getC();j++){
                    Coord coord=new Coord(c1.getL()+i,c1.getC()-j);
                    coords.add(coord);
                }
            }else{
                // c2 est à droite de c1
                for(int j=1; j<=c2.getC()-c1.getC();j++){
                    Coord coord=new Coord(c1.getL()+i,c1.getC()+j);
                    coords.add(coord);
                }
            }
        }
        return coords;
    }

    public Instance getInstance() {
        return inst;
    }

    @Override
    public String toString() {
        return permut + "";
    }

    @Override
    public Solution calculerSol() {
        return new Solution();
    }

    public int evaluerFitness() {
        return 0;
    }
}