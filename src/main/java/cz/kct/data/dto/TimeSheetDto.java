package cz.kct.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.kct.data.enums.KindEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
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
    private LocalDate date;
    @PositiveOrZero
    @Max(10000)
    private int typeOfItem;
    @PositiveOrZero
    @Max(24)
    private double hours;
    @NotEmpty
    private String projectName; //Enum
    @NotEmpty
    private String description;
    @NonNull
    private String kind;
}
