package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public abstract class Field {
    private final boolean canBeAttained;
    private boolean isEmpty = true;
    private Piece owner;
    public final char code;
    public final int x;
    public final int y;
    public final int [] horizontalDirections = {1, -1, 0, 0};
    public final int [] verticalDirections = {0, 0, 1, -1};

    public Field(boolean canBeAttained, int x, int y, char code) {
        this.canBeAttained = canBeAttained;
        this.x = x;
        this.y = y;
        this.code = code;
    }

    public boolean canBeAttained() {
        return canBeAttained;
    }

    public void occupy(Piece piece){
        isEmpty = false;
        owner = piece;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void free(){
        isEmpty = true;
        owner = null;
    }

    public Piece getOwner() {
        return owner;
    }

    public boolean isInProximity(Field field){
        for(int i = 0; i < 4; i++){
            if(this.x + verticalDirections[i] == field.x && this.y + horizontalDirections[i] == field.y){
                return true;
            }
        }
        return false;
    }
}