package projecta07.ultil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Hoang NV
public class EncrypPasswordUtils {
    public static String EncrypPasswordUtils(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static void main(String[] args) {
        System.out.println("pass 123456 ma hoa thanh: " + EncrypPasswordUtils("123456"));
    }
}
