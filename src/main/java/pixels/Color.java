/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

public class Color {
  static public int alpha(int rgb) {
    return (rgb >> 16) & 0xff;
  }
    
  static public int red(int rgb) {
    return (rgb >> 16) & 0xff;
  }
    
  static public int green(int rgb) {
    return (rgb >> 8) & 0xff;
  }
    
  static public int blue(int rgb) {
    return (rgb) & 0xff;
  }
}
