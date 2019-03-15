package Demo.ChapterThree;
import static Util.Print.*;

/**
 * Created by asus on 2017/12/2.
 */

class Tank{
    int level;
}
public class Assigment {

    public static void main(String[] args){
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.level = 9;
        t2.level = 47;
        print("1 : t1.level " + t1.level +
        " , t2.level " + t2.level);
        t1 = t2; //现在t1 t2是同一个对象的引用，所以t1 t2 的值以后都将是一样的
        print("2 : t1.level " + t1.level +
                " , t2.level " + t2.level);
        t1.level = 27;
        print("3 : t1.level " + t1.level +
                " , t2.level " + t2.level);
    }
}
