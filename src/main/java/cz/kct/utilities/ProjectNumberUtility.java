package cz.kct.utilities;

import cz.kct.constants.ProjectNumberUtilityConstants;
import cz.kct.data.entity.OrderEntity;
import cz.kct.data.enums.KindEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class ProjectNumberUtility {

    /**
     * Utility for definition of project's number
     *
     * @param inputAccountName  account name field from Excel sheet
     * @param inputIssueSummary issue summary field from Excel sheet
     * @return project number(cislo zakazky)
     */
    public String defineProjectNumber(String inputAccountName, String inputIssueSummary, String inputEpicValue, List<OrderEntity> orderEntityList) {
        String number = DefinitionUtility.defineKind(inputAccountName, inputIssueSummary);
        if (number.equals(KindEnum.SUPPORT.getValue())) {
            return ProjectNumberUtilityConstants.SUPPORT_IDENTIFICATION_NUMBER;
        } else if (number.equals(KindEnum.DEVELOPMENT.getValue())) {
            String orderId = findEpic(getEpicIdOnly(inputEpicValue), orderEntityList);
            return orderId.length() > 0 ? orderNumberFromSap(orderId) : ProjectNumberUtilityConstants.DEV_IDENTIFICATION_NUMBER;
        } else return ProjectNumberUtilityConstants.VISPART_IDENTIFICATION_NUMBER;
    }

    public String getEpicIdOnly(String inputEpicValue) {
        return inputEpicValue.replaceAll(" .*", "");
    }

    /**
     * Utility for definition suitable order id for each Skoda's order(JIRA ID)
     *
     * @param epicId          order id from Skoda
     * @param orderEntityList list of orders id's and suitable order numbers from SAP Excel file
     * @return suitable order id (cislo zakazki) if exists and empty string if not
     */
    public String findEpic(String epicId, List<OrderEntity> orderEntityList) {
        List<OrderEntity> epicList = orderEntityList.stream()
                .filter(item -> item.getEpicId().equals(epicId))
                .collect(Collectors.toList());
        return epicList.size() > 0 ? epicList.get(0).getOrderNumber() : "";
    }

    public String orderNumberFromSap(String orderId) {
        return ProjectNumberUtilityConstants.START_IDENTIFICATION_NUMBER.concat(orderId);
    }
}
