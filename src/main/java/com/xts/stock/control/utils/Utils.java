package com.xts.stock.control.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;

@Component
public class Utils {

    public static String generateUniqueNumber() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);
        BigInteger bigInteger = new BigInteger(1, randomBytes);
        String uniqueNumber = bigInteger.toString(10);
        return uniqueNumber.substring(0, 8);
    }

    public static String removeSignals(final String valueWithSignals) {

        return Optional.ofNullable(valueWithSignals)
                .map(value -> value.replaceAll("[./-]", ""))
                .orElse(null);
    }

    public static String cnpjRegex(final String cnpjWithoutRegex) {
        return Optional.ofNullable(cnpjWithoutRegex)
                .map(cnpjValue -> cnpjValue
                        .replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5"))
                .orElse(null);
    }
}
