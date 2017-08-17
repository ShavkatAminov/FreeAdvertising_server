package hello;


public class Reklamniy {
    private  int id;
    private int parentid;
    private  String name;

    public Reklamniy(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Reklamniy()
    {

    }
    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public void setId(int id){this.id = id;}
    public int getParentid(){return this.parentid;}
    public void setParentid(int parentid){this.parentid = parentid;}
    public void setName(String name){this.name = name;}
}
