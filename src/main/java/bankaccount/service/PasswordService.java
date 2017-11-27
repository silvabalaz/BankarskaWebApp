package bankaccount.service;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordService {

    public static String getPasswordHash(String password) {


        String hasshedPassword = DigestUtils.sha1Hex(password);

        return hasshedPassword;

    }

}