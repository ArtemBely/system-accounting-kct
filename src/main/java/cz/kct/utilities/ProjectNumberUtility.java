package cz.kct.utilities;

import cz.kct.constants.ProjectNumberUtilityConstants;
import cz.kct.data.enums.KindEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ProjectNumberUtility {

    /**
     * Method for defining of project's number
     *
     * @param inputAccountName  account name field from Excel sheet
     * @param inputIssueSummary issue summary field from Excel sheet
     * @return project number(cislo zakazky)
     */
    public String defineProjectNumber(String inputAccountName, String inputIssueSummary) {
        String number = DefinitionUtility.defineKind(inputAccountName, inputIssueSummary);
        return number.equals(KindEnum.SUPPORT.getValue()) ?
                ProjectNumberUtilityConstants.SUPPORT_IDENTIFICATION_NUMBER :
                number.equals(KindEnum.DEVELOPMENT.getValue()) ?
                        ProjectNumberUtilityConstants.DEV_IDENTIFICATION_NUMBER :
                        ProjectNumberUtilityConstants.VISPART_IDENTIFICATION_NUMBER;
    }
}
