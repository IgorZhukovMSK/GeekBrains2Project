package Level2.Lesson3.Point2;

public class Person extends Main {
    private String name;
    private int numPhone;
    private String email;

    public Person(String name, int numPhone, String email) {
        this.name = name;
        this.numPhone = numPhone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(int numPhone) {
        this.numPhone = numPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


