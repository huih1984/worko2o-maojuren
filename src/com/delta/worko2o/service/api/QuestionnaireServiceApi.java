package com.delta.worko2o.service.api;

import com.delta.worko2o.model.EbusinessNews;
import com.delta.worko2o.model.Questionnaire;
import com.delta.worko2o.model.QuestionnaireAnswer;
import com.delta.worko2o.model.QuestionnairePic;

import java.util.Date;
import java.util.List;

/**
 * @author john
 */
public interface QuestionnaireServiceApi {

    Questionnaire qryQuestionnaire(int questionnaireId);

    QuestionnaireAnswer qryQuestionnaireAnswerById(Integer questionnaireAnswerId);

    QuestionnaireAnswer qryQuestionnaireAnswerById(Integer jobId, Integer jobHunterId);

    QuestionnairePic qryQuestionnairePicById(Integer questionnaireId);

    Questionnaire addQuestionnaire(Questionnaire questionnaire);

    void addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    QuestionnairePic addQuestionnairePic(QuestionnairePic questionnairePic);

    void modQuestionnaire(Questionnaire questionnaire, boolean ignoreNull);

    void modQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer, boolean ignoreNull);

    void modQuestionnairePic(QuestionnairePic questionnairePic, boolean ignoreNull);

    void deleteQuestionnaire(Questionnaire questionnaire);

    void deleteQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    void deleteQuestionnairePic(QuestionnairePic questionnairePic);
}
