package eapli.base.ordersmanagement.survey.domain;


import javax.persistence.*;

@Entity
public class SurveyRules {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SurveyRuleID surveyRuleID;

    @Embedded
    private RuleAge ruleAge;

    @Enumerated(EnumType.STRING)
    private RuleGender ruleGender;


    protected SurveyRules() {

    }

    public SurveyRules(SurveyRuleID surveyRuleID, RuleAge ruleAge, RuleGender ruleGender) {
        this.surveyRuleID = surveyRuleID;
        this.ruleAge = ruleAge;
        this.ruleGender = ruleGender;
    }

    public SurveyRules(SurveyRuleID surveyRuleID, RuleAge ruleAge) {
        this.surveyRuleID = surveyRuleID;
        this.ruleAge = ruleAge;
    }

    public SurveyRules(SurveyRuleID surveyRuleID, RuleGender ruleGender) {
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
