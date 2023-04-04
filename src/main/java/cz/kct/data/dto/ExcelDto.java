package cz.kct.data.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class ExcelDto implements Serializable {
    private int id;
    private String value;
}
