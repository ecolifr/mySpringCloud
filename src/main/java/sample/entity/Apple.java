package sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Serializable {
    private String color;
    private Double weight;

    public Apple(Double weight) {
        this.weight = weight;
    }

    public Apple(String color) {

        this.color = color;
    }
}
