package cz.kct.data.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class JiraObjectDto {
    private int id;
    private String issueKey;
    private String issueSummary;
    private int hours;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate workDate;
    private String userName;
    private String fullName;
    private int period;
    private String accountKey;
    private String accountName;
    private String accountLead;
    private String accountCategory;
    private String accountCustomer;
    private String activityName;
    private String component;
    private String allComponents;
    private String versionName;
    private String issueType;
    private String issueStatus;
    private String projectKey;
    private String projectName;
    private String epic;
    private String epicLink;
    private String workDescription;
    private String parentKey;
    private String reporter;
    private int externalHours;
    private int billedHours;
    private int issueOriginalEstimate;
    private int issueRemainingEstimate;
    private String locationName;
    private String account;
}
