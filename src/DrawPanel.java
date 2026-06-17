import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {
   private double[] target;
    private Link x,y,z;


    public DrawPanel() {
        double[] target = {3,4};
        double[] start = {100,100};

        this.x = new Link(start,0,100);
        this.y = new Link(x.end(),0,100);
        this.z = new Link(y.end(),0,20);
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8));
        g2.setColor(Color.GRAY);
        g.drawLine((int) x.getStartPos()[0], (int) x.getStartPos()[1], (int) x.end()[0], (int) x.end()[1]);
        g.drawLine( (int) x.end()[0], (int) x.end()[1],(int) y.end()[0], (int) y.end()[1]);
        g.drawLine( (int) y.end()[0], (int) y.end()[1],(int) z.end()[0], (int) z.end()[1]);
        g2.setColor(Color.BLUE);
        g.drawOval((int) x.end()[0], (int) x.end()[1],5,5);
        g.drawOval((int) y.end()[0], (int) y.end()[1],5,5);

    }

    public void mousePressed(MouseEvent e) {
       target = new double[]{getMousePosition().x, getMousePosition().y};
        for (int i = 0; i <10; i++) {
            {
                if (x.anglePlus(target, y)) {
                    while (!x.angleMin(target, y)) {
                        x.setAngle(x.getAngle() + 0.0001);
                    }
                } else if (x.angleMin(target, y)) {
                    while (!x.anglePlus(target, y)) {
                        x.setAngle(x.getAngle() - 0.0001);
                    }
                }
                if (y.anglePlus(target,z)) {
                    while (!y.angleMin(target,z)) {
                        y.setAngle(y.getAngle() + 0.0001);
                    }
                } else if (y.angleMin(target,z)) {
                    while (!y.anglePlus(target,z)) {
                        y.setAngle(y.getAngle() - 0.0001);
                    }
                }
                if (z.anglePlus(target)) {
                    while (!z.angleMin(target)) {
                        z.setAngle(z.getAngle() + 0.0001);
                    }
                } else if (z.angleMin(target)) {
                    while (!z.anglePlus(target)) {
                        z.setAngle(z.getAngle() - 0.0001);
                    }
                }
            }
        }
        y.setStartPos(x.end());
        z.setStartPos(y.end());
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}