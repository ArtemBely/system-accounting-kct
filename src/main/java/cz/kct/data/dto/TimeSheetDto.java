package cz.kct.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import cz.kct.data.entity.DzcEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

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
    private String projectName;
    @NotEmpty
    private String description;
    @NotEmpty
    private String kind;
    private DzcEntity dzc_id;
}
