grammar FormGrammar;

lprog: start+;

start : allParameter;

allParameter:  parameterId parameterTitle parameterWelcomeMes parameterSection+ parameterFinalMes
    | parameterId parameterTitle parameterSection+ parameterFinalMes ;

parameterSection : 'Section: ' parameterSectionId parameterSectionTitle parameterSectionDesc parameterOblig parameterQuestion+
  'Section: ' parameterSectionId parameterSectionTitle parameterSectionDesc parameterOblig parameterQuestion+
  'Section: ' parameterSectionId parameterSectionTitle parameterSectionDesc parameterOblig parameterQuestion+   ;

parameterQuestion : parameterQuestionId parameterOblig parameterQ parameterQuestionType  ;

parameterId : STRING SIGNAL INT ;

parameterTitle : 'Title: ' STRING SPACE STRING END ;

parameterQ : (parameterStrings SPACE)* STRING END
    | (STRING SPACE INT SPACE)* (parameterStrings SPACE)* STRING END   ;

parameterText : parameterSentence parameterText |  parameterSentence ;

parameterSentence : parameterStrings | parameterStrings END;

parameterWelcomeMes : 'Welcome Message:'  (STRING SPACE)* STRING SPACE INT SPACE STRING END (STRING SPACE)* STRING END | ;

parameterSectionId : CHOICE ;

parameterSectionTitle : 'STitle: ' STRING SPACE STRING SPACE STRING END
    | 'STitle: ' STRING END  ;

parameterSectionDesc : 'Description: ' (parameterText SPACE)* END | 'Description: ' parameterText  END | ;

parameterOblig : MANDATORY | OPTIONAL;

parameterStrings : STRING | STRING parameterStrings ;

parameterQuestionId : QUESTION ;

parameterQuestionType : 'Type: ' parameterType ;

parameterType : parameterDiffType ;

parameterDiffType : TYPESINGLE parameterSingleChoice | parameterFreeText | parameterMultipleChoice ;

parameterSingleChoice : (CHOICE SPACE STRING END)+ | (CHOICE SPACE INT END)+  ;

parameterFreeText: TYPEFREE ;

parameterMultipleChoice: parameterSingleChoice parameterSingleChoice ;

parameterFinalMes : 'Final Message: ' parameterText | ;

INT : [0-9]+ ;
STRING : [A-Za-z0-9]+ | '_' | '\''  ;
END : '.' | '?' | | ':' | ';' | ',' ;
QUESTION : 'Q' [0-9]+ '.' ;
CHOICE : INT '.' ;
QUEST : 'Questionnaire' ;
SECTION : 'Section: ' ;
SIGNAL : ':' | '-' | '.' ;
MANDATORY : 'Obligatoriness: Mandatory.' ;
OPTIONAL : 'Obligatoriness: Optional.' ;
TYPESINGLE : 'Single-Choice.' | 'SINGLE-CHOICE.' ;
TYPEFREE: 'Free-Text.' | 'FREE-TEXT.' ;
EMAIL : STRING '@' STRING '.' STRING  ;
NEWLINE : ('\r'? '\n' | '\r' )+ -> skip;
SPACE : [,-/: \t]+ ;
SPACE1: [ \t] -> skip;
OBLIGATORINESS : 'Mandatory.' | 'Optional.'  ;

