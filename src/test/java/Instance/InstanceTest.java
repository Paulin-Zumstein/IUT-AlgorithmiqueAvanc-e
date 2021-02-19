package Instance;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import fr.umontpellier.iut.algogen.Coord;
import fr.umontpellier.iut.algogen.Instance;
import fr.umontpellier.iut.algogen.Solution;


public class InstanceTest {


	@Test
	public void testPiecePresente() throws Exception {
		 boolean[][] p4 = new boolean[10][10];
	        for (int i = 0; i < p4.length; i++) {
	            for (int j = 0; j < p4[0].length; j+=2) {
	                
	                    p4[i][j] = true;
	                
	            }
	        }
	        Coord sp4 = new Coord(9, 5);
	        int k4 = p4.length * p4.length / 10;
	        Instance in4 = new Instance(p4, sp4, k4);
	        for (int i = 0; i < p4.length; i++) {
	            for (int j = 0; j < p4[0].length; j+=2) {
	                
	                    assertEquals(true, in4.piecePresente(new Coord(i, j)));
	                
	            }
	        }	}

	@Test
	public void testEstValide() throws Exception {
		
        boolean[][] p4 = new boolean[10][10];
        for (int i = 0; i < p4.length; i++) {
            for (int j = 0; j < p4[0].length; j+=2) {
                
                    p4[i][j] = true;
                
            }
        }
        Coord sp4 = new Coord(9, 5);
        int k4 = p4.length * p4.length / 10;
        Instance in4 = new Instance(p4, sp4, k4);
        Solution s = new Solution();
        s.add(new Coord(9, 5));
        s.add(new Coord(9, 6));
        s.add(new Coord(8, 6));
        s.add(new Coord(7, 6));
        s.add(new Coord(6, 6));
        s.add(new Coord(5, 6));
        s.add(new Coord(4, 6));
        s.add(new Coord(3, 6));
        s.add(new Coord(2, 6));
        s.add(new Coord(1, 6));
        s.add(new Coord(0, 6));
        assertEquals(true,in4.estValide(s));
		
	}

	@Test
	public void testEvaluerSolution() throws Exception {
		  boolean[][] p4 = new boolean[10][10];
	        for (int i = 0; i < p4.length; i++) {
	            for (int j = 0; j < p4[0].length; j+=2) {
	                
	                    p4[i][j] = true;
	                
	            }
	        }
	        Coord sp4 = new Coord(9, 5);
	        int k4 = p4.length * p4.length / 10;
	        Instance in4 = new Instance(p4, sp4, k4);
	        Solution s = new Solution();
	        s.add(new Coord(9, 5));
	        s.add(new Coord(9, 6));
	        s.add(new Coord(8, 6));
	        s.add(new Coord(7, 6));
	        s.add(new Coord(6, 6));
	        s.add(new Coord(5, 6));
	        s.add(new Coord(4, 6));
	        s.add(new Coord(3, 6));
	        s.add(new Coord(2, 6));
	        s.add(new Coord(1, 6));
	        s.add(new Coord(0, 6));
	        assertEquals(10,in4.evaluerSolution(s));
	        }

	@Test
	public void testGreedySolver() throws Exception {
		  boolean[][] p4 = new boolean[10][10];
        for (int i = 0; i < p4.length; i++) {
            for (int j = 0; j < p4[0].length; j+=2) {
                
                    p4[i][j] = true;
                
            }
        }
        Coord sp4 = new Coord(9, 5);
        int k4 = p4.length * p4.length / 10;
        Instance in4 = new Instance(p4, sp4, k4);
        Solution s = new Solution();
        s.add(new Coord(9, 5));
        s.add(new Coord(9, 4));
        s.add(new Coord(8, 4));
        s.add(new Coord(7, 4));
        s.add(new Coord(6, 4));
        s.add(new Coord(5, 4));
        s.add(new Coord(4, 4));
        s.add(new Coord(3, 4));
        s.add(new Coord(2, 4));
        s.add(new Coord(1, 4));
        s.add(new Coord(0, 4));
        assertEquals(10,in4.evaluerSolution(s));
	}

	@Test
	public void testGreedyPermut() throws Exception {
		boolean[][] p4 = new boolean[10][10];
        for (int i = 0; i < p4.length; i++) {
            for (int j = 0; j < p4[0].length; j+=2) {
                
                    p4[i][j] = true;
                
            }
        }
        Coord sp4 = new Coord(9, 5);
        int k4 = p4.length * p4.length / 10; // pour recupurer toutes les pieces, comme le test le suggere il faut faire plus de 10 pas mdr, genre 100 c good
        Instance in4 = new Instance(p4, sp4, k4);
        ArrayList<Integer> s = new ArrayList<>();
        int[] ref = {47, 42, 37, 32, 27, 22, 17, 12, 7, 2, 1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0, 1, 2, 3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 49, 44, 39, 34, 29, 24, 19, 14, 9, 4};
        int[] values = new int[]{47, 42, 37, 32, 27, 22, 17, 12, 7, 2, 1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0, 3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 49, 44, 39, 34, 29, 24, 19, 14, 9, 4};
        System.out.println(values.length);

        for(int a : values)
        	s.add(a);
        assertTrue(s.equals(in4.greedyPermut()));	
        }

}
