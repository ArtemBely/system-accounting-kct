package cz.kct.utilities;

import cz.kct.constants.DefinitionUtilityConstants;
import cz.kct.data.enums.KindEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DefinitionUtility {

    /**
     * Method for defining of type of developer's work
     *
     * @param inputAccountName  account name field from Excel sheet
     * @param inputIssueSummary issue summary field from Excel sheet
     * @return type of developer's work
     * @see cz.kct.data.enums.KindEnum
     */
    public String defineKind(String inputAccountName, String inputIssueSummary) {
        if (inputAccountName.equals(DefinitionUtilityConstants.DEFINE_SUPPORT)) {
            return KindEnum.SUPPORT.getValue();
        } else if (inputAccountName.equals(DefinitionUtilityConstants.DEFINE_DEV) || findDefinition(inputIssueSummary)) {
            return KindEnum.DEVELOPMENT.getValue();
        } else {
            return KindEnum.VISPART.getValue();
        }
    }

    /**
     * Method return true if input string contains VALUATION or PRIORITIZATION
     *
     * @param inputIssueSummary issue summary field from Excel sheet
     * @return true if object contains record VALUATION or PRIORITIZATION
     */
    public boolean findDefinition(String inputIssueSummary) {
        return (inputIssueSummary.toLowerCase().contains(DefinitionUtilityConstants.VALUATION.toLowerCase()) ||
                inputIssueSummary.toLowerCase().contains(DefinitionUtilityConstants.PRIORITIZATION.toLowerCase()));
    }
}
