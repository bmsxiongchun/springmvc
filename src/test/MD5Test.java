import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Test {

    public static void main(String[] args) {

        //模拟用户输入的密码
        String source = "111111";

        //加入我们的salt
        String salt = "qwerty";

        int hashIterations = 1;


        /*
         * 构造方法中：
         * @source:明文，原始密码
         * @salt：通过使用随机数
         * @hashIterations：散列的次数，比如散列两次，相当于md5(md5(***))
         */
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

        String password_md5 = md5Hash.toString();

        System.out.println(password_md5);
    }
}
