package getalongwithmecommunity;

/**
 *
 * @author Mona
 */
public class Volunteer {
    private String name;
    private int age;
    private String phone;
    private String email;
    private String city;

    public Volunteer(String name, int age, String phone, String email, String city) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.city = city;
}
@Override
    public String toString() {
        return "Volunteer," + "name=" + name + ",age=" + age + ",phone=" + phone + ",email=" + email + ",city=" + city;
    }
}
