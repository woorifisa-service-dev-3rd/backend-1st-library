package domain.login;
import java.sql.Date;

public class User {

    private long id;
    private String name;
    private Date birth;
    private String address;

    public User(long id, String name, Date birth, String address) {
        super();
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }

    @Override
    public String toString(){
        return "Student [id=" + id + ", name=" + name + ", birth=" + birth + ", address=" + address;
    }
}