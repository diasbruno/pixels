package main.java;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class ColorPicker {
    int x, y, w, h, c;
    PImage cpImage;
    PGraphics g;
  
    public ColorPicker (PGraphics g, int x, int y, int w, int h, int c )
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
    
        this.cpImage = new PImage( w, h );
        this.g = g;
    
        init();
    }
  
    private void init ()
    {
        // draw color.
        int cw = w - 60;
        for( int i = 0; i < cw; i++ ) 
            {
                float nColorPercent = i / (float)cw;
                float rad = (float) ((-360 * nColorPercent) * (Math.PI / 180));
                int nR = (int)(Math.cos(rad) * 127 + 128) << 16;
                int nG = (int)(Math.cos(rad + 2 * Math.PI / 3) * 127 + 128) << 8;
                int nB = (int)(Math.cos(rad + 4 * Math.PI / 3) * 127 + 128);
                int nColor = nR | nG | nB;
      
                setGradient( i, 0, 1, h/2, 0xFFFFFF, nColor );
                setGradient( i, (h/2), 1, h/2, nColor, 0x000000 );
            }
    
        // draw black/white.
        drawRect( cw, 0,   30, h/2, 0xFFFFFF );
        drawRect( cw, h/2, 30, h/2, 0 );
    
        // draw grey scale.
        for(int j = 0; j < h; j++) {
        	int g = 255 - (int)(j/(float)(h-1) * 255 );
                drawRect( w-30, j, 30, 1, this.g.color( g, g, g ) );
            }
    }
    
    public int alpha(int rgb) {
    	return (rgb >> 16) & 0xff;
    }
    
    public int red(int rgb) {
    	return (rgb >> 16) & 0xff;
    }
    
    public int green(int rgb) {
    	return (rgb >> 8) & 0xff;
    }
    
    public int blue(int rgb) {
    	return (rgb) & 0xff;
    }

    private void setGradient(int x, int y, float w, float h, int c1, int c2 )
    {
        float deltaR = red(c2) - red(c1);
        float deltaG = green(c2) - green(c1);
        float deltaB = blue(c2) - blue(c1);

        for (int j = y; j<(y+h); j++)
            {
                int c = this.g.color( red(c1)+(j-y)*(deltaR/h), green(c1)+(j-y)*(deltaG/h), blue(c1)+(j-y)*(deltaB/h) );
                cpImage.set( x, j, c );
            }
    }
  
    private void drawRect( int rx, int ry, int rw, int rh, int rc )
    {
        for(int i=rx; i<rx+rw; i++) 
            {
                for(int j=ry; j<ry+rh; j++) 
                    {
                        cpImage.set( i, j, rc );
                    }
            }
    }
  
    public void render (boolean mousePressed, int mouseX, int mouseY)
    {
        this.g.image( cpImage, x, y );
        if (mousePressed &&
            mouseX >= x && 
            mouseX < x + w &&
            mouseY >= y &&
            mouseY < y + h ) {
            c = this.g.get( mouseX, mouseY );
        }
        this.g.fill( c );
        this.g.rect( x, y+h+10, 20, 20 );
    }
}
