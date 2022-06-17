package eapli.base.ordersmanagement.survey.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDTO {
    public String surveyID;
    public String surveyDescription;

    public String getSurveyID() {
        return surveyID;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }
}
