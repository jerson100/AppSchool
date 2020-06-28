package utilidades;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class Cifrado {
	
	public static String cifrar(String txt) {
		return DigestUtils.md5Hex(txt);
	}
	
}
