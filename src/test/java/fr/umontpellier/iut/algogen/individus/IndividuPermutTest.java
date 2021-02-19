package fr.umontpellier.iut.algogen.individus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;
import fr.umontpellier.iut.algogen.fabriques.CreationIndividuPermut;

public class IndividuPermutTest {

	@Test
	public void testIndividuPermutInstance() throws Exception {
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
	        ArrayList<PermutSimple> individu = cr.creerPopInit(in4, 1);
			System.out.println("individu : " +individu);
	        ArrayList<PermutSimple> individu1 = cr.creerPopInit(in4, 1);
			System.out.println("individu1 : "+individu1);

	        assertFalse(individu.get(0).permut.equals(individu1.get(0).permut));

	        assertEquals(50,individu.get(0).permut.size());
	}

	@Test
	public void testPlusCourtChemin() throws Exception {
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
	        ArrayList<PermutSimple> individu = cr.creerPopInit(in4, 1);
	        ArrayList<Coord> res = new ArrayList<>();
	        res.add(new Coord(8,5));
	        res.add(new Coord(7,5));
	        res.add(new Coord(6,5));
	        res.add(new Coord(5,5));
	        res.add(new Coord(4,5));
	        res.add(new Coord(4,4));
			System.out.println("plusCoursChemin : "+individu.get(0).plusCourtChemin(sp4, new Coord(4,4)));
			System.out.println("res : "+res);
	        assertTrue(res.equals(individu.get(0).plusCourtChemin(sp4, new Coord(4,4))));
	}

	@Test
	public void testCalculerSol() throws Exception {
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
	        ArrayList<PermutSimple> individu = cr.creerPopInit(in4, 1);
	        individu.get(0).permut.clear();
	        individu.get(0).permut= new ArrayList<>(Arrays.asList(19, 36, 40, 23, 30, 10, 22, 9, 44, 24, 31, 39, 0, 29, 45, 13, 46, 38, 25, 17, 12, 28, 1, 49, 15, 20, 6, 11, 41, 42, 2, 33, 3, 7, 35, 26, 37, 4, 43, 32, 18, 48, 14, 8, 5, 27, 47, 34, 21, 16));

	        Solution s = new Solution();
	        s.add(new Coord(9,5));
	        s.add(new Coord(8,5));
	        s.add(new Coord(7,5));
	        s.add(new Coord(6,5));
	        s.add(new Coord(5,5));	        
	        s.add(new Coord(4,5));
	        s.add(new Coord(3,5));
	        s.add(new Coord(3,6));
	        s.add(new Coord(3,7));
	        s.add(new Coord(3,8));
	        s.add(new Coord(4,8));
		System.out.println(in4.getListeCoordPieces().get(19));
	        assertTrue(s.equals(individu.get(0).calculerSol()));
	}

	@Test
	public void testEvaluerFitness() throws Exception {
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
	        ArrayList<PermutSimple> individu = cr.creerPopInit(in4, 1);
	        individu.get(0).permut.clear();
	        individu.get(0).permut= new ArrayList<>(Arrays.asList(19, 36, 40, 23, 30, 10, 22, 9, 44, 24, 31, 39, 0, 29, 45, 13, 46, 38, 25, 17, 12, 28, 1, 49, 15, 20, 6, 11, 41, 42, 2, 33, 3, 7, 35, 26, 37, 4, 43, 32, 18, 48, 14, 8, 5, 27, 47, 34, 21, 16));
	        assertEquals(31,individu.get(0).evaluerFitness());
	}

}
