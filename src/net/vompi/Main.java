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

    //red dreapta green stanga yellow jos blue sus
    public static void main(String[] args) throws FileNotFoundException {
        initialisations();
        pieceArrangementPhase();
        theGameItself();
        theFinal();
    }

    private static void theFinal(){
        System.out.println("The player: " + /*map[7][7].getOwner().getOwner().name + */" has won!");
    }

    private static void theGameItself() {
        while(((CenterField)map[7][7]).hasBeenConquered()){
	        for(Player p : players){
	            Field field = new NormalAttainableField(5, 5);
	            Piece piece = new Piece(5);
	            p.movePiece(piece, field);
            }
        }
    }

    private static void pieceArrangementPhase() throws FileNotFoundException {
        for(Player p : players){
            arrangePieces(p);
}
    }

    private static void initialisations() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/players.in"));
        numberOfPlayers = Integer.parseInt(fileScanner.nextLine());
        createPlayers(numberOfPlayers);
        createMapFor(numberOfPlayers);
        printMap();
    }

    private static void createPlayers(int numberOfPlayers) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/players.in"));
        fileScanner.nextLine();
        while(numberOfPlayers > 0){
            Player player = new Player(fileScanner.nextLine(), fileScanner.nextLine());
            players.add(player);
            System.out.println("Successfully created player " + player.name + " with color " + player.color);
            numberOfPlayers--;
        }
    }

    private static void printMap() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/Map.out");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(map[i][j].isEmpty()){
                    out.print(map[i][j].code + " ");
                } else {
                    out.print(map[i][j].getOwner().getCode());
                }
            }
            out.print('\n');
        }
        out.close();
    }

    private static void createMapFor(int numberOfPlayers){
        Map board = new Map(numberOfPlayers);
        map = board.data;
    }

    private static void arrangePieces(Player player) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/pieces.in"));
        fileScanner.nextLine();
        int x, y;
        for(Piece piece : player.pieces){
          //  System.out.println("Place your " + piece.getName() + " please");
            x = fileScanner.nextInt();
            y = fileScanner.nextInt();
            Field fieldToPutPieceIn = new NormalAttainableField(x, y);
            if(fieldIsAvailableForPlayer(fieldToPutPieceIn, player)) {
                player.putPiece(piece, fieldToPutPieceIn);
                //player.pieces.remove(piece);
                System.out.println(player.name + "'s " + piece.getName() + " has been successfully placed at field (" + x + ", " + y + ")");
            } else {
                System.out.println("(" + x + ", " + y + ")" + " cannot be used by the " + player.color + " player as a starting point");
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
