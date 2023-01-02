package com.binar.finalproject.BEFlightTicket.utility;

import java.util.Random;

public class PNRGenerator {
    public static final Random random = new Random();
    private PNRGenerator(){
    }

    public static String generatePNR()
    {

        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = alphaNumericString.length() * random.nextInt();

            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
