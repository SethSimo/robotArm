import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

class DrawPanel extends JPanel implements MouseListener {
    private Link x,y,z;


    public DrawPanel() {
        double[] target = {3,4};
        double[] start = {430,250};

        this.x = new Link(start,0,100);
        this.y = new Link(x.end(),0,100);
        this.z = new Link(y.end(),0,20);
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8));
        g2.setColor(Color.BLACK);

        g2.setFont(new Font("Times New Roman", Font.BOLD, 15));


        if(x != null) g.drawString("Base Angle: " + Math.round((-1*x.getAngle()/Math.PI%2)*1000)/1000.0 + " π", 50, 150);
        if (y != null && x != null) g.drawString("Link 2 Angle: " + Math.round((-1*(y.getAngle() - x.getAngle())/Math.PI%2)*1000)/1000.0 + " π", 50, 180);
        if (z != null && y != null) g.drawString("Link 3 Angle: " + Math.round((-1*(z.getAngle() - y.getAngle())/Math.PI%2)*1000)/1000.0 + " π", 50, 210);

        g2.setColor(Color.GRAY);

        g.drawLine((int) x.getStartPos()[0], (int) x.getStartPos()[1], (int) x.end()[0], (int) x.end()[1]);
        g.drawLine( (int) x.end()[0], (int) x.end()[1],(int) y.end()[0], (int) y.end()[1]);
        g.drawLine( (int) y.end()[0], (int) y.end()[1],(int) z.end()[0], (int) z.end()[1]);
        g2.setColor(Color.BLUE);
        g.drawOval((int) x.end()[0], (int) x.end()[1],5,5);
        g.drawOval((int) y.end()[0], (int) y.end()[1],5,5);

        g2.setColor(Color.RED);

        float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
        g2.setStroke(new BasicStroke(
                2,                      // line thickness
                BasicStroke.CAP_ROUND,  // makes dots rounder
                BasicStroke.JOIN_ROUND,
                0,
                dashPattern,
                0
        ));

        g2.drawOval(210,30, 440, 440);
    }

    public void mousePressed(MouseEvent e) {
        double[] target = new double[]{getMousePosition().x, getMousePosition().y};
        for (int i = 0; i <10; i++) {
            {
                findOpitmalAngleXstartYend(target, x, y);
                findOpitmalAngleXstartYend(target, y, z);
                findOptimalAngleEnd(target, z);
            }
        }
        y.setStartPos(x.end());
        z.setStartPos(y.end());
    }

    private void findOptimalAngleEnd(double[] target, Link z) {
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

    private void findOpitmalAngleXstartYend(double[] target, Link x, Link y) {
        if (x.anglePlus(target, y)) {
            while (!x.angleMin(target, y)) {
                x.setAngle(x.getAngle() + 0.0001);
            }
        } else if (x.angleMin(target, y)) {
            while (!x.anglePlus(target, y)) {
                x.setAngle(x.getAngle() - 0.0001);
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}