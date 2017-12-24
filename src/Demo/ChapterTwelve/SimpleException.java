package Demo.ChapterTwelve;

/**
 * Created by asus on 2017/12/24.
 */
public class SimpleException extends Exception{
}
class InheritingExceptions{
    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args){
        InheritingExceptions sed = new InheritingExceptions();
        try{
            sed.f();
        } catch (SimpleException e) {
           System.out.println("Caught it !");
        }
//        Throw SimpleException from f()
//        Caught it !
    }
}
