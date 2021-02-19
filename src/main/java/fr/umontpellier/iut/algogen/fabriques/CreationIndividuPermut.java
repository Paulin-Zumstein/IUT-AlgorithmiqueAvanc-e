package fr.umontpellier.iut.algogen.fabriques;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.individus.GDBHSmartCrossing;
import fr.umontpellier.iut.algogen.individus.IndividuPermut;
import fr.umontpellier.iut.algogen.individus.PermutSimple;

import java.util.ArrayList;

public class CreationIndividuPermut extends ICreator<PermutSimple> {

    @Override
    protected PermutSimple create(Instance in) {
        PermutSimple permutSimple=new PermutSimple(in);
        return permutSimple;
    }

    @Override
    public ArrayList<PermutSimple> creerPopInit(Instance in, int n) {
        ArrayList<PermutSimple> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(create(in));
        }
        return res;
    }

    public static void main(String[] args) {
        boolean[][] p4 = new boolean[10][10];
        for (int i = 0; i < p4.length; i++) {
            for (int j = 0; j < p4[0].length; j+=2) {

                p4[i][j] = true;

            }
        }
        Coord sp4 = new Coord(9, 5);
        int k4 = p4.length * p4.length / 10;
        Instance in4 = new Instance(p4, sp4, k4);

        CreationIndividuPermut cr = new CreationIndividuPermut();
        PermutSimple permutSimple =cr.create(in4);
        System.out.println(permutSimple);
    }
}

