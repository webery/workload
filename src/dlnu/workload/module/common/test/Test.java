package dlnu.workload.module.common.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Test {

	public static void main(String[] args) {
		
		String password = "000000";
        String salt = "teacher";
        String md5Str1 = new Md5Hash(password, salt).toString();
        System.out.println("MD5值1：" + md5Str1);

	}

}
