package com.ipiecoles.java.java210;
import java.util.Scanner;

public class Sudoku {

	public final static String FIN_SAISIE = "FIN";
	public boolean resolu = false;
	public short[][] sudokuAResoudre;


	public short[][] getSudokuAResoudre() {
		return sudokuAResoudre;
	}

	public void setSudokuAResoudre(short[][] tableau) {
		sudokuAResoudre = tableau;
	}

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
		if (ligneSaisie == null || ligneSaisie.trim().equals("")) {
			System.out.println("Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces\n");
			return false;
		}
		else if (ligneSaisie.length() != 3) {
			System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères\n");
			return false;
		}
		else if (!ligneSaisie.matches("[0-8][0-8][1-9]")) {
			System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n");
			return false;
		}
		return true;
	}

	/**
	 * Constructeur par défaut;
	 */
	public Sudoku() {
		//sudokuAResoudre = new short[9][9];
		setSudokuAResoudre(new short[9][9]);
	}
	
	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie. 
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {
		String coord = "";
		short i = 0; 
		String[] tableau =  new String[81];
        Scanner ligne = new Scanner(System.in);
		while(i < tableau.length)
			{
				System.out.println("Saisiez trois chiffres, 1: 0-8, 2: 0-8, 3: 1-9");
				coord = ligne.nextLine();
				if (coord.equals(FIN_SAISIE)){
					break;
				}
				else if (ligneSaisieEstCoherente(coord) == true) {
					tableau[i] = coord;
					i++;
				}
			}
		ligne.close();
		return tableau;
	}
	
	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant 
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est 
	 * rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */

	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		int k = 0;
		int i = 0;
		int j = 0;
		//short val = 0;
		while(k < tableauCoordonnees.length) {
			if (tableauCoordonnees[k] == null) {
				break;
			}
			else {
				i = stringToInt(tableauCoordonnees[k].substring(0,1));
				j = stringToInt(tableauCoordonnees[k].substring(1,2));
				sudokuAResoudre[i][j] = Short.parseShort(tableauCoordonnees[k].substring(2,3));
				k++;
			}
		}
		//ecrireSudoku(sudokuAResoudre);
		//regarde alternative sur GitHub
    }
	
	private int stringToInt(String s) {
		return Integer.parseInt(s);
		//return Short.parseShort(s);
	}
	
	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 *  -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 *  -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 *  -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non). 
	 * Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur 
	 * non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */
	public void ecrireSudoku(short[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			if (i%3 == 0) {
				System.out.println(" -----------------------");
			}
			for (int j = 0; j < sudoku[i].length; j++) {
				if (j%3 == 0) {
					System.out.print("| ");	
				}
				if (sudoku[i][j] != 0)
					System.out.print(sudoku[i][j]+" ");	
				else {
					System.out.print("  ");
				}
				if (j == sudoku[i].length-1) {
					System.out.println("|");
				}	
			}
		}
		System.out.println(" -----------------------");
    }
	
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes : 
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param ligne
	 * @param colonne
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	
	public static boolean estAutorise(int ligne, int colonne, short chiffre, short[][] sudoku) {
		for (int j = 0; j < sudoku[ligne].length; j++) {
			if (sudoku[ligne][j] == chiffre) {
				return false;
			}
		}
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i][colonne] == chiffre) {
				return false;
			}
		}
		int decalageI = (ligne / 3) * 3;
		int decalageY = (colonne / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudoku[i+decalageI][j+decalageY] ==  chiffre) {
					return false;
				}
			}
		}
		return true;
    }

	public boolean resoudre(int ligne, int colonne, short[][] sudoku) {
		if (colonne == 9) {
			colonne = 0; ligne++;
			if (ligne == 9) {
				return true;
			}
		}

		if (sudoku[ligne][colonne] != 0) {
			return resoudre (ligne, colonne++, sudoku);
		}

		for (short k = 1; k <= 9; k++) {
			if (estAutorise(ligne, colonne, k, sudoku)) {
				sudoku[ligne][colonne] = k;
				if (resoudre(ligne, colonne++, sudoku)) {
					return true;
				}
			}
			else {
				sudoku[ligne][colonne] = 0;
				return false;
			}

		}
	}
	
}