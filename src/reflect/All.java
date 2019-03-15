package reflect;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class All {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        user.setName("qianz");
        user.setId(22);
        user.setSex("male");
        User user1 = new User();
        Class<User> userClass = null;
        try {
            userClass = (Class<User>) Class.forName("reflect.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for ( Method m: userClass.getMethods()
             ) {
                System.out.println(m.getName());
        }
        for (Field f:userClass.getDeclaredFields()
             ) {
//            System.out.println(f.getName());
            String name = All.upper(f.getName());
            try {
                Method setter = userClass.getMethod("set" + name, f.getType());
                Method getter = userClass.getMethod("get" + name);
//                System.out.println(getter.invoke(user, null));
                setter.invoke(user1,getter.invoke(user,null));
                System.out.println(getter.invoke(user1,null));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private static String upper(String s){
        char[] chars = s.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    private static <T> Method getGetter(Class<T> cl, String fieldName, Class<?> type) throws NoSuchMethodException {
        char[] chars = fieldName.toCharArray();
        chars[0] -= 32;
        fieldName = String.valueOf(chars);
        if(type == Boolean.class){
            return cl.getMethod("is" + fieldName);
        }else {
            return cl.getMethod("get" + fieldName);
        }
    }

    private static <T> Method getSetter(Class<T> cl, String fieldName, Class<?> type) throws NoSuchMethodException {
        char[] chars = fieldName.toCharArray();
        chars[0] -= 32;
        fieldName = String.valueOf(chars);
        if(type == Boolean.class){
            return cl.getMethod("is" + fieldName);
        }else {
            return cl.getMethod("get" + fieldName);
        }
    }
}
