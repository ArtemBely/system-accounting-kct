package cz.kct.utilities;

import cz.kct.data.enums.KindEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DefinitionUtility {
    public static final String VALUATION = "Ocenění";
    public static final String PRIORITIZATION = "Prioritizace";
    public static final String DEFINE_SUPPORT = "FIBAMS EXT Support account";
    public static final String DEFINE_DEV = "FIBAMS EXT Development account";

    public String defineKind(String inputAccountName, String inputIssueSummary) {
        String output;
        if(inputAccountName.equals(DEFINE_SUPPORT)) output = KindEnum.SUPPORT.getValue();
        else if (inputAccountName.equals(DEFINE_DEV) || findDefinition(inputIssueSummary)) {
            output = KindEnum.DEVELOPMENT.getValue();
        } else  output = KindEnum.VISPART.getValue();
        return output;
    }
    public boolean findDefinition(String inputIssueSummary) {
        return(inputIssueSummary.toLowerCase().contains(VALUATION.toLowerCase()) ||
                inputIssueSummary.toLowerCase().contains(PRIORITIZATION.toLowerCase()));
    }
}
