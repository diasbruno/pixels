/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

import processing.core.PGraphics;

public class Grid {
  int mat;
  float grip_size;
  float block_size;
  Pixel values[] = new Pixel[100];

  public Grid(int mat, float size) {
    this.mat = mat;
    this.grip_size = size;
    this.block_size = size / mat;
    this.values = new Pixel[100];
    this.init();
  }

  public Grid(int mat, float size, Pixel values[]) {
    this.mat = mat;
    this.grip_size = size;
    this.block_size = size / mat;
    this.values = values;
  }
	  
  void init() {
    for (int i = 0; i < this.values.length; i++) {
      this.values[i] = new Pixel(255);
    }
  }
    
  static void grid_toggle_at_index(Grid g, int i, int cl) {
    g.values[i].toggle = !g.values[i].toggle;
    g.values[i].cl = cl;
  }

  static void grid_display(PGraphics c, Grid g) {
    int i = 0;
    float bs = g.block_size;
    float gs = g.grip_size;
    for (i = 0; i <= g.mat; i++) {
      c.line(i * bs, 0, i * bs, Math.round(gs));
    }

    for (i = 0; i <= g.mat; i++) {
      c.line(0, i * bs, gs, i * bs);  
    }
  }

  static int grid_index_for(Grid g, int px, int py) {
    float bx = px / Math.round(g.block_size);
    float by = py / Math.round(g.block_size);
    return (int) (bx + (g.mat * by));
  }

  static void grid_draw_box(PGraphics c, Grid g, float px, float py, float bs, int cl) {
    c.fill(cl);
    c.rect(px, py, bs, bs);
  }

  static void grid_draw_boxes(PGraphics c, Grid g) {
    int t = g.values.length;
    float bs = g.block_size;
    for (int i = 0; i < t; i++) {
      if (g.values[i].toggle) {
        float px = (i % g.mat) * bs;
        float py = (i / g.mat) * bs;
        Grid.grid_draw_box(c, g, px, py, bs, g.values[i].cl);
      }
    }
  }

  static Grid grid_scale(Grid g, float x) {
    return new Grid(g.mat, g.grip_size * x, g.values);
  }

  static void grid_clear(Grid g) {
    int t = g.values.length;
    for (int i = 0; i < t; i++) {
      g.values[i].toggle = false;
    }
  }
}


