package sample.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Create By Xie ZuoZhi On 2018/5/10
 */
@Data
@ConfigurationProperties(prefix = "customer")
public class Customer {
    private String name;
    private int age;
}
