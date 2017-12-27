package Demo.ChapterTwenty;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 2017/12/27.
 */
public class UseCaseTracker {
    public static void trackUseCases(List<Integer>useCases , Class<?>cl){
        for(Method m : cl.getDeclaredMethods()){
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.printf("Found Use Case:%d %s\n" , uc.id() , uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for(int i : useCases){
            System.out.printf("Warning : Missing use case-%d" , i);
        }
    }

    public static void main(String[] args){
        List<Integer>useCases = new ArrayList<Integer>();
        Collections.addAll(useCases , 47 , 48 , 49 , 50);
        trackUseCases(useCases , PasswordUtils.class);

//        Found Use Case:48 no description
//        Found Use Case:47 hello world
//        Found Use Case:49 New passwords can't equal
//        Warning : Missing use case-50
    }
}
