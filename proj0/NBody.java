public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int firstItemInFile = in.readInt();
        double radius = in.readDouble();
        return radius;

    }
    public static Planet[] readPlanets(String file){/**method with String**/
        In in = new In(file);
        int num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0;i < num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            planets[i]= new Planet(xP,yP,xV,yV,mass,img);
        }
        return planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        /**finish parsing universe parameters from input file**/

        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        for (Planet element : allPlanets){
            element.draw();
        }


        double time = 0;
        while (time<T){
            double [] xForce = new double[allPlanets.length];
            double [] yForce = new double[allPlanets.length];

            for (int i = 0; i< allPlanets.length;i++){
                xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i< allPlanets.length;i++){
                allPlanets[i].update(dt, xForce[i],yForce[i]);
            }

            for (Planet element: allPlanets){
                element.draw();

            }
            StdDraw.enableDoubleBuffering();
            StdDraw.pause(10);


            time += dt;
        }
    }

}
