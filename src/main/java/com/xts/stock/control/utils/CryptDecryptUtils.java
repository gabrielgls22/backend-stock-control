package com.xts.stock.control.utils;

import com.xts.stock.control.entrypoint.interceptor.exceptions.CryptDecryptException;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public final class CryptDecryptUtils {

    private static final String ALGORITHM = "RSA";
    private static final String RSA_ALGORITHM = "RSA";

    private CryptDecryptUtils() {
    }

    public static String encrypt(final String data, final String publicKeyBase64) {
        try {
            final byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            final KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            final PublicKey publicKey = keyFactory.generatePublic(keySpec);

            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            final byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (InvalidKeyException | InvalidKeySpecException | BadPaddingException
                 | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException e) {
            throw new CryptDecryptException("Error encrypting data", e);
        }
    }

    public static String decrypt(final String encryptedDataBase64, final String privateKeyBase64) {
        try {
            final byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            final KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            final PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            final byte[] encryptedData = Base64.getDecoder().decode(encryptedDataBase64);
            final byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (InvalidKeyException | InvalidKeySpecException | BadPaddingException
                 | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException e) {
            throw new CryptDecryptException("Error decrypting data", e);
        }
    }
}
