public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    /**
     * 创建一个星球
     * @param xP x方向的位置
     * @param yP y方向的位置
     * @param xV x方向的速度
     * @param yV y方向的速度
     * @param m 物体的质量
     * @param img 物体的图片
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * 创建一个星球但该星球但参数与p相同
     *
     * @param p 被复制的星球
     */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * 计算该星球与星球p之间的距离
     * @param p 星球
     * @return 两星球之间的距离
     */
    public double calcDistance(Planet p){
        double distance;
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;

        distance = Math.sqrt(dx * dx + dy * dy);

        return distance;
    }

    /**
     * 计算两星球间的万有引力
     * @param p 星球p
     * @return 两星球间的万有引力
     */
    public double calcForceExertedBy(Planet p){
        double Force;
        double distance = calcDistance(p);

        Force = G * mass * p.mass / (distance * distance);

        return Force;
    }

    /**
     * 万有引力x轴的分力
     * @param p 星球p
     * @return x轴分力(有方向)
     */
    public double calcForceExertedByX(Planet p){
        double F_x;
        double dx = p.xxPos - xxPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);

        F_x = F * dx / r;

        return F_x;
    }

    /**
     * 计算两星球间万有引力y轴分力
     * @param p 星球p
     * @return y轴分力
     */
    public double calcForceExertedByY(Planet p){
        double F_y;
        double dy = p.yyPos - yyPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);

        F_y = F * dy / r;

        return F_y;
    }

    /**
     * 计算万有引力合力的x轴分力
     * @param all_pla 该星球附近所有星球
     * @return x轴分力
     */
    public double calcNetForceExertedByX(Planet[] all_pla){
        double F_xall = 0;

        for(int index = 0; index < all_pla.length; index += 1){
            if(this.equals(all_pla[index])){
                continue;
            }else {
                F_xall += calcForceExertedByX(all_pla[index]);
            }
        }

        return F_xall;
    }

    /**
     * 计算万有引力合力的y轴分力
     * @param all_pla 该星球附近所有星球（不计算包括自己）
     * @return y轴分力
     */
    public double calcNetForceExertedByY(Planet[] all_pla){
        double F_yall = 0;

        for(int index = 0; index < all_pla.length; index += 1){
            if(this.equals(all_pla[index])){
                continue;
            }else{
                F_yall += calcForceExertedByY(all_pla[index]);
            }
        }

        return F_yall;
    }

    /**
     * 更新该星球在万有引力的作用下的位置和速度
     * @param dt 作用时间
     * @param fX x轴合力
     * @param fY y轴合力
     */
    public void update(double dt, double fX, double fY){
        double ax;
        double ay;

        ax = fX / mass;
        ay = fY / mass;

        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}