package net.vompi;

/**
 * Created by dani on 1/4/17.
 */
public final class CenterField extends Field {
    public CenterField() {
        super(true, 7, 7, '@');
    }

    public boolean hasBeenConquered(){
        return !this.isEmpty() && this.getOwner().isFlagOwner() && this.getOwner().getFlag().getOwner() == this.getOwner().getOwner();
    }
}
