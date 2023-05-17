package cz.kct.data.entity;

import lombok.*;
import javax.persistence.*;

@Entity(name = "OrderEntity")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Setter
@Getter

public class OrderEntity {
    @Id
    @NonNull
    private String epicId;
    @NonNull
    private String orderNumber;
}