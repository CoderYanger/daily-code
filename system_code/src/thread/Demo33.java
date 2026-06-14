package thread;
//class Point {
//    //通过横坐标纵坐标表示
//    public Point(double x,double y){
//
//    }
//    //通过极坐标表示(x=r×cos(a),y=r×sin(a))
//    public Point(double r,double theta){
//
//    }
//}
//平面上的一个点
//工厂设计模式
class Point{

}
//工厂类
class PointFactory{
    //工厂方法
    public static Point makePointByXY(double x,double y){
        Point p=new Point();
        //通过 x 和 y 给 p 进行属性设置
        return p;
    }
    public static Point makePointByRY(double r,double a){
        Point p=new Point();
        //通过 r 和 a 给 p 进行属性设置
        return p;
    }
}
public class Demo33 {
    public static void main(String[] args) {
        Point p=PointFactory.makePointByXY(10,20);
    }
}