/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

public class Geometry {
  static public boolean inBound(float x, float y, float rwidth, float rheight, float px, float py) {
    return 
      px >= x && px < (x + rwidth) && 
      py >= y && py < (y + rheight);
  }
}
