package Demo.ChapterEight;

/**
 * Created by asus on 2017/12/24.
 */
public class PrivateOverride {
   private  void f(){
        System.out.println("private f()");
    }

    public static void main(String[] args){
        PrivateOverride p = new Derived();
        p.f();

//        private f()
        //only public functions can be overrided
    }
}

class Derived extends PrivateOverride{
    public void f(){
        System.out.println("public f()");
    }
}
