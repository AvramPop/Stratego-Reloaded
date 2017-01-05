package net.vompi;

import java.util.ArrayList;

/**
 * Created by dani on 1/2/17.
 */
public class Player {
    private int kills = 0;
    private ArrayList<Piece> recycleBin = new ArrayList<>();
    public final String name;
    public final String color;
    public ArrayList<Piece> pieces = new ArrayList<>();
    public final int MINIMUM_START_X;
    public final int MINIMUM_START_Y;
    public final int MAXIMUM_START_X;
    public final int MAXIMUM_START_Y;
    public final int RESURRECTION_Y;
    public final int RESURRECTION_X;
    public final int NUMBER_OF_CANNONS = 2;
    public final int NUMBER_OF_SPIES = 1;
    public final int NUMBER_OF_SCOUTS = 4;
    public final int NUMBER_OF_SERGEANTS = 3;
    public final int NUMBER_OF_LIEUTENANTS = 3;
    public final int NUMBER_OF_CAPTAINS = 3;
    public final int NUMBER_OF_COLONELS = 2;
    public final int NUMBER_OF_GENERALS = 1;
    public final int NUMBER_OF_MARSHALS = 1;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        switch(color){
            case "Red":
                MINIMUM_START_X = 4;
                MAXIMUM_START_X = 10;
                MINIMUM_START_Y = 12;
                MAXIMUM_START_Y = 14;
                RESURRECTION_X = 13;
                RESURRECTION_Y = 7;
                break;
            case "Blue":
                MINIMUM_START_X = 0;
                MAXIMUM_START_X = 2;
                MINIMUM_START_Y = 4;
                MAXIMUM_START_Y = 10;
                RESURRECTION_X = 1;
                RESURRECTION_Y = 7;
                break;
            case "Yellow":
                MINIMUM_START_X = 4;
                MAXIMUM_START_X = 10;
                MINIMUM_START_Y = 12;
                MAXIMUM_START_Y = 14;
                RESURRECTION_X = 7;
                RESURRECTION_Y = 13;
                break;
            default: //"Green":
                MINIMUM_START_X = 4;
                MAXIMUM_START_X = 10;
                MINIMUM_START_Y = 0;
                MAXIMUM_START_Y = 2;
                RESURRECTION_X = 7;
                RESURRECTION_Y = 1;
//            default:
//                System.out.println("We do not have this colour in our database yet :(");
        }
        createPieces();
    }

    public void chooseFlagOwner(Piece flagOwner){
        Flag flag = new Flag(this);
        flagOwner.takeFlag(flag);
    }

    public void resurrectPiece(Piece pieceToResurrect) throws IllegalArgumentException{
        if(recycleBin.contains(pieceToResurrect)) {
            //resurrect
            System.out.println("Your " + pieceToResurrect.getName() + " has been resurrected");
            return;
        }
        throw new IllegalArgumentException("Cannot resurrect a piece that has not died");
    }

    public void putPiece(Piece pieceToPut, Field fieldToPutPieceIn){
        pieceToPut.setOwner(this);
        pieceToPut.setField(fieldToPutPieceIn);
        fieldToPutPieceIn.occupy(pieceToPut);
    }

    public void addToRecycleBin(Piece pieceToAddToRecycleBin){
        recycleBin.add(pieceToAddToRecycleBin);
    }

    public void increaseKills(){
        kills++;
        if(kills == 6){
            System.out.println("Choose a piece to resurrect");
            kills = 0;
        }
    }

    public void movePiece(Piece pieceToMove, Field fieldToMovePieceIn){
        if(hasPieceAlive(pieceToMove)){
            pieceToMove.move(fieldToMovePieceIn);
        } else {
            System.out.println(this.color + " has not any " + pieceToMove.getName() + " alive!");
        }
    }

    private boolean hasPieceAlive(Piece piece){
        int numberInGarbage = 0;
        for (int i = 0; i < recycleBin.size(); i++) {
            if(recycleBin.get(i).getRank() == piece.getRank()){
                numberInGarbage++;
            }
        }
        switch(piece.getRank()){
            case 1:
                return numberInGarbage < NUMBER_OF_CANNONS;
            case 2:
                return numberInGarbage < NUMBER_OF_SPIES;
            case 3:
                return numberInGarbage < NUMBER_OF_SCOUTS;
            case 4:
                return numberInGarbage < NUMBER_OF_SERGEANTS;
            case 5:
                return numberInGarbage < NUMBER_OF_LIEUTENANTS;
            case 6:
                return numberInGarbage < NUMBER_OF_CAPTAINS;
            case 7:
                return numberInGarbage < NUMBER_OF_COLONELS;
            case 8:
                return numberInGarbage < NUMBER_OF_GENERALS;
            case 9:
                return numberInGarbage < NUMBER_OF_MARSHALS;
            default:
                return false;
        }
    }

    private void createPieces(){
        Piece cannon = new Cannon();
        Piece spy = new Spy();
        Piece scout = new Scout();
        Piece sergeant = new Piece(4);
        Piece lieutenant = new Piece(5);
        Piece captain = new Captain();
        Piece colonel = new Piece(7);
        Piece general = new Piece(8);
        Piece marshall = new Piece(9);

        pieces.add(cannon);
        pieces.add(cannon);
        pieces.add(spy);
        pieces.add(scout);
        pieces.add(scout);
        pieces.add(scout);
        pieces.add(scout);
        pieces.add(sergeant);
        pieces.add(sergeant);
        pieces.add(sergeant);
        pieces.add(lieutenant);
        pieces.add(lieutenant);
        pieces.add(lieutenant);
        pieces.add(captain);
        pieces.add(captain);
        pieces.add(captain);
        pieces.add(colonel);
        pieces.add(colonel);
        pieces.add(general);
        pieces.add(marshall);
    }
}
