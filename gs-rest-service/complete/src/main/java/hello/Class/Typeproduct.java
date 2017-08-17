package hello.Class;

public class Typeproduct {
    private String name;
    private int id;
    private int parentid;
    public Typeproduct() {};
    public Typeproduct(String name, int id, int parentid) {
        this.name = name;
        this.id = id;
        this.parentid = parentid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getParentid() {
        return parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
