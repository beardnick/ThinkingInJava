package Demo.ChapterThree;

import static Util.Print.*;

/**
 * Created by asus on 2017/12/2.
 */
public class Equivalence {
    public static void main(String[] args){
        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        print(n1 == n2); //比较的是两个对象的引用
        print(n1 != n2);
        print(n1.equals(n2));//比较的是两个的内容
    }
}
