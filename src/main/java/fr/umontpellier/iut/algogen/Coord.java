package fr.umontpellier.iut.algogen;

import java.util.Objects;

public class Coord {
    private final int l;
    private final int c;

    public Coord(int ll, int cc) {
        /*if (ll<0 || cc<0) {
            ll = ll * -1;
            cc = cc * -1;
        }*/
        this.l = ll;
        this.c = cc;
    }


    /**
     * @param nbL : nombre de lignes de la grille
     * @param nbC : nombre de colonnes de la grille
     * @return true si la coordonnée ne dépace pas les bordures de la grille
     *
     **/
    public boolean estDansPlateau(int nbL, int nbC) {
        if (l<nbL && c<nbC && 0<=l && 0<=c){
            return true;
        }
        else return false;
    }
    /**
     * @param cd : Coordonnée
     * @return true si this est de distance 1 de la coordonnée cd
     *
     **/
    public boolean estADistanceUn(Coord cd) {
        if (this.distanceFrom(cd) == 1 ) return true;
        else return false;
    }
    /**
     * @param cd : Coordonnée
     * @return distance entre this et la coordonnée cd
     *
     **/
    public int distanceFrom(Coord cd) {
        int distance = 0;

        if (cd.c>this.c){
            distance += cd.c -this.c;
        }else distance += this.c - cd.c;

        if (cd.l>this.l){
            distance += cd.l -this.l;
        }else distance += this.l - cd.l;

        return distance;
    }

    public String toString() {
        return "(" + l + "," + c + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return l == coord.l &&
                c == coord.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(l, c);
    }


    public int getL() {
        return this.l;
    }


    public int getC() {
        return this.c;
    }


}