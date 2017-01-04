package net.vompi;

/**
 * Created by dani on 1/3/17.
 */
public class Flag {
    private final Player owner;

    public Flag(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
