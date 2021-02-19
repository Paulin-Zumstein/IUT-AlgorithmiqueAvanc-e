package fr.umontpellier.iut.algogen;

import fr.umontpellier.iut.algogen.individus.PermutSimple;

import java.util.ArrayList;

public class Instance {

    ////////////////////////////////////////////////// attribut : //////////////////////////////////////////////////////

    private  Coord startingP;
    private  int k;
    private boolean[][] plateau;
    private ArrayList<Coord> listeCoordPieces;


    //////////////////////////////////////////////// constructeur : ////////////////////////////////////////////////////

    /**
     * @param p : grille du jeu
     * @param s : coordonnée de depart
     * @param kk : nombre de pas autorisé
     *
     **/
    public Instance(boolean[][] p, Coord s, int kk) {
        /*if(p==null || s ==null || kk<0){
            throw new java.lang.NullPointerException();
        }
        else {*/
        plateau = p;
        startingP = s;
        k = kk;
        initListeCoordPieces();
        // }
    }


    /////////////////////////////////////////////// initialisateur : ///////////////////////////////////////////////////

    private void initListeCoordPieces() {
        //fixme laisser ce code, car si ils font les boucles dans l'autre sens, les numéros des pièces seront différents, et du coup le sens des invididuPermut (qui sont basés
        //sur les numéros de pièces justement) sera différent
        listeCoordPieces = new ArrayList<>();
        for (int l = 0; l < getNbL(); l++) {
            for (int c = 0; c < getNbC(); c++) {
                if (piecePresente(new Coord(l, c))) {
                    listeCoordPieces.add(new Coord(l, c));
                }
            }
        }
    }


    ////////////////////////////////////////////////// testeur : ///////////////////////////////////////////////////////

    /**
     * @param c : coordonnées
     *@return vrai si la piéce de coordonées c est presente dans la grille
     **/
    public boolean piecePresente(Coord c) {
        return plateau[c.getL()][c.getC()];
    }

    /**
     * @param s : Solution
     *@return vrai la solutions est valide, c-a-d depuis le point de départ on a fait k pas sans sortir de la grille.
     **/
    public boolean estValide(Solution s) {
        int cpt = 0;
        boolean retour = true;

        // arret de la boucle quand on a tout parcouru ou quand une coord n'est pas valide
        while (cpt < s.size() && retour )
        {
            retour = s.get(cpt).estDansPlateau(getNbL(),getNbC());
            if (cpt > 0)
            {
                retour = s.get(cpt-1).estADistanceUn(s.get(cpt));
            }
            cpt++;
        }
        return retour;
    }

    /**
     * @param s : Solution
     *@return le nombre de piéces récolté
     **/
    public int evaluerSolution(Solution s) {
        int cpt = 0;
        if (startingP==null) throw new java.lang.NullPointerException();
        if (estValide(s)) {
            for (Coord c : s) {
                if (piecePresente(c)) {
                    cpt++;
                }
            }
        }
        return cpt;
    }


    //////////////////////////////////////////////// algo glouton : ////////////////////////////////////////////////////

    /**
     * Le solveur glouton
     *
     *@return Une solution de l'instance.
     **/
    public Solution greedySolver() {
        Solution solution = new Solution();
        Coord courant = startingP;
        ArrayList<Coord> reste = new ArrayList(listeCoordPieces);

        int cpt = 0;
        int i = 0;
        if (startingP != null){
            solution.add(startingP);
        }


        // tant qu'il y a des pas, et que la solution ne résout pas le pb
        while (cpt < k && reste.size() > 0){

            ArrayList<Integer> listOrdrePiece = greedyPermut();
            Coord cible =  listeCoordPieces.get(listOrdrePiece.get(i));

            i++;

            // tant qu'il reste des pas, et que la coord courante n'est pas sur une piece
            while (cpt < k && (courant.getL() != cible.getL() || courant.getC() != cible.getC()) ){
                solution.add(deplacerDeUnVers(courant,cible));
                courant = deplacerDeUnVers(courant,cible);
                cpt++;
            }
        }

        return solution;
    }

    // retourne la coord de la piece la plus proche de la coord courant
    public Coord plusProche(ArrayList<Coord> liste, Coord courant){

        Coord retourne = liste.get(0);
        int comparateur = liste.get(0).distanceFrom(courant);


        for (int i = 1; i < liste.size(); i++){
            if (liste.get(i).distanceFrom(courant) < comparateur){
                retourne = liste.get(i);
                comparateur = retourne.distanceFrom(courant);
            }
        }
        return retourne;
    }

