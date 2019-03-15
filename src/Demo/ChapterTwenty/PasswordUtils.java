package Demo.ChapterTwenty;


import java.util.List;

/**
 * Created by asus on 2017/12/27.
 */
public class PasswordUtils {
    @UseCase(id = 47 , description = "hello world" )
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49 , description = "New passwords can't equal")
    public boolean checkForNewPassword(List<String>prevPasswords , String password){
        return ! prevPasswords.contains(password);
    }

}
