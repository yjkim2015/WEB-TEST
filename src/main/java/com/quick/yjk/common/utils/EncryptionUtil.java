package com.quick.yjk.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public final class EncryptionUtil {
	/**
	 * "BARUNSOFTWAREENGINEERING" to SHA1
	 */
	private static final String AES_KEY = "a5f526c64ef62b7f0141e893748d238902d8464f";

	/**
	 * 생성자
	 */
	private EncryptionUtil() {
		super();
	}

	/**
	 * SHA512 암호화
	 * @param target
	 * @return
	 */
	public static String encryptSHA512(final String target) throws NoSuchAlgorithmException {
		final MessageDigest objSHA = MessageDigest.getInstance("SHA-512");
		final byte[] bytSHA = objSHA.digest(target.getBytes());
		final BigInteger intNumber = new BigInteger(1, bytSHA);
		
		final StringBuffer strHashCode = new StringBuffer();
		strHashCode.append(intNumber.toString(16));

		while (strHashCode.length() < 128) {
//			strHashCode.append('0');
			strHashCode.insert(0, '0');
		}

		return strHashCode.toString();
	}

	/**
	 * AES Key Spec
	 * @return
	 */
	private static SecretKeySpec getAESKeySpec() {
		final byte[] keyBytes = new byte[16];
		byte[] bytes = null;
		try {
			bytes = AES_KEY.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		int len = bytes.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(bytes, 0, keyBytes, 0, len);
		return new SecretKeySpec(keyBytes, "AES");
	}

	/**
	 * AES256 으로 암호화 한다.
	 * 
	 * @param str 암호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptAES256(final String str) throws Exception {
		String enStr = null;
		if (!StringUtils.isEmpty(str)) {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, getAESKeySpec(), new IvParameterSpec(AES_KEY.substring(0, 16).getBytes()));
			final byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
			enStr = new String(Base64.encodeBase64(encrypted));
		}
		return enStr;
	}

	/**
	 * AES256으로 암호화된 txt 를 복호화한다.
	 * 
	 * @param str 복호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptAES256(final String str) throws Exception {
		String deStr = null;
		if (!StringUtils.isEmpty(str)) {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, getAESKeySpec(), new IvParameterSpec(AES_KEY.substring(0, 16).getBytes()));
			final byte[] byteStr = Base64.decodeBase64(str.getBytes());
			deStr = new String(cipher.doFinal(byteStr), "UTF-8");
		}
		return deStr;
		
	}
	
}
