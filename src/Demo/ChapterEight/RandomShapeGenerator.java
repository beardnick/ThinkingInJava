package Demo.ChapterEight;

import java.util.Random;

/**
 * Created by asus on 2017/12/24.
 */
public class RandomShapeGenerator {
    private Random rand = new Random(47);
    public Shape next(){
        switch (rand.nextInt(3)){
            default:
                case 0:return new Circle();
                case 1:return new Triangle();
                case 2:return new Square();
        }
    }

    public  static void main(String[] args){
        RandomShapeGenerator rand = new RandomShapeGenerator();
        Shape[] s = new Shape[9];
        for(int i = 0 ;i < s.length ; i ++)
            s[i] = rand.next();
        for(Shape x : s)
            x.draw();

        //without @override
//        Square.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Circle.draw()

        // with @override
//        Square.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Triangle.draw()
//        Square.draw()
//        Circle.draw()


    }


}
