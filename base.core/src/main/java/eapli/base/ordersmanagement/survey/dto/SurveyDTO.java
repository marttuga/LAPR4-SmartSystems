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

    @Override
    public String toString() {
        return
                "Survey ID:" + surveyID  +
                ", Survey Description:" + surveyDescription + "\n";
    }
}
