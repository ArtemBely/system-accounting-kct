package cz.kct.utilities;

import cz.kct.data.enums.KindEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ProjectNumberUtility {
    public static final String SUPPORT_IDENTIFICATION_NUMBER = "5601001520";
    public static final String DEV_IDENTIFICATION_NUMBER = "5601001790";
    public static final String VISPART_IDENTIFICATION_NUMBER = "";
    public String defineProjectNumber(String inputAccountName, String inputIssueSummary) {
        String number = DefinitionUtility.defineKind(inputAccountName, inputIssueSummary);
        return  number.equals(KindEnum.SUPPORT.getValue()) ?
                SUPPORT_IDENTIFICATION_NUMBER : number.equals(KindEnum.DEVELOPMENT.getValue()) ?
                DEV_IDENTIFICATION_NUMBER : VISPART_IDENTIFICATION_NUMBER;
    }
}
