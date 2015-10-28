/**
 * Created by zach on 10/20/15.
 */
public class Person implements Comparable {
    int id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ip;

    public Person(int id, String firstName, String lastName, String email, String country, String ip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return lastName.compareTo(p.lastName);
    }
}
