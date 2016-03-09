public class NBody {

    public static void main(String[] args) {

        // Simulation duration
        double T = Double.parseDouble(args[0]);

        // Simulation frame
        double dt = Double.parseDouble(args[1]);

        // Simulation data file
        String filename = args[2];

        Planet[] planets = readPlanets(filename);

        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);

        // Background picture
        StdDraw.picture(0, 0, "images/starfield.jpg");

        StdAudio.play("audio/2001.mid");

        double time = 0;

        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yforces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yforces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yforces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show(10);
            time += dt;
        }
        
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

        StdOut.close();
    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        double universeRadius = in.readDouble();
        return universeRadius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        double universeRadius = in.readDouble();
        Planet[] planets = new Planet[numberOfPlanets];
        for (int i =0; i<numberOfPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        return planets;
    }

}
