public class NBody {

    public static double readRadius(String FileName){
        double Radius;
        In in = new In(FileName);

        in.readInt();
        Radius = in.readDouble();

        return Radius;
    }

    public static Planet[] readPlanets(String FileName){
        In in = new In(FileName);
        int N = in.readInt();
        Planet[] all_pla = new Planet[N];

        in.readDouble();
        for(int index = 0; index < N; index += 1){
            all_pla[index] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }

        return all_pla;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] all_pla = readPlanets(filename);
        double[] xForces = new double[all_pla.length];
        double[] yForces = new double[all_pla.length];

        /*设置宇宙的范围*/
        StdDraw.setScale(-radius,radius);

        StdDraw.enableDoubleBuffering();

        for (double t = 0; t <= T; t += dt) {

            for (int i = 0; i < all_pla.length; i += 1){
                xForces[i] = all_pla[i].calcNetForceExertedByX(all_pla);
                yForces[i] = all_pla[i].calcNetForceExertedByY(all_pla);
            }
            for (int j = 0; j < all_pla.length; j += 1){
                all_pla[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int index = 0; index < all_pla.length; index += 1) {
                all_pla[index].draw();
            }

            StdDraw.show();
        }
        StdOut.printf("%d\n", all_pla.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < all_pla.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    all_pla[i].xxPos, all_pla[i].yyPos, all_pla[i].xxVel,
                    all_pla[i].yyVel, all_pla[i].mass, all_pla[i].imgFileName);
        }
    }

}