package com.medlife.eastafrica;

import java.util.Map;
import java.util.Optional;

record Country(String name, String isoCode) {
    String flag() {
        int firstLetter = Character.codePointAt(isoCode, 0) - 'A' + 0x1F1E6;
        int secondLetter = Character.codePointAt(isoCode, 1) - 'A' + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }
}

final class CountryCodes {
    private static final Map<String, Country> COUNTRIES = Map.ofEntries(
            Map.entry("211", new Country("South Sudan", "SS")),
            Map.entry("243", new Country("Democratic Republic of the Congo", "CD")),
            Map.entry("250", new Country("Rwanda", "RW")),
            Map.entry("251", new Country("Ethiopia", "ET")),
            Map.entry("252", new Country("Somalia", "SO")),
            Map.entry("253", new Country("Djibouti", "DJ")),
            Map.entry("254", new Country("Kenya", "KE")),
            Map.entry("255", new Country("Tanzania", "TZ")),
            Map.entry("256", new Country("Uganda", "UG")),
            Map.entry("257", new Country("Burundi", "BI")),
            Map.entry("291", new Country("Eritrea", "ER"))
    );

    private CountryCodes() {
    }

    static Optional<Country> find(String input) {
        String normalized = input == null ? "" : input.replaceAll("[\\s+()-]", "");
        return Optional.ofNullable(COUNTRIES.get(normalized));
    }
}
