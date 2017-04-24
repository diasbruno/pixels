/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

import processing.core.PApplet;

public class Pixels extends PApplet {
  static public String SCALE_UP = "SCALE_UP";
  static public String SCALE_DOWN = "SCALE_DOWN";
	
  Grid grid;
  ControlWindow cw;
  int cl;
    
  boolean redraw = true;
  boolean inited = false;
  boolean gridDisplay = true;
  
  public Pixels() {
    super();
    grid = new Grid(10, 300); 
    cw = new ControlWindow(this);
    cw.show();
  }

  public void settings() {
    size(501, 501);
  }

  public void draw() {
    if (redraw) {
      background(60);
      fill(cl);
      noStroke();
      Grid.grid_draw_boxes(this.g, grid);
      if (gridDisplay) {
    	stroke(0);
      	Grid.grid_display(this.g, grid);
      }
    }

    redraw = false;
  }

  void saveCurrentFrame() {
    background(60);
    fill(255);
    noStroke();
    Grid s = Grid.grid_scale(grid, 0.1f);
    Grid.grid_draw_boxes(this.g, s);
    saveFrame();
    redraw = true;
  }
  
  public void mouseClicked() {
    if(Geometry.inBound(grid.grip_size, 200, width - grid.grip_size, 50, mouseX, mouseY)) {
      redraw = true;
    }

    if (Geometry.inBound(0, 0, grid.grip_size, grid.grip_size, mouseX, mouseY)) {
      int idx = Grid.grid_index_for(grid, mouseX, mouseY);
      Grid.grid_toggle_at_index(grid, idx, cl);
      redraw = true;
    }
  }

  public void toggleGridDisplay() {
	// TODO Auto-generated method stub
	gridDisplay = !gridDisplay;
	redraw = true;
  }

  public void scale(String factor) {
	float s = factor == SCALE_UP ? 1.1f : 0.9f;
	grid = Grid.grid_scale(grid, s);
	redraw = true;
  }
}
