package cheungqzy.reflect;

/**
 * Created by Cheungqzy on 2017/6/6.
 */
public class StudentB implements InterfaceA , logHelper{

    @Override
    public void testA() {
        System.out.println("StudentB testA");
    }

    @Override
    public void log() {
        System.out.println("StudentB log");
    }
}
