package hello.Class;


public class Product {
    private long id;
    private String name;
    private long type;
    private String score;
    private String comment;
    private String imagepath;
    private String userlogin;
    public Product(){}

    public Product(String name, String score, String comment, String imagepath, long type, String login) {
        this.name = name;
        this.type = type;
        this.score = score;
        this.comment = comment;
        this.imagepath = imagepath;
        this.userlogin = login;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public long getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public long getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }

    public String getImagepath() {
        return imagepath;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }
}
