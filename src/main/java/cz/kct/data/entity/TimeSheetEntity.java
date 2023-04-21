package cz.kct.data.entity;

import cz.kct.data.enums.KindEnum;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name="TimeSheetEntity")
@Builder(toBuilder = true)
@Table(name="system_accounting_kct")
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
@Setter
@Getter

public class TimeSheetEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private UUID id;
    @Column(name="DATE", columnDefinition = "DATE", nullable = false)
    private LocalDate date;
    @Column(name="TYPE_OF_ITEM", nullable = false)
    private int typeOfItem;
    @Column(name="HOURS", nullable = false)
    private double hours;
    @Column(name="PROJECT_NAME", nullable = false)
    private String projectName; //Enum
    @Column(name="DESCRIPTION", nullable = false)
    private String description;
    @Column(name="KIND")
    private String kind;

}
