package main.java;

public class Geometry {
	static public boolean inBound(float x, float y, float rwidth, float rheight, float px, float py) {
      return 
        px >= x && px < (x + rwidth) && 
        py >= y && py < (y + rheight);
    }
}
