package cheungqzy.util;

import java.util.Date;

/**
 * Created by Cheungqzy on 2017/6/7.
 */
public class TimeStamp {

    public static void main(String[] args) {
        Long lo = new Date().getTime();
        System.out.println(lo);
        System.out.println(new Date(lo).getTime());

        System.out.println(System.currentTimeMillis());
    }
}
