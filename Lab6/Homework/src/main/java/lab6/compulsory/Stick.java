
package lab6.compulsory;

import java.io.Serializable;

public class Stick implements Serializable {
    private Position start;
    private Position end;

    public Stick(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }
}