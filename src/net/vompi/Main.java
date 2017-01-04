package net.vompi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Player> players = new ArrayList<>();
    static Field map [][] = new Field[16][16];
    static int numberOfPlayers;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/data.txt"));
        numberOfPlayers = Integer.parseInt(fileScanner.nextLine());
	    createPlayers(numberOfPlayers);
	    createMapFor(numberOfPlayers);
	    printMap();
	    for(Player p : players){
	        arrangePieces(p);
        }
        while(/*(CenterField)(NormalAttainableField)map[7][7].hasBeenConquered()*/ true){
	        for(Player p : players){
	            Field field = new NormalAttainableField(5, 5);
	            Piece piece = new Piece(5);
	            p.movePiece(piece, field);
            }
        }
    }

    private static void createPlayers(int numberOfPlayers) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/data.txt"));
        fileScanner.nextLine();
        while(numberOfPlayers > 0){
            Player player = new Player(fileScanner.nextLine(), fileScanner.nextLine());
            players.add(player);
            System.out.println("Successfully created player " + player.name + " with color " + player.color);
            numberOfPlayers--;
        }
    }

    private static void printMap() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/Map.txt");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                out.print(map[i][j].code + " ");
            }
            out.print('\n');
        }
        out.close();
    }

    private static void createMapFor(int numberOfPlayers){
        Map board = new Map(numberOfPlayers);
        map = board.data;
    }

    private static void arrangePieces(Player player){
        for(Piece piece : player.pieces){
            System.out.println("Place your " + piece.getName() + " please");
            Field fieldToPutPieceIn = new NormalAttainableField(5, 1);
            if(fieldIsAvailableForPlayer(fieldToPutPieceIn, player)) {
                player.putPiece(piece, fieldToPutPieceIn);
                player.pieces.remove(piece);
            } else {
                System.out.println("This place cannot be used by the " + player.color + " as a starting point");
            }
        }
    }

    private static boolean fieldIsAvailableForPlayer(Field fieldToPutPieceIn, Player player){
        return (fieldToPutPieceIn.x >= player.MINIMUM_START_X && fieldToPutPieceIn.y >= player.MINIMUM_START_Y
                && fieldToPutPieceIn.x <= player.MAXIMUM_START_X && fieldToPutPieceIn.y <= player.MAXIMUM_START_Y
                && fieldToPutPieceIn.isEmpty()
                && fieldToPutPieceIn.x != player.RESURRECTION_X && fieldToPutPieceIn.y != player.RESURRECTION_Y);
    }
}
