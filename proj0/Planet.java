public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * calculate distance between two planet
     */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculate force
     */
    public double calcForceExertedBy(Planet p) {
        return G * p.mass * this.mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return G * p.mass * this.mass / (this.calcDistance(p) * this.calcDistance(p)) * dx / this.calcDistance(p) ;
    }

    public double calcForceExertedByY(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return G * p.mass * this.mass / (this.calcDistance(p) * this.calcDistance(p)) * dy / this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double NetForceX = 0;
        for(Planet element: allPlanets){
            if (!this.equals(element)){
                NetForceX += calcForceExertedByX(element) ;
            }
        }
        return NetForceX;
    }
     public double calcNetForceExertedByY(Planet[] allPlanets){
        double NetForceY = 0;
        for(Planet element:allPlanets){
            if(!this.equals(element)){
                NetForceY += calcForceExertedByY(element) ;
            }
        }
        return NetForceY;
     }
     public void update(double dt, double fX, double fY){
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        xxVel = this.xxVel + dt*aX;
        yyVel = this.yyVel + dt*aY;
        xxPos = this.xxPos + dt*xxVel;
        yyPos = this.yyPos + dt*yyVel;
     }
     public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);

     }
}