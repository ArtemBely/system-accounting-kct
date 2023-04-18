package cz.kct.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.kct.data.enums.KindEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)

public class TimeSheetDto {
    private UUID id;
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate workDate;
    @NotEmpty
    private int typeOfItem;
    @NotEmpty
    private String projectName; //Enum
    @NotEmpty
    private double hour;
    @NotEmpty
    private String description;
    @NonNull
    private String kind;
}
