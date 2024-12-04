public class NBody {
    /**
     * 从相关文件中获取宇宙的半径，使用In实例的相关methods
     * @param FileName 文件名
     * @param in 为In的实例
     * @return Radius 宇宙的半径
     */
    public static double readRadius(String FileName){
        double Radius;
        In in = new In(FileName);
        /*从输入流中读取int、double、String*/
        in.readInt();
        Radius = in.readDouble();

        return Radius;
    }

    /**
     * 获取文件中所有的星球
     * @param FileName 文件的名字
     * @param N 星球的个数
     * @return all_pla 星球的数组
     */
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

    /**
     *
     * @param T 星球运动的总时间
     * @param dt 星球运动的时间间隔
     * @param radius 宇宙的半径
     * @param xForces 各星球所受合外力的数组
     * @return void
     */
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

            /*更新各星球在x、y轴上的万有引力*/
            for (int i = 0; i < all_pla.length; i += 1){
                xForces[i] = all_pla[i].calcNetForceExertedByX(all_pla);
                yForces[i] = all_pla[i].calcNetForceExertedByY(all_pla);
            }

            /*更新各星球位置*/
            for (int j = 0; j < all_pla.length; j += 1){
                all_pla[j].update(dt, xForces[j], yForces[j]);
            }

            /*画图*/
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int index = 0; index < all_pla.length; index += 1) {
                all_pla[index].draw();
            }

            StdDraw.show();
        }

        /*打印各星球最后的信息*/
        StdOut.printf("%d\n", all_pla.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < all_pla.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    all_pla[i].xxPos, all_pla[i].yyPos, all_pla[i].xxVel,
                    all_pla[i].yyVel, all_pla[i].mass, all_pla[i].imgFileName);
        }
    }

}