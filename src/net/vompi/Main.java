package net.vompi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Player> players = new ArrayList<>();
    static Field map [][][] = new Field[16][16][2];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/data.txt"));
        int numberOfPlayers = fileScanner.nextInt();
	    createPlayers(numberOfPlayers);
	    createMapFor(numberOfPlayers);
	    printMap();
    }

    public static void createPlayers(int numberOfPlayers) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/data.txt"));
        while(numberOfPlayers > 0){
            Player player = new Player(fileScanner.nextLine());
            players.add(player);
            numberOfPlayers--;
        }
    }

    public static void printMap() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/Map.txt");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                out.print(map[i][j][0].code + " ");
            }
            out.print('\n');
        }
        out.close();
    }

    public static void createMapFor(int numberOfPlayers){
        Map board = new Map(numberOfPlayers);
        map = board.data;
    }
}
