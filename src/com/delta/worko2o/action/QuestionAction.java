package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.QuestionnaireServiceApi;
import com.delta.worko2o.util.DateUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@IocBean(singleton = false)
public class QuestionAction extends BaseAction {

    public static final Log log = Logs.get();

    @Inject
    private QuestionnaireServiceApi questionnaireService;

    @At("/page/demo/questionnaire/rques")
    public Map<String, ?> quesRAction(@Param("questionnaireId") Integer questionnaireId, HttpServletRequest request) {

        log.debug("[quesRAction] begin");

        try {
            Questionnaire questionnaire = questionnaireService.qryQuestionnaire(questionnaireId);
            setRetInfo("questionnaire", questionnaire);
        } catch (Exception e) {
            log.debug("quesRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesRAction] end");

        return retMap;
    }

    @At("/page/demo/questionnaire/rquesans")
    public Map<String, ?> quesAnsRAction(@Param("questionnaireAnswerId") Integer questionnaireAnswerId,
                                         @Param("jobHunterId") Integer jobHunterId,
                                         @Param("jobId") Integer jobId, HttpServletRequest request) {

        log.debug("[quesAnsRAction] begin");

        try {
            QuestionnaireAnswer questionnaireAnswer = null;
            if (questionnaireAnswerId != null) {
                questionnaireAnswer = questionnaireService.qryQuestionnaireAnswerById(questionnaireAnswerId);
            } else {
                questionnaireAnswer = questionnaireService.qryQuestionnaireAnswerById(jobId, jobHunterId);
            }
            setRetInfo("questionnaire_answer", questionnaireAnswer);
        } catch (Exception e) {
            log.debug("quesAnsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesAnsRAction] end");

        return retMap;
    }

    public class QuestionPic {
        public Integer questionnairePicId;

        public String picSrc1;

        public String picSrc2;

        public String picSrc3;

        public String picSrc4;

        public String picSrc5;
    }

    @At("/page/demo/questionnaire/rquespic")
    public Map<String, ?> quespicRAction(@Param("questionnairePicId") Integer questionnairePicId, HttpServletRequest request) {

        log.debug("[quespicRAction] begin");

        try {
            QuestionnairePic questionnairePic = questionnaireService.qryQuestionnairePicById(questionnairePicId);
            QuestionPic questionPic = new QuestionPic();
            questionPic.questionnairePicId = questionnairePic.getQuestionnairePicId();
            questionPic.picSrc1 = questionnairePic.getPicSrc1() == null ? null : new String(questionnairePic.getPicSrc1(), "ISO-8859-1");
            questionPic.picSrc2 = questionnairePic.getPicSrc2() == null ? null : new String(questionnairePic.getPicSrc2(), "ISO-8859-1");
            questionPic.picSrc3 = questionnairePic.getPicSrc3() == null ? null : new String(questionnairePic.getPicSrc3(), "ISO-8859-1");
            questionPic.picSrc4 = questionnairePic.getPicSrc4() == null ? null : new String(questionnairePic.getPicSrc4(), "ISO-8859-1");
            questionPic.picSrc5 = questionnairePic.getPicSrc5() == null ? null : new String(questionnairePic.getPicSrc5(), "ISO-8859-1");
            setRetInfo("questionnaire_pic", questionPic);
        } catch (Exception e) {
            log.debug("quespicRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quespicRAction] end");

        return retMap;
    }

    @At("/page/demo/questionnaire/wquespic")
    public Map<String, ?> quespicWAction(@Param("questionnaireId") Integer questionnaireId, @Param("optionId") Integer optionId,
                                         @Param("picSrc1") String picSrc1, @Param("picSrc2") String picSrc2,
                                         @Param("picSrc3") String picSrc3, @Param("picSrc4") String picSrc4,
                                         @Param("picSrc5") String picSrc5,
                                         HttpServletRequest request) {

        log.debug("[quespicWAction] begin");

        try {
            QuestionnairePic questionnairePic = new QuestionnairePic();
            questionnairePic.setPicSrc1(picSrc1 == null ? null : picSrc1.getBytes());
            questionnairePic.setPicSrc2(picSrc2 == null ? null : picSrc2.getBytes());
            questionnairePic.setPicSrc3(picSrc3 == null ? null : picSrc3.getBytes());
            questionnairePic.setPicSrc4(picSrc4 == null ? null : picSrc4.getBytes());
            questionnairePic.setPicSrc5(picSrc5 == null ? null : picSrc5.getBytes());
            QuestionnairePic questionnairePic1 = questionnaireService.addQuestionnairePic(questionnairePic);
            setRetInfo("questionnaire_pic_id", questionnairePic1.getQuestionnairePicId());
        } catch (Exception e) {
            log.debug("quespicWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quespicWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/wques")
    public Map<String, ?> quesWAction(@Param("questionnaireId") Integer questionnaireId, @Param("content") String content,
                                      @Param("fitType") String fitType, @Param("isOpen") Integer isOpen,
                                      @Param("jobId") Integer jobId, @Param("timeLimit") String timeLimit,
                                      HttpServletRequest request) {

        log.debug("[quesWAction] begin");

        try {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaire.setContent(content);
            questionnaire.setFitType(fitType);
            questionnaire.setIsOpen(isOpen);
            questionnaire.setJobId(jobId);
            questionnaire.setTimeLimit(timeLimit);
            questionnaire.setUpdateTime(DateUtil.getNowDate());
            Questionnaire questionnaire1 = questionnaireService.addQuestionnaire(questionnaire);
            setRetInfo("questionnaire_id", questionnaire1.getQuestionnaireId());
        } catch (Exception e) {
            log.debug("quesWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/wquesans")
    public Map<String, ?> quesansWAction(@Param("jobHunterId") Integer jobHunterId, @Param("answer") String answer,
                                         @Param("jobId") Integer jobId, @Param("questionnaireId") Integer questionnaireId,
                                         @Param("checkans") String checkans,
                                         HttpServletRequest request) {

        log.debug("[quesansWAction] begin");

        try {
            QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
            questionnaireAnswer.setAnswer(answer);
            questionnaireAnswer.setJobId(jobId);
            questionnaireAnswer.setJobHunterId(jobHunterId);
            questionnaireAnswer.setQuestionnaireId(questionnaireId);
            questionnaireAnswer.setCheckans(checkans);
            questionnaireService.addQuestionnaireAnswer(questionnaireAnswer);
        } catch (Exception e) {
            log.debug("quesansWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesansWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/mquespic")
    public Map<String, ?> quespicMAction(@Param("questionnaireId") Integer questionnaireId, @Param("optionId") Integer optionId,
                                         @Param("picSrc1") String picSrc1, @Param("picSrc2") String picSrc2,
                                         @Param("picSrc3") String picSrc3, @Param("picSrc4") String picSrc4,
                                         @Param("picSrc5") String picSrc5,
                                         HttpServletRequest request) {

        log.debug("[quespicWAction] begin");

        try {
            QuestionnairePic questionnairePic = new QuestionnairePic();
            questionnairePic.setPicSrc1(picSrc1.getBytes());
            questionnairePic.setPicSrc2(picSrc2.getBytes());
            questionnairePic.setPicSrc3(picSrc3.getBytes());
            questionnairePic.setPicSrc4(picSrc4.getBytes());
            questionnairePic.setPicSrc5(picSrc5.getBytes());
            questionnaireService.modQuestionnairePic(questionnairePic, true);
        } catch (Exception e) {
            log.debug("quespicWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quespicWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/mques")
    public Map<String, ?> quesMAction(@Param("questionnaireId") Integer questionnaireId, @Param("content") String content,
                                      @Param("fitType") String fitType, @Param("isOpen") Integer isOpen,
                                      @Param("jobId") Integer jobId, @Param("timeLimit") String timeLimit,
                                      HttpServletRequest request) {

        log.debug("[quesWAction] begin");

        try {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaire.setContent(content);
            questionnaire.setFitType(fitType);
            questionnaire.setIsOpen(isOpen);
            questionnaire.setJobId(jobId);
            questionnaire.setTimeLimit(timeLimit);
            questionnaire.setUpdateTime(DateUtil.getNowDate());
            questionnaireService.modQuestionnaire(questionnaire, true);
        } catch (Exception e) {
            log.debug("quesWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/mquesans")
    public Map<String, ?> quesansMAction(@Param("jobHunterId") Integer jobHunterId, @Param("answer") String answer,
                                         @Param("questionnaireId") Integer questionnaireId,
                                         @Param("checkans") String checkans,
                                         HttpServletRequest request) {

        log.debug("[quesansWAction] begin");

        try {
            QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
            questionnaireAnswer.setAnswer(answer);
            questionnaireAnswer.setJobHunterId(jobHunterId);
            questionnaireAnswer.setQuestionnaireId(questionnaireId);
            questionnaireAnswer.setCheckans(checkans);
            questionnaireService.modQuestionnaireAnswer(questionnaireAnswer, true);
        } catch (Exception e) {
            log.debug("quesansWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesansWAction] end");

        return retMap;
    }

    @At("/page/demo/questionnaire/dquespic")
    public Map<String, ?> quespicDAction(@Param("questionnairePicId") Integer questionnairePicId,
                                         HttpServletRequest request) {

        log.debug("[quespicDAction] begin");

        try {
            QuestionnairePic questionnairePic = new QuestionnairePic();
            questionnairePic.setQuestionnairePicId(questionnairePicId);
            questionnaireService.deleteQuestionnairePic(questionnairePic);
        } catch (Exception e) {
            log.debug("quespicDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quespicDAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/dques")
    public Map<String, ?> quesDAction(@Param("questionnaireId") Integer questionnaireId,
                                      HttpServletRequest request) {

        log.debug("[quesWAction] begin");

        try {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaireService.deleteQuestionnaire(questionnaire);
        } catch (Exception e) {
            log.debug("quesWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesWAction] end");

        return retMap;
    }


    @At("/page/demo/questionnaire/dquesans")
    public Map<String, ?> quesansDAction(@Param("questionnaireAnswerId") Integer questionnaireAnswerId,
                                         HttpServletRequest request) {

        log.debug("[quesansWAction] begin");

        try {
            QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
            questionnaireAnswer.setQuestionnaireAnswerId(questionnaireAnswerId);
            questionnaireService.deleteQuestionnaireAnswer(questionnaireAnswer);
        } catch (Exception e) {
            log.debug("quesansWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[quesansWAction] end");

        return retMap;
    }
}
