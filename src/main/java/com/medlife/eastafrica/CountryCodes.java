package com.medlife.eastafrica;

import java.util.Map;
import java.util.Optional;

// Stores the display name and ISO country code needed to create a flag emoji.
record Country(String name, String isoCode) {
    // Unicode represents a flag as two regional-indicator characters,
    // one calculated from each letter of the country's ISO code.
    String flag() {
        int firstLetter = Character.codePointAt(isoCode, 0) - 'A' + 0x1F1E6;
        int secondLetter = Character.codePointAt(isoCode, 1) - 'A' + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }
}

// Maps international calling codes to their countries.
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

    // Accept codes with or without common formatting, such as "+254" or "(254)".
    // An empty Optional means the code is not in this application's country list.
    static Optional<Country> find(String input) {
        String normalized = input == null ? "" : input.replaceAll("[\\s+()-]", "");
        return Optional.ofNullable(COUNTRIES.get(normalized));
    }
}
