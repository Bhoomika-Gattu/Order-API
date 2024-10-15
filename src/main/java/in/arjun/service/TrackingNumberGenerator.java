package in.arjun.service;

import java.security.SecureRandom;

public class TrackingNumberGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateTrackingNumber() {
        StringBuilder trackingNumber = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                trackingNumber.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            if (i < 2) {
                trackingNumber.append("-");
            }
        }

        return trackingNumber.toString();
    }
}
