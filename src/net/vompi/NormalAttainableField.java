package net.vompi;

/**
 * Created by dani on 1/4/17.
 */
public class NormalAttainableField extends Field {
    public NormalAttainableField(int x, int y) {
        super(true, x, y, '#');
    }
    public NormalAttainableField(int x, int y, char code){
        super(true, x, y, code);
    }
}
