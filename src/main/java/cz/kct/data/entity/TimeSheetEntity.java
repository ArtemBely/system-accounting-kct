package cz.kct.data.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name="TimeSheetEntity")
@Builder(toBuilder = true)
@Table(name="system_accounting_kct")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class TimeSheetEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private UUID id;
    @NonNull
    @Column(name="DATE", columnDefinition = "DATE", nullable = false)
    private LocalDate date;
    @NonNull
    @Column(name="TYPE_OF_ITEM", nullable = false)
    private int typeOfItem;
    @NonNull
    @Column(name="HOURS", nullable = false)
    private double hours;
    @NonNull
    @Column(name="PROJECT_NAME", nullable = false)
    private String projectName; //Enum
    @NonNull
    @Column(name="DESCRIPTION", nullable = false)
    private String description;
    @NonNull
    @Column(name="KIND")
    private String kind;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DZC_ID", updatable = false)
    @Fetch(FetchMode.JOIN)
    private DzcEntity dzc_id;
}
