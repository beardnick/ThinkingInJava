package Demo.ChapterEight;

/**
 * Created by asus on 2017/12/24.
 */
public class PolyConstructors {
    public static void main(String[] arg){
        new RoundGlyph(5);

//        Glyph() before draw()
//        RoundGlyph.draw() , radius =0
//        Glyph() after draw()
//        RoundGlyph.RoundGlyph() , radius = 5
    }
}

class Glyph{
    void draw(){
        System.out.println("Glyph.draw()");
    }

    Glyph(){
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph{
    private int radius = 1;
    RoundGlyph(int r){
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph() , radius = " + radius);
    }
    void draw(){
        System.out.println("RoundGlyph.draw() , radius =" + radius);
    }
}