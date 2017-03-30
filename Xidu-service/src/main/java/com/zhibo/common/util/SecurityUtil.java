package com.zhibo.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.axiom.om.util.Base64;

public class SecurityUtil {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String originalPwd = "1"; 
		MessageDigest messageDigest=MessageDigest.getInstance("SHA-512");
		messageDigest.update(originalPwd.getBytes());
		String digestedPwdString = new String(Base64.encode(messageDigest.digest()));
		System.out.println("pwd:" + digestedPwdString);
	}
}
