public class Link {
    private double[] startPos = new double[2];
    private double angle, length;

    public Link(double[] startPos, double angle, double length){
        this.startPos = startPos;
        this.angle=angle;
        this.length=length;
    }
    public Link(double angle, double length){
        this.startPos = new double[]{0,0};
        this.angle=angle;
        this.length=length;
    }

    public double[] end(){
        double[] end = new double[2];
        end[0]= startPos[0]+length*Math.cos(angle);
        end[1]=startPos[1]+length*Math.sin(angle);
        return end;
    }

    public boolean anglePlus(double[] target, Link x){
        Link temp = new Link(startPos,angle+.000001,length);
        x.setStartPos(end());
        double og = x.distance(target);
        x.setStartPos(temp.end());
        double New = x.distance(target);
        return og > New;
    }
    public boolean anglePlus(double[] target){
        Link temp = new Link(startPos,angle+.000001,length);
        return distance(target) > temp.distance(target);
    }

    public boolean angleMin(double[] target, Link x){
        Link temp = new Link(startPos,angle-.000001,length);
        x.setStartPos(end());
        double og = x.distance(target);
        x.setStartPos(temp.end());
        double New = x.distance(target);
        return og > New;
    }    public boolean angleMin(double[] target){
        Link temp = new Link(startPos,angle-.000001,length);
        return distance(target) > temp.distance(target);
    }


    public double distance(double[] target){
        return Math.hypot(target[0]- end()[0], target[1] - end()[1]);
    }
    public double getAngle() {
        return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double[] getStartPos() {
        return startPos;
    }
    public void setStartPos(double[] startPos) {
        this.startPos = startPos;
    }


}
