package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Scout extends Piece {
    public Scout() {
        super(3);
    }

    @Override
    public void move(Field fieldToMoveIn) {
        if((fieldToMoveIn.x == field.x && fieldToMoveIn.y != field.y) ||
                (fieldToMoveIn.x != field.x && fieldToMoveIn.y == field.y)){
            moveActually(fieldToMoveIn);
        }
    }
}
