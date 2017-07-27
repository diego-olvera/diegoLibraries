package diegoLibraries.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BsCrypterEncoder {
	private static BCryptPasswordEncoder bcryptEncoder=new BCryptPasswordEncoder();

	public static String encode(String string) {
		return bcryptEncoder.encode(string);
	}
	public static boolean matches(String str,String encode) {
		return bcryptEncoder.matches(str, encode);
	}
	public static void testEncoders() {
		String original="secret";
		String encoded=encode(original);
		System.out.println(encoded);
		//encoded=encode("s");
		System.out.println(matches(original,encoded));
	}
	public static void main(String[] args) {
		testEncoders();
	}
}
