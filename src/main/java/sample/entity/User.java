package sample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

/**
 * Create By Xie ZuoZhi On 2018/5/9
 */
@Data
@Document(collection = "User")
public class User {
    @Id
    private String id;
    @Field("first_name")
    private String firstName;
    @NotNull
    @Field("last_name")
    private String lastName;
    private char sex;
    private int age;

    public User( String id, String firstName, String lastName ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }
}
