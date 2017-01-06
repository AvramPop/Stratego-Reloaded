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
        initialisations();
        pieceArrangementPhase();
        printMapToFile();
        theGameItself();
        theFinal();
    }

    private static void theFinal(){
        System.out.println("The player: " + /*map[7][7].getOwner().getOwner().name + */" has won!");
    }

    private static void theGameItself() throws FileNotFoundException{
        Scanner keyboard = new Scanner(System.in);
        int xToMoveFrom = -1;
        int yToMoveFrom = -1;
        int xToMoveTo = -1;
        int yToMoveTo = -1;
        boolean successfulInput;

        while(!((CenterField)map[7][7]).hasBeenConquered()){
	        for(Player p : players){
	            successfulInput = false;
	            while(!successfulInput) {
                    System.out.println("The " + p.color + " player's turn. Please move");
                    System.out.println("Please insert the coordinates of the field you want to move your piece from:");
                    xToMoveFrom = keyboard.nextInt();
                    yToMoveFrom = keyboard.nextInt();
                    System.out.println("Please insert the coordinates of the field you want to move your piece to:");
                    xToMoveTo = keyboard.nextInt();
                    yToMoveTo = keyboard.nextInt();
                    if(map[xToMoveFrom][yToMoveFrom].getOwner().getOwner() == p){
                        successfulInput = true;
                    }
                }
	            p.movePiece(map[xToMoveFrom][yToMoveFrom], map[xToMoveTo][yToMoveTo]);
	            printMapToConsole();
            }
        }
    }

    private static void pieceArrangementPhase() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/AddingPieces.out");
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/pieces.in"));
        for(Player p : players){
            arrangePieces(p, out, fileScanner);
        }
        out.close();
    }

    private static void initialisations() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/players.in"));
        numberOfPlayers = Integer.parseInt(fileScanner.nextLine());
        createPlayers(numberOfPlayers);
        createMapFor(numberOfPlayers);
        printMapToFile();
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

    private static void printMapToFile() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/dani/Desktop/Stratego Reloaded/Stratego-Reloaded/data/Map.out");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(map[i][j].isEmpty()){
                    out.print(map[i][j].code + " ");
                  //  System.out.println("empty");
                } else {
                    out.print(map[i][j].getOwner().getCode() + " ");
                }
            }
            out.print('\n');
        }
        out.close();
    }

    private static void printMapToConsole() throws FileNotFoundException {
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(map[i][j].isEmpty()){
                    System.out.print(map[i][j].code + " ");
                } else {
                    System.out.print(map[i][j].getOwner().getCode() + " ");
                }
            }
            System.out.print('\n');
        }
        System.out.close();
    }

    private static void createMapFor(int numberOfPlayers){
        Map board = new Map(numberOfPlayers);
        map = board.data;
    }

    private static void arrangePieces(Player player, PrintWriter out, Scanner fileScanner) throws FileNotFoundException {
        int x, y;

        for(Piece piece : player.pieces){
          //  System.out.println("Place your " + piece.getName() + " please");
            x = fileScanner.nextInt();
            y = fileScanner.nextInt();
            if(fieldIsAvailableForPlayer(map[x][y], player, out)) {
                player.putPiece(piece, map[x][y]);
                printSuccessOfAddingPiece(player, x, y, piece, out);
            } else {
                printFailureOfAddingPiece(player, x, y, out);
            }
        }
        player.pieces.clear();
    }

    private static void printFailureOfAddingPiece(Player player, int x, int y, PrintWriter out) throws FileNotFoundException{
        out.println("(" + x + ", " + y + ")" + " cannot be used by the " + player.color + " player as a starting point");
    }

    private static void printSuccessOfAddingPiece(Player player, int x, int y, Piece piece, PrintWriter out) throws  FileNotFoundException{
        out.println(player.name + "'s " + piece.getName() + " has been successfully placed at field (" + x + ", " + y + ")");
    }

    private static boolean fieldIsAvailableForPlayer(Field fieldToPutPieceIn, Player player, PrintWriter out) throws FileNotFoundException{
        if(!(fieldToPutPieceIn.x >= player.MINIMUM_START_X && fieldToPutPieceIn.x <= player.MAXIMUM_START_X)) {
            out.println("The field of coordinates (" + fieldToPutPieceIn.x + ", " + fieldToPutPieceIn.y
                        + ") is not okay for the " + player.color + " to add his piece in. X wrong!");
            return false;
        }

        if(!(fieldToPutPieceIn.y >= player.MINIMUM_START_Y && fieldToPutPieceIn.y <= player.MAXIMUM_START_Y)){
            out.println("The field of coordinates (" + fieldToPutPieceIn.x + ", " + fieldToPutPieceIn.y
                        + ") is not okay for the " + player.color + " to add his piece in. Y wrong!");
            return false;
        }

        if(!fieldToPutPieceIn.isEmpty()){
            out.println("The field of coordinates (" + fieldToPutPieceIn.x + ", " + fieldToPutPieceIn.y + ") is not empty!");
            return false;
        }

        if(fieldToPutPieceIn.x == player.RESURRECTION_X && fieldToPutPieceIn.y == player.RESURRECTION_Y){
            out.println("Cannot add a piece to the resurrection field");
            return false;
        }

        return true;
    }
}