    // courant se rapproche de la coord ou il y a la piece
    public Coord deplacerDeUnVers(Coord courant,Coord cible){
        Coord c = new Coord(courant.getL(), courant.getC());
        if (courant.getC() < cible.getC()){
            c = new Coord(courant.getL(), courant.getC()+1);
        }
        else if (courant.getC() > cible.getC()){
            c = new Coord(courant.getL(), courant.getC()-1);
        }
        else if (courant.getL() < cible.getL()){
            c = new Coord(courant.getL()+1, courant.getC());
        }
        else if (courant.getL() > cible.getL()){
            c = new Coord(courant.getL()-1, courant.getC());
        }
        return c;
    }


    public ArrayList<Integer> greedyPermut() {
        ArrayList<Integer> greedypermut = new ArrayList<Integer>();

        /*
        Solution soluce = greedySolver();
        for(Coord coord : soluce){
            if(listeCoordPieces.contains(coord)){
                if(!greedypermut.contains(listeCoordPieces.indexOf(coord))){
                    greedypermut.add(listeCoordPieces.indexOf(coord));
                }
            }
        }
        if (greedypermut.size()==0){
            greedypermut=null;
            greedypermut.add(4);
        }
        //System.out.println(greedypermut);
        //System.out.println(greedypermut.size());*/
        Coord courant = startingP;
        ArrayList<Coord> reste = new ArrayList(listeCoordPieces);


        //  et que la solution ne résout pas le pb
        while ( reste.size() > 0){
            Coord cible = plusProche(reste, courant);
            courant = cible;
            greedypermut.add(listeCoordPieces.indexOf(courant));
            reste.remove(cible);
        }

        return greedypermut;
    }


    ////////////////////////////////////////////////// toString : //////////////////////////////////////////////////////

    @Override
    public String toString() {
        //retourne une chaine représentant this, au format de votre choix
        StringBuilder res = new StringBuilder("k = " + k + "\n" + "nb pieces = " + listeCoordPieces.size() + "\nstarting point = " + startingP + "\n");
        for (int l = 0; l < getNbL(); l++) {
            for (int c = 0; c < getNbC(); c++) {
                if (piecePresente(new Coord(l, c))) {
                    res.append("x");
                } else {
                    res.append(".");
                }
            }
            res.append("\n");
        }

        return res.toString();
    }

    public String toString(Solution s) {
        //prérequis : s est valide
        //retourne une chaine sous la forme suivante
        //o!..
        //.ox.
        //.o..
        //.o..

        //où
        // '.' signifie que la solution ne passe pas là, et qu'il n'y a pas de pièce
        // 'x' signifie que la solution ne passe pas là, et qu'il y a pas une pièce
        // 'o' signifie que la solution passe par là, et qu'il n'y a pas de pièce
        // '!' signifie que la solution passe par là, et qu'il y a une pièce

        // dans l'exemple ci-dessus, on avait donc 2 pièces dans l'instance (dont 1 ramassée par s)
        //et la chaîne de l'exemple contient 4 fois le caractère "\n" (une fois à chaque fin de ligne)

        StringBuilder res = new StringBuilder("");//\n k = " + k + "\n" + "nb pieces = " + listeCoordPieces.size() + "\n");
        for (int l = 0; l < getNbL(); l++) {
            for (int c = 0; c < getNbC(); c++) {
                if (s.contains(new Coord(l, c)) && piecePresente(new Coord(l, c))) {
                    res.append("!");
                }
                if (!s.contains(new Coord(l, c)) && piecePresente(new Coord(l, c))) {
                    res.append("x");
                }
                if (s.contains(new Coord(l, c)) && !piecePresente(new Coord(l, c))) {
                    res.append("o");
                }
                if (!s.contains(new Coord(l, c)) && !piecePresente(new Coord(l, c))) {
                    res.append(".");
                }
            }
            res.append("\n");
        }
        return res.toString();
    }


    /////////////////////////////////////////////////// getter : ///////////////////////////////////////////////////////

    public int getNbL() {
        return plateau.length;
    }

    public int getNbC() {
        return plateau[0].length;
    }

    public Coord getStartingP() {
        return startingP;
    }

    public int getK() {
        return k;
    }

    public ArrayList<Coord> getListeCoordPieces() {
        return listeCoordPieces;
    }

}