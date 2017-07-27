package diegoLibraries.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5Encoder {
	private static Md5PasswordEncoder md5Encoder=new Md5PasswordEncoder();
	
	public static String encode(String string) {
		return md5Encoder.encodePassword(string, null);
	}
	public static boolean matches(String str,String encode) {
		return md5Encoder.encodePassword(str,null).equals(encode);
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
