package com.pstech.stocks.buybackipostockmarket;

import com.pstech.stocks.buybackipostockmarket.utils.AppConstants;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getDate_isCorrect() throws Exception {

        String todayDate = AppConstants.getTodayDate();
        System.out.println(todayDate);

//        assertEquals(4, 2 + 2);


    }

}