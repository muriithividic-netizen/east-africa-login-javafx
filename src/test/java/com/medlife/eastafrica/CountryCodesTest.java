package com.medlife.eastafrica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CountryCodesTest {
    @Test
    void findsCountryFromCodeWithOrWithoutPlus() {
        assertEquals("Kenya", CountryCodes.find("+254").orElseThrow().name());
        assertEquals("Uganda", CountryCodes.find("256").orElseThrow().name());
    }

    @Test
    void findsEastAfricanCountriesOutsideTheEastAfricanCommunity() {
        assertEquals("Ethiopia", CountryCodes.find("+251").orElseThrow().name());
        assertEquals("Somalia", CountryCodes.find("+252").orElseThrow().name());
    }

    @Test
    void rejectsUnknownCountryCodes() {
        assertTrue(CountryCodes.find("+1").isEmpty());
    }
}
