package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("Lombok");
        helloLombok.setAge(1);

        String name1 = helloLombok.getName();
        int age1 = helloLombok.getAge();
        System.out.println("name1 = " + name1 + "  age1 = " +age1);

    }


}
