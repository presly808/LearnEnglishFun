package ua.artcode.englishfun.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by serhii on 09.07.17.
 */
public class HttpClientUtilsTest {
    @Test
    public void get() throws Exception {

        String result =
                HttpClientUtils.get(
                        "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=uk&dt=t&q=Inference");

        System.out.println(result);

        assertNotNull(result);


    }

}