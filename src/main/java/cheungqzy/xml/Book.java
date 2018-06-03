package cheungqzy.xml;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class Book {

    private String id;
    private String name;

    public Book(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
