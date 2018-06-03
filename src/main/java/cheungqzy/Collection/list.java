package cheungqzy.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheungqzy on 2017/6/12.
 */
public class list {
    static List<String> list = new ArrayList<>();

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) list.add(String.valueOf(i));
        list.add(0,"123");
        System.out.println(list);
    }
}
