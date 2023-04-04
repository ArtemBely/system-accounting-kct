package cz.kct.data.entity;

import lombok.*;
import javax.persistence.*;
@Entity(name="ExcelEntity")
@Table(name="excel")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@Setter
@Getter
public class ExcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private int id;
    @Column(name = "VALUE", nullable = false)
    private String value;

    public ExcelEntity(String value) {
        this.value = value;
    }
}
