package Demo.ChapterTwelve;

/**
 * Created by asus on 2017/12/24.
 */
public class WhoCalled {
    static void f(){
        try{
            throw new Exception();
        }catch (Exception e){
            for(StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }

    static void g(){f();}
    static void h(){g();}
    public static void main(String[] args){
        f();
        System.out.println("************");
        g();
        System.out.println("************");
        h();

        //the method name from inner to outer

//        f
//                main
//        invoke0
//                invoke
//        invoke
//                invoke
//        main
//                ************
//        f
//                g
//        main
//                invoke0
//        invoke
//                invoke
//        invoke
//                main
//        ************
//        f
//                g
//        h
//                main
//        invoke0
//                invoke
//        invoke
//                invoke
//        main
    }
}
