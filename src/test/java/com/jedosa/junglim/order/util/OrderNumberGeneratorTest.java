package com.jedosa.junglim.order.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderNumberGeneratorTest {
    @Test
    public void generateOrderNumberTest() throws Exception {
        // given
        int length = 8;
        boolean useLetter = false;
        boolean useNumber = true;

        // when
        String randomNumber = RandomStringUtils.random(length, useLetter, useNumber);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // then
        System.out.println(today+randomNumber);
    }
}
