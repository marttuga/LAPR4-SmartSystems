package eapli.base.Utils;

import java.security.SecureRandom;

public class PasswordGeneratorAlgorithm {

    public static final Integer PASSWORD_SIZE = 8;

    /**
     * Hide implicit constructor.
     */
    private PasswordGeneratorAlgorithm() {

    }

    private static final SecureRandom random = new SecureRandom();

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final String NUMBERS = "0123456789";

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    public static String generatesPassword() {
        int index;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= PASSWORD_SIZE-3; i++) {
            index = random.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        index = random.nextInt(UPPER.length());
        result.append(UPPER.charAt(index));

        index = random.nextInt(LOWER.length());
        result.append(LOWER.charAt(index));

        index = random.nextInt(NUMBERS.length());
        result.append(NUMBERS.charAt(index));

        return result.toString();
    }
}
