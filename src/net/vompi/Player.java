package net.vompi;

import java.util.ArrayList;

/**
 * Created by dani on 1/2/17.
 */
public class Player {
    private int kills = 0;
    private ArrayList<Piece> recycleBin = new ArrayList<>();
    public final String name;

    public Player(String name) {
        this.name = name;
    }

    public void chooseFlagOwner(Piece flagOwner){

    }

    public void ressurectPiece(Piece pieceToRessurect) throws IllegalArgumentException{
        if(recycleBin.contains(pieceToRessurect)) {
            //resurrect
            System.out.println("Your " + pieceToRessurect.getName() + " has been resurrected");
            return;
        }
        throw new IllegalArgumentException("Cannot ressurect a piece that has not died");
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
}
