package com.jedosa.junglim.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBlinderTest {

    @Test
    public void blindAddressTailAsStar() throws Exception {
        // given
        // when
        String convertedAddress = AddressBlidner.blindAsStar("서울특별시 마포구 성암로 301");

        // then
        assertEquals("서울특별시 마포구 성암로 ***", convertedAddress);

    }
}
