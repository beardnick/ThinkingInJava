package Demo.ChapterTwenty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asus on 2017/12/27.
 */
@Target(ElementType.METHOD)//refer to the place to use this annotation
@Retention(RetentionPolicy.RUNTIME)//在什么级别保存注解信息

public @interface UseCase {
    public int id();
    public String description() default "no description";
}
