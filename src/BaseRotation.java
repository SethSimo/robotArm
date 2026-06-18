import java.util.Scanner;
import java.util.Arrays;
public class BaseRotation {
    public static void main(String[] args)
        {
            Scanner scan = new Scanner(System.in);  // create a Scanner "object"

            double[] Start = new double[3];
            System.out.println("-- Start --");
            System.out.print("please enter the X coordinate: ");
            Start[0] = scan.nextDouble();
            System.out.print("please enter the y coordinate: ");
            Start[1] = scan.nextDouble();
            System.out.print("please enter the z coordinate: ");
            Start[2] = scan.nextDouble();

            double[] Target = new double[3];
            System.out.println("-- Target --");
            System.out.print("please enter the X coordinate: ");
            Target[0] = scan.nextDouble();
            System.out.print("please enter the y coordinate: ");
            Target[1] = scan.nextDouble();
            System.out.print("please enter the z coordinate: ");
            Target[2] = scan.nextDouble();

            double[] Lengths = new double[3];
            System.out.println("-- Lengths --");
            System.out.print("please enter the 1st Length: ");
            Lengths[0] = scan.nextDouble();
            System.out.print("please enter the 2nd Length: ");
            Lengths[1] = scan.nextDouble();
            System.out.print("please enter the 3rd Length: ");
            Lengths[2] = scan.nextDouble();

            Link x = new Link(Start,0,Lengths[0]);
            Link y = new Link(x.end(),0,Lengths[1]);
            Link z = new Link(y.end(),0,Lengths[2]);

            double Angle = Math.atan((Target[1]-Start[1])/(Target[0]-Start[0]));
            System.out.println("Angle: " + Angle);
            double[] newTarget = new double[2];
                newTarget[0] = Math.abs(Math.hypot(Start[0]- Target[0], Start[1] - Target[1]));
                newTarget[1] = Target[2];
            System.out.println("New Target(3D):" + Arrays.toString(newTarget));

            for (int i = 0; i <100; i++) {
                {
                    findOpitmalAngleXstartYend(newTarget, x, y);
                    findOpitmalAngleXstartYend(newTarget, y, z);
                    findOptimalAngleEnd(newTarget, z);
                }
            }
            y.setStartPos(x.end());
            z.setStartPos(y.end());
            System.out.println("Base Angle: " + Angle);
            System.out.println("Link 1 Angle: " + x.getAngle());
            System.out.println("Link 2 Angle: " + y.getAngle());
            System.out.println("Link 3 Angle: " + z.getAngle());



        }

    private static void findOpitmalAngleXstartYend(double[] target, Link x, Link y) {
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
    private static void findOptimalAngleEnd(double[] target, Link z) {
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


