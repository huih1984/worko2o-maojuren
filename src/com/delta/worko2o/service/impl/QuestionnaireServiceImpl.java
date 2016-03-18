package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.NewsServiceApi;
import com.delta.worko2o.service.api.QuestionnaireServiceApi;
import com.delta.worko2o.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Date;
import java.util.List;

@IocBean(name = "questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    @Override
    public Questionnaire qryQuestionnaire(int questionnaireId) {

        log.debug("[questionnaireService]-[qryQuestionnaire]-begin");

        Questionnaire questionnaire = dao.fetch(Questionnaire.class, questionnaireId);

        log.debug("[questionnaireService]-[qryQuestionnaire]-end");

        return questionnaire;
    }

    @Override
    public QuestionnaireAnswer qryQuestionnaireAnswerById(Integer questionnaireAnswerId) {

        log.debug("[questionnaireService]-[qryQuestionnaireAnswerById]-begin");

        QuestionnaireAnswer questionnaireAnswer = dao.fetch(QuestionnaireAnswer.class, questionnaireAnswerId);

        log.debug("[questionnaireService]-[qryQuestionnaireAnswerById]-end");

        return questionnaireAnswer;
    }

    @Override
    public QuestionnaireAnswer qryQuestionnaireAnswerById(Integer jobId, Integer jobHunterId) {
        log.debug("[questionnaireService]-[qryQuestionnaireAnswerById]-end");
        Criteria cri = Cnd.cri();
        cri.where().and("job_id", "=", jobId).and("job_hunter_id", "=", jobHunterId);

        List<QuestionnaireAnswer> tempList = dao.query(QuestionnaireAnswer.class, cri, null);
        log.debug("[questionnaireService]-[qryQuestionnaireAnswerById]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public QuestionnairePic qryQuestionnairePicById(Integer questionnairePicId) {

        log.debug("[questionnaireService]-[qryQuestionnairePicById]-begin");

        QuestionnairePic questionnairePic = dao.fetch(QuestionnairePic.class, questionnairePicId);

        log.debug("[questionnaireService]-[qryQuestionnairePicById]-end");

        return questionnairePic;
    }

    @Override
    public Questionnaire addQuestionnaire(Questionnaire questionnaire) {

        log.debug("[questionnaireService]-[addQuestionnaire]-begin");

        Questionnaire questionnaire1 = dao.insert(questionnaire);

        log.debug("[questionnaireService]-[addQuestionnaire]-end");

        return questionnaire1;
    }

    @Override
    public void addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {

        log.debug("[questionnaireService]-[addQuestionnaireAnswer]-begin");

        dao.insert(questionnaireAnswer);

        log.debug("[questionnaireService]-[addQuestionnaireAnswer]-end");
    }

    @Override
    public QuestionnairePic addQuestionnairePic(QuestionnairePic questionnairePic) {

        log.debug("[questionnaireService]-[addQuestionnairePic]-begin");

        QuestionnairePic questionnairePic1 = dao.insert(questionnairePic);

        log.debug("[questionnaireService]-[addQuestionnairePic]-end");

        return questionnairePic1;
    }

    @Override
    public void modQuestionnaire(Questionnaire questionnaire, boolean ignoreNull) {

        log.debug("[questionnaireService]-[addQuestionnairePic]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(Questionnaire.class, true)).update(questionnaire);
        } else {
            dao.update(questionnaire);
        }

        log.debug("[questionnaireService]-[addQuestionnairePic]-end");
    }

    @Override
    public void modQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer, boolean ignoreNull) {

        log.debug("[questionnaireService]-[modQuestionnaireAnswer]-begin");
        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(QuestionnaireAnswer.class, true)).update(questionnaireAnswer);
        } else {
            dao.update(questionnaireAnswer);
        }
        log.debug("[questionnaireService]-[modQuestionnaireAnswer]-end");
    }

    @Override
    public void modQuestionnairePic(QuestionnairePic questionnairePic, boolean ignoreNull) {

        log.debug("[questionnaireService]-[modQuestionnairePic]-begin");
        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(QuestionnairePic.class, true)).update(questionnairePic);
        } else {
            dao.update(questionnairePic);
        }
        log.debug("[questionnaireService]-[modQuestionnairePic]-end");
    }

    @Override
    public void deleteQuestionnaire(Questionnaire questionnaire) {

        log.debug("[questionnaireService]-[deleteQuestionnaire]-begin");

        dao.delete(questionnaire);

        log.debug("[questionnaireService]-[deleteQuestionnaire]-end");
    }

    @Override
    public void deleteQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {

        log.debug("[questionnaireService]-[deleteQuestionnaireAnswer]-begin");

        dao.delete(questionnaireAnswer);

        log.debug("[questionnaireService]-[deleteQuestionnaireAnswer]-end");
    }

    @Override
    public void deleteQuestionnairePic(QuestionnairePic questionnairePic) {

        log.debug("[questionnaireService]-[deleteQuestionnairePic]-begin");

        dao.delete(questionnairePic);

        log.debug("[questionnaireService]-[deleteQuestionnairePic]-end");
    }
}
