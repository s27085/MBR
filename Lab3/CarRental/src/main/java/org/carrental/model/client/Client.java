package org.carrental.model.client;
import lombok.*;

@Data
@AllArgsConstructor
public class Client {
        private Integer id;
        private String name;
        private Gender gender;
}