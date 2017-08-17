package cheungqzy.reflect;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class Student extends Person implements InterfaceA,InterfaceB{

    private int id;
    private String name;
    public Integer age;

    public Student(int id, String name, Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public void testA() {
        System.out.println("testA");
    }

    @Override
    public void testB() {
        System.out.println("testB");
    }

    private void say(){
        System.out.println("student say");
    }

    public void saySomethirng(String something){
        System.out.println(something);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
