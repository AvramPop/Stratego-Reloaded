package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Cannon extends Piece {
    public Cannon(int rank, String name) {
        super(rank, name);
    }

    @Override
    protected void attack(Field fieldOfPieceToAttack) {
        if((fieldOfPieceToAttack.x == this.field.x && fieldOfPieceToAttack.y == this.field.y + 3) ||
                (fieldOfPieceToAttack.x == this.field.x && fieldOfPieceToAttack.y == this.field.y - 3) ||
                (fieldOfPieceToAttack.x == this.field.x + 3 && fieldOfPieceToAttack.y == this.field.y) ||
                (fieldOfPieceToAttack.x == this.field.x - 3 && fieldOfPieceToAttack.y == this.field.y)){
            fieldOfPieceToAttack.getOwner().goToRecycleBin();
            goToRecycleBin();
            this.getOwner().increaseKills();
        } else {
            super.attack(fieldOfPieceToAttack);
        }
    }
}
