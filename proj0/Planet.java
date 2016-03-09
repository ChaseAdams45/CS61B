public class Planet {
    /* Its current x position
     */
    public double xxPos;
    /* Its current y position
     */
    public double yyPos;
    /* Its current velocity in the x direction
     */
    public double xxVel;
    /* Its current velocity in the y direction
     */
    public double yyVel;
    /* Its mass
     */
    public double mass;
    /* The name of an image in the images directory that depicts the planet
     */
    public String imgFileName;


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



    public double calcForceExertedBy(Planet p) {
        /* the force exerted on this planet by the given planet
           F = G * m1 * m2 / r^2
           gravitational constant G (6.67 * 10^-11 N-m^2 / kg^2)
         */

        return 6.67e-11 * this.mass * p.mass /
                (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcDistance(Planet p) {
        /* calculates the distance between two planets.
           r2 = dx^2 + dy^2
         */

        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedByX(Planet p) {
        /* force exerted on this planet by the given planet
           in the X direction
         */

        if (this.equals(p)) return 0;
        double dx = p.xxPos - this.xxPos;

        return (calcForceExertedBy(p)*dx) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        /* force exerted on this planet by the given planet
           in the Y direction
         */
        if (this.equals(p)) return 0;
        double dy = p.yyPos - this.yyPos;

        return (calcForceExertedBy(p)*dy) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        /* calculates the net X force exerted by all planets
           in the array upon the current planet
         */
        double result = 0;
        for (Planet p : planets) {
            result += this.calcForceExertedByX(p);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        /* calculates the net Y force exerted by all planets
           in the array upon the current planet
         */
        double result = 0;
        for (Planet p : planets) {
            result += this.calcForceExertedByY(p);
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        /* determines how much the forces exerted on the planet
        will cause that planet to accelerate, and the resulting
        change in the planet's velocity and position in a small
        period of time dt.
        Acceleration:
        ax = Fx / m
        ay = Fy / m
        New velocity: (vx + dt * ax, vy + dt * ay)
        New position: (px + dt * vx, py + dt * vy)
         */
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel += dt * aX;
        this.yyVel += dt * aY;

        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;

    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }


}
