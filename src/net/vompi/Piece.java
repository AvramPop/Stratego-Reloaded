package net.vompi;

/**
 * Created by dani on 1/2/17.
 */
public class Piece {
    private final int rank;
    private final String name;
    private Player owner;
    protected Field field;
    private boolean isFlagOwner = false;
    private Flag flag;

    public Piece(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void takeFlag(Flag flagToTake){
        isFlagOwner = true;
        flag = flagToTake;
    }

    public void leaveFlag(){
        isFlagOwner = false;
        flag = null;
    }

    public void move(Field fieldToMoveIn){
        if(fieldToMoveIn.isInProximity(field)) {
            moveActually(fieldToMoveIn);
        }
    }

    protected void moveActually(Field fieldToMoveIn) {
        if (fieldToMoveIn.isEmpty()) {
            fieldToMoveIn.occupy(this);
            this.field = fieldToMoveIn;
            System.out.println("You have successfully moved to field (" + fieldToMoveIn.x + " ," + fieldToMoveIn.y + ")");
        } else if (fieldToMoveIn.getOwner().getOwner() == this.owner) {
            System.out.println("Cannot move over friendly soldier");
        } else {
            attack(fieldToMoveIn);
        }
    }

    public void setField(Field field) {
        this.field = field;
    }

    protected void attack(Field fieldOfPieceToAttack){
        System.out.println("You have attacked a " + fieldOfPieceToAttack.getOwner().getName() +
                "(rank " + fieldOfPieceToAttack.getOwner().getRank() + ")");
        if(this.rank > fieldOfPieceToAttack.getOwner().getRank()){
            this.move(fieldOfPieceToAttack);
            this.field = fieldOfPieceToAttack;
            fieldOfPieceToAttack.getOwner().getOwner().addToRecycleBin(fieldOfPieceToAttack.getOwner());
            this.owner.increaseKills();
            System.out.println("You have won the fight");
            System.out.println("You have successfully moved to field ("
                    + fieldOfPieceToAttack.x + " ," + fieldOfPieceToAttack.y + ")");
        } else if(this.rank < fieldOfPieceToAttack.getOwner().getRank()){
            goToRecycleBin();
            System.out.println("You have lost the fight");
            fieldOfPieceToAttack.getOwner().getOwner().increaseKills();
        } else {
            goToRecycleBin();
            System.out.println("Tie. Both soldiers die!");
            fieldOfPieceToAttack.getOwner().goToRecycleBin();
        }
    }

    protected void goToRecycleBin(){
        owner.addToRecycleBin(this);
    }
}
