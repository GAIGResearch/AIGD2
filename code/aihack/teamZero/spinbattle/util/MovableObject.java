package teamZero.spinbattle.util;

import math.Vector2d;
import teamZero.spinbattle.core.VectorField;

public class MovableObject {
    public Vector2d s;
    public Vector2d v;

    // may not need this...
    boolean isActive = false;

    public MovableObject copy() {
        MovableObject mo = new MovableObject();
        mo.s = s.copy();
        mo.v = v.copy();
        return mo;
    }

    public MovableObject update(VectorField vf) {
        if (vf == null) {
            s.add(v);
        } else {
            v.add(vf.getForce(s), vf.getForceConstant());
            s.add(v);
        }
        return this;
    }

    public String toString() {
        return s + " :: " + v;
    }

}
