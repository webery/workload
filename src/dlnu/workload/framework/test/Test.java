package dlnu.workload.framework.test;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Test {

	public static void main(String[] args) {
		
		UnauthorizedException u;

		String algorithmName = "md5";
		String account = "2011082222";
		String password = "000000";
		String randomNum = new SecureRandomNumberGenerator().nextBytes()
				.toHex();
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, account + randomNum, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println("salt " + account + randomNum);
		System.out.println(encodedPassword);
	}

}
