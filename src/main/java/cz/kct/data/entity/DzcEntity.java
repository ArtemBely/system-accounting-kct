package cz.kct.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity(name = "DzcEntity")
@Builder(toBuilder = true)
@Table(name = "dzc")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Component

public class DzcEntity {
    @Id
    @NonNull
    @Column(name = "DZC", nullable = false)
    private String dzc;
    @OneToMany(targetEntity = TimeSheetEntity.class, mappedBy = "dzc_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TimeSheetEntity> timeSheetEntities;
}
