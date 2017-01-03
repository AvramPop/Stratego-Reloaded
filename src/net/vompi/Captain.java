package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Captain extends Piece {

    public Captain(int rank, String name) {
        super(rank, name);
    }

    @Override
    public void move(Field fieldToMoveIn) {
        for(int i = 0; i < 4; i++){
            Field field = new Field(true,
                    this.field.x + fieldToMoveIn.verticalDirections[i],
                    this.field.y + fieldToMoveIn.horizontalDirections[i], false);
            if(fieldToMoveIn.isInProximity(field)){
                moveActually(fieldToMoveIn);
            }
        }
    }
}
