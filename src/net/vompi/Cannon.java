package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Cannon extends Piece {
    public Cannon() {
        super(1, "Cannon");
    }

    public void shoot(Field fieldOfPieceToShoot){
        if((fieldOfPieceToShoot.x == this.field.x && fieldOfPieceToShoot.y == this.field.y + 3) ||
                (fieldOfPieceToShoot.x == this.field.x && fieldOfPieceToShoot.y == this.field.y - 3) ||
                (fieldOfPieceToShoot.x == this.field.x + 3 && fieldOfPieceToShoot.y == this.field.y) ||
                (fieldOfPieceToShoot.x == this.field.x - 3 && fieldOfPieceToShoot.y == this.field.y)){
            fieldOfPieceToShoot.getOwner().goToRecycleBin();
            goToRecycleBin();
            this.getOwner().increaseKills();
        } else {
            super.attack(fieldOfPieceToShoot);
        }
    }
}
