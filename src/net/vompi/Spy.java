package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Spy extends Piece {
    public Spy() {
        super(2, "Spy");
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
