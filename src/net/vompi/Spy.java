package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Spy extends Piece {
    public Spy(int rank, String name) {
        super(rank, name);
    }

    @Override
    protected void attack(Field fieldOfPieceToAttack) {
        if(fieldOfPieceToAttack.getOwner().getRank() == 9){
            fieldOfPieceToAttack.getOwner().goToRecycleBin();
            this.getOwner().increaseKills();
            goToRecycleBin();
        } else {
            super.attack(fieldOfPieceToAttack);
        }
    }
}
