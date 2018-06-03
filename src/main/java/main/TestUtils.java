package main;

import java.math.BigDecimal;

/**
 * Created by Cheungqzy on 2017/6/23.
 */
public class TestUtils {

    public static void main(String[] args) {

        System.out.println(new BigDecimal(1.1).divide(new BigDecimal(3),2, BigDecimal.ROUND_DOWN));

    }

}
