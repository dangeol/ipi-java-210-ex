package com.ipiecoles.java.java210;
//import java.util.Scanner; 

public class Main {

    public static void main(String[] args) {
        System.out.println(Sudoku.FIN_SAISIE);

        Sudoku s = new Sudoku(); // après instantation, variable non-static accéssible
        //s.resolu = true;
        //Sudoku s2 = new Sudoku();
        //System.out.println(s.getSudokuAResoudre().length);
        //Demander une saisie à l'utilisateur de 3 chiffres 1: 0-8, 2: 0-8, 3: 1-9
        
        System.out.println("");
        /*System.out.println("Saisiez trois chiffres, 1: 0-8, 2: 0-8, 3: 1-9");
        Scanner sc = new Scanner(System.in);
        String ligne = sc.nextLine();
        sc.close();

        System.out.println(Sudoku.ligneSaisieEstCoherente(ligne));*/
        //Sudoku.demandeCoordonneesSudoku();
        //String[] tabCoordonnees = Sudoku.demandeCoordonneesSudoku();
        //s.remplitSudokuATrous(Sudoku.demandeCoordonneesSudoku()); //juste pour tester, remettre après test
        s.ecrireSudoku(s.getSudokuAResoudre());
        if(s.resoudre(0, 0, s.getSudokuAResoudre())) {
            s.ecrireSudoku(s.getSudokuAResoudre());
        } else {
            System.out.println("Pas de solution...");
        }
        /*short[][] tab3 = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 8, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 7, 0, 0}
        };*/
        //s.ecrireSudoku(tab3);
        //short val = 4;
        //System.out.println(Sudoku.estAutorise(1, 3, val, s));

        //System.out.println(s.resolu);
        //System.out.println(s2.resolu);
    }
}