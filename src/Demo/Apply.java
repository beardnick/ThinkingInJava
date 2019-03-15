package Demo;

import java.util.Arrays;

/**
 * Created by asus on 2017/12/1.
 */

class Processor{
    public String name(){
        return getClass().getSimpleName();
    }

    Object process(Object input){
        return  input;
    }
}

class Upcase extends Processor{
    String process(Object input){
        return ((String)input).toUpperCase();
    }
}

class Downcase extends Processor{
    String process(Object input){
        return ((String)input).toLowerCase();
    }
}

class  Splitter extends Processor{
    String process(Object input){
        return Arrays.toString(((String)input).split(" "));
    }
}
public class Apply {
    public static void process(Processor p , Object s){
    }

}
