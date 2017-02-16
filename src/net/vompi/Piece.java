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

    public Piece(int rank) {
        this.rank = rank;
        switch (rank) {
            case 1:
                this.name = "Cannon";
                break;
            case 2:
                this.name = "Spy";
                break;
            case 3:
                this.name = "Scout";
                break;
            case 4:
                this.name = "Sergeant";
                break;
            case 5:
                this.name = "Lieutenant";
                break;
            case 6:
                this.name = "Captain";
                break;
            case 7:
                this.name = "Colonel";
                break;
            case 8:
                this.name = "General";
                break;
            default: //case 9:
                this.name = "Marshall";
                break;
//            default:
//                System.out.println("This rank does not exist");
        }
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

    public void takeFlag(Flag flagToTake) {
        isFlagOwner = true;
        flag = flagToTake;
    }

    public boolean isFlagOwner() {
        return isFlagOwner;
    }

    public Flag getFlag() {
        return flag;
    }

    public void leaveFlagAndMove(Field fieldToMoveIn) {
        if (this.isFlagOwner) {
            boolean success = false;
            try {
                move(fieldToMoveIn);
                success = true;
            } catch (Exception e) {
                System.out.println("Cannot move to field. It is not in your proximity!");
            }
            if (success) {
                fieldToMoveIn.setFlag(this.flag);
                isFlagOwner = false;
                flag = null;
            }
        }
    }

    public void move(Field fieldToMoveIn) throws IllegalArgumentException {
        if (fieldToMoveIn.isInProximity(this.field)) {
            moveActually(fieldToMoveIn);
        } else {
            throw new IllegalArgumentException("Cannot move to that field, because it is not in your proximity.");
        }
    }

    protected void moveActually(Field fieldToMoveIn) {
        if (fieldToMoveIn.isEmpty()) {
            this.field.freeField();
            fieldToMoveIn.occupy(this);
            if (fieldToMoveIn.hasFlag()) {
                this.takeFlag(fieldToMoveIn.getFlag());
                fieldToMoveIn.setFlag(null);
            }
            this.field = fieldToMoveIn;
            System.out.println("You have successfully moved to field (" + fieldToMoveIn.x + ", " + fieldToMoveIn.y + ")");
        } else if (fieldToMoveIn.getOwner().getOwner() == this.owner) {
            System.out.println("Cannot move over friendly soldier");
        } else {
            attack(fieldToMoveIn);
        }
    }

    public void setField(Field field) {
        this.field = field;
    }

    protected void attack(Field fieldOfPieceToAttack) {
        System.out.println("You have attacked a " + fieldOfPieceToAttack.getOwner().getName() +
                "(rank " + fieldOfPieceToAttack.getOwner().getRank() + ")");
        if (this.rank > fieldOfPieceToAttack.getOwner().getRank()) {
            this.move(fieldOfPieceToAttack);
            this.field = fieldOfPieceToAttack;
            fieldOfPieceToAttack.getOwner().getOwner().addToRecycleBin(fieldOfPieceToAttack.getOwner());
            this.owner.increaseKills();
            System.out.println("You have won the fight");
            System.out.println("You have successfully moved to field ("
                    + fieldOfPieceToAttack.x + " ," + fieldOfPieceToAttack.y + ")");
        } else if (this.rank < fieldOfPieceToAttack.getOwner().getRank()) {
            goToRecycleBin();
            System.out.println("You have lost the fight");
            fieldOfPieceToAttack.getOwner().getOwner().increaseKills();
        } else {
            goToRecycleBin();
            System.out.println("Tie. Both soldiers die!");
            fieldOfPieceToAttack.freeField();
            this.field.freeField();
            fieldOfPieceToAttack.getOwner().goToRecycleBin();
        }
    }

    protected void goToRecycleBin() {
        owner.addToRecycleBin(this);
    }

    public String getCode() {
        String initialColor = findPieceOwnerColor();
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(this.getRank()));
        stringBuilder.append(initialColor);
        return stringBuilder.toString();
    }

    private String findPieceOwnerColor() {
        switch (this.getOwner().color) {
            case "Red":
                return "R";
            case "Blue":
                return "B";
            case "Yellow":
                return "Y";
            case "Green":
                return "G";
            default:
                System.err.println("Your piece owner does not have a valid color!");
                return null;
        }
    }
}
