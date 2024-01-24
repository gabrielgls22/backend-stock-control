package com.xts.stock.control.utils;

import lombok.extern.slf4j.Slf4j;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Slf4j
@SuppressWarnings("PMD")
public class PairKeyGeneratorUtils {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        final String publicKeyString = publicKeyToString(publicKey);
        final String privateKeyString = privateKeyToString(privateKey);

        System.out.println("Public key: " + publicKeyString);
        System.out.println("Private key: " + privateKeyString);

        final String encryptPassword = CryptDecryptUtils.encrypt("stelGlisia01", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuWpwTEJ4fzk0CXKBrBhefS8jcFAKin5dyVnK56paky2nILpsdHMQn2PwOjXYxyzyZfS16JQGvlyrdJ7D8yYohmhMoi/t+a3DjIYEfqlYb5F0nJqTLLKzaHHWTv2reQkQerb6XYvHAdUeBSvXcgYkwESiyQIVe8ZOvmqSp72nGsrCyMxX4Ogd6N8Ka0caFrQK9a+KbTAXcL0XxToV2OEbEbWKtfYzY1okKBAuAIkrNOlCP75exOskO9JLOMMWr4yWno29dwmuNPTt4p+KAbIr6VE0jf8B6PbXo0fSBiCqF8x+A0RbglXjulAEsjDxwUN3Ng7jS9OyEI/GeAOIiVEP9QIDAQAB");
        System.out.println("password: " + encryptPassword);

    }

    public static String publicKeyToString(final PublicKey publicKey) {
        final byte[] publicKeyBytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    public static String privateKeyToString(final PrivateKey privateKey) {
        final byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

}
