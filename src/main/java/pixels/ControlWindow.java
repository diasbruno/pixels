/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */
package pixels;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlWindow implements ActionListener, WindowListener, ChangeListener  {
  Pixels pr;
  Frame w;
  JPanel panel;
  JButton saveFrame;
  JButton gridDisplay;
  JButton scaleUp;
  JButton scaleDown;
  JColorChooser cc;

  public ControlWindow(Pixels pr) {
    this.pr = pr;
    w = new Frame("Controls");
    w.setBounds(0, 0, 100, 300);
    w.addWindowListener(this);
    panel = new JPanel();
    cc = new JColorChooser();
    cc.getSelectionModel().addChangeListener((ChangeListener) this);
    saveFrame = btnFactory("Save Frame", new Rectangle(0, 0, 100, 50), this);
    gridDisplay = btnFactory("Grid Display", new Rectangle(0, 50, 100, 50), this);
    scaleUp = btnFactory("Scale +", new Rectangle(0, 100, 100, 50), this);
    scaleDown = btnFactory("Scale -", new Rectangle(0, 150, 100, 50), this);
    panel.add(saveFrame, BorderLayout.CENTER);
    panel.add(gridDisplay, BorderLayout.CENTER);
    panel.add(scaleUp, BorderLayout.CENTER);
    panel.add(scaleDown, BorderLayout.CENTER);
    panel.add(cc, BorderLayout.PAGE_END);
    w.add(panel);
  }

  public JButton btnFactory(String label, Rectangle x, ActionListener l) {
    JButton btn = new JButton(label);
    btn.addActionListener(l);
    btn.setBounds(x);
    return btn;
  }

  public void show() {
    w.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(saveFrame)) {
      System.out.print("save");
      pr.saveCurrentFrame();
      return;
    }
    if (e.getSource().equals(gridDisplay)) {
      System.out.print("grid");
      pr.toggleGridDisplay();
      return;
    }
    if (e.getSource().equals(scaleUp)) {
      pr.scale(Pixels.SCALE_UP);
      return;
    }
    if (e.getSource().equals(scaleDown)) {
      pr.scale(Pixels.SCALE_DOWN);
      return;
    }
  }

  public void stateChanged(ChangeEvent e) {
    pr.cl = cc.getColor().getRGB();
  }

  @Override
  public void windowOpened(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowClosing(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowClosed(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowIconified(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowActivated(WindowEvent e) {
    System.out.println(e);
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    System.out.println(e);
  }
}
