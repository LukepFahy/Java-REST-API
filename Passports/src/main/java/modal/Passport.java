package modal;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "passport")
@XmlType(propOrder = {"id", "name", "country", "age"})
public class Passport {

    private String id;
    private String name;
    private String country;
    private String age;

    public Passport() {
    }

    public Passport(String id, String name, String country, String age) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.age = age;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Passport {" + "id=" + id + ", name=" + name + ", country=" + country + ", age=" + age
+ '}';
    }
}
