/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

import processing.core.PApplet;

public class Pixels extends PApplet {
  ColorPicker pc;
  Grid grid;
  ControlWindow cw;

  boolean redraw = true;
  boolean inited = false;

  public Pixels() {
    super();
    cw = new ControlWindow();
    cw.show();
  }

  public void settings() {
    size(501, 501);
  }

  public void draw() {
    if (!inited) {
      grid = new Grid(10, 300);
      pc = new ColorPicker(this.g, (int)(grid.grip_size), 200, (int)(width - grid.grip_size), 200, 0);

      inited = true;
    }
    if (redraw) {
      float cps = width - grid.grip_size;
      background(60);
      fill(34, 255, 0);
      rect(grid.grip_size, 0, cps, 50);
      fill(255, 35, 0);
      rect(grid.grip_size, 50, cps, 50);
      fill(255, 35, 35);
      rect(grid.grip_size, 100, cps, 50);
      fill(255, 35, 255);
      rect(grid.grip_size, 150, cps, 50);

      fill(pc.c);
      noStroke();
      Grid.grid_draw_boxes(this.g, grid);
      stroke(0);
      Grid.grid_display(this.g, grid);
    }
    pc.render(mousePressed, mouseX, mouseY);

    redraw = false;
  }

  void draw_for_save() {
    background(60);
    fill(255);
    noStroke();
    Grid s = Grid.grid_scale(grid, 0.1f);
    Grid.grid_draw_boxes(this.g, s);
  }

  public void mouseClicked() {
    System.out.println("pressed");
    if (Geometry.inBound(grid.grip_size, 0, width - grid.grip_size, 50, mouseX, mouseY)) {
      draw_for_save();
      saveFrame();
      redraw = true;
      return;
    }

    if (Geometry.inBound(grid.grip_size, 50, width - grid.grip_size, 50, mouseX, mouseY)) {
      grid = Grid.grid_scale(grid, 0.9f);
      redraw = true;
      return;
    }

    if (Geometry.inBound(grid.grip_size, 100, width - grid.grip_size, 50, mouseX, mouseY)) {
      grid = Grid.grid_scale(grid, 1.1f);
      redraw = true;
      return;
    }

    if(Geometry.inBound(grid.grip_size, 200, width - grid.grip_size, 50, mouseX, mouseY)) {
      redraw = true;
    }

    if (Geometry.inBound(0, 0, grid.grip_size, grid.grip_size, mouseX, mouseY)) {
      int idx = Grid.grid_index_for(grid, mouseX, mouseY);
      Grid.grid_toggle_at_index(grid, idx, pc.c);
      redraw = true;
    }
  }
}
