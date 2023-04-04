package cz.kct.data.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="TimeSheetEntity")
@Builder(toBuilder = true)
@Table(name="person")
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
@Setter
@Getter

public class TimeSheetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private int id;
    @Column(name="DATE", nullable = false)
    private LocalDate date;
    @Column(name="TYPE_OF_ITEM", nullable = false)
    private String typeOfItem;
    @Column(name="CONTRACT", nullable = false)
    private String contract; //Enum
    @Column(name="HOURS", nullable = false)
    private double hours;
    @Column(name="DESCRIPTION", nullable = false)
    private String description;
}
