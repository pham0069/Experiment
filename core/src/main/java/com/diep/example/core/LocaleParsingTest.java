package com.diep.example.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class LocaleParsingTest {
    private static final DecimalFormatSymbols DEFAULT_SYMBOLS
            = DecimalFormatSymbols.getInstance(Locale.GERMAN);

    private static final char DEFAULT_DECIMAL_SEPARATOR = '.';
    private static final char DEFAULT_THOUSAND_SEPARATOR = ',';

    public static void main(String[] args) {
        BigDecimal a = parseNumber("1234,56");
        System.out.println(a.toString());
        Locale.getDefault(Locale.Category.FORMAT);
    }

    private static BigDecimal parseNumber(String stringValue) {
        try {
            stringValue = convertToDefaultFormatValue(stringValue, ",", ".");
            DecimalFormatFromText decimalFormatFromText = new DecimalFormatFromText(stringValue);
            return new BigDecimal(decimalFormatFromText.parse(stringValue).toString());
        } catch (Exception e) {
            return null;
        }
    }

    private static String convertToDefaultFormatValue(String value, String decimalSeparator, String thousandSeparator) {
        StringBuilder builder = new StringBuilder();

        for (char c: value.toCharArray()) {
            if (c == thousandSeparator.charAt(0)) {
                builder.append(DEFAULT_THOUSAND_SEPARATOR);
            } else if (c == decimalSeparator.charAt(0)) {
                builder.append(DEFAULT_DECIMAL_SEPARATOR);
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    static class DecimalFormatFromText extends DecimalFormat {
        public DecimalFormatFromText(String numericText) {
            super(numericText.replaceAll("[-0-9]", "0"), DEFAULT_SYMBOLS);
        }
    }
}

