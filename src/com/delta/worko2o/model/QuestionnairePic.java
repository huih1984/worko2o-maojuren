package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_QUESTIONNAIRE_PIC的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-50-12 07:50:08 <br>
 * @since V1.0<br>
 */
@Table("T_QUESTIONNAIRE_PIC")
public class QuestionnairePic {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * questionnaire_pic_id
     */
    @Id
    @Column(value = "questionnaire_pic_id")
    private Integer questionnairePicId;

    /**
     * pic_src1
     */
    @Column(value = "pic_src1")
    private byte[] picSrc1;

    /**
     * pic_src2
     */
    @Column(value = "pic_src2")
    private byte[] picSrc2;

    /**
     * pic_src3
     */
    @Column(value = "pic_src3")
    private byte[] picSrc3;

    /**
     * pic_src4
     */
    @Column(value = "pic_src4")
    private byte[] picSrc4;

    /**
     * pic_src5
     */
    @Column(value = "pic_src5")
    private byte[] picSrc5;

    public Integer getQuestionnairePicId() {
        return this.questionnairePicId;
    }

    public void setQuestionnairePicId(Integer questionnairePicId) {
        this.questionnairePicId = questionnairePicId;
    }

    public byte[] getPicSrc1() {
        return picSrc1;
    }

    public void setPicSrc1(byte[] picSrc1) {
        this.picSrc1 = picSrc1;
    }

    public byte[] getPicSrc2() {
        return picSrc2;
    }

    public void setPicSrc2(byte[] picSrc2) {
        this.picSrc2 = picSrc2;
    }

    public byte[] getPicSrc3() {
        return picSrc3;
    }

    public void setPicSrc3(byte[] picSrc3) {
        this.picSrc3 = picSrc3;
    }

    public byte[] getPicSrc4() {
        return picSrc4;
    }

    public void setPicSrc4(byte[] picSrc4) {
        this.picSrc4 = picSrc4;
    }

    public byte[] getPicSrc5() {
        return picSrc5;
    }

    public void setPicSrc5(byte[] picSrc5) {
        this.picSrc5 = picSrc5;
    }
}
