package eapli.base.ordersmanagement.survey.domain;


import javax.persistence.*;

@Entity
public class SurveyRule {



    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SurveyRuleID surveyRuleID;

    @Embedded
    private RuleAge ruleAge;

    @Enumerated(EnumType.STRING)
    private RuleGender ruleGender;


    protected SurveyRule() {

    }

    public SurveyRule(SurveyRuleID surveyRuleID, RuleAge ruleAge, RuleGender ruleGender) {
        this.surveyRuleID = surveyRuleID;
        this.ruleAge = ruleAge;
        this.ruleGender = ruleGender;
    }

    public SurveyRule(SurveyRuleID surveyRuleID, RuleAge ruleAge) {
        this.surveyRuleID = surveyRuleID;
        this.ruleAge = ruleAge;
    }

    public SurveyRule(SurveyRuleID surveyRuleID, RuleGender ruleGender) {
        this.surveyRuleID = surveyRuleID;
        this.ruleGender = ruleGender;
    }

    public SurveyRuleID getSurveyRuleID() {
        return surveyRuleID;
    }

    public RuleAge getRuleAge() {
        return ruleAge;
    }

    public RuleGender getRuleGender() {
        return ruleGender;
    }

    @Override
    public String toString() {
        return "Rule:" + surveyRuleID +
                "/ birthDay:" + ruleAge +
                "/ gender:" + ruleGender;
    }
}
