package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_EMPLOYER_STORE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_EMPLOYER_STORE")
public class EmployerStore {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * employer_store_id
     */
    @Id
    @Column(value = "employer_store_id")
    private Integer employerStoreId;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private Integer employerId;

    /**
     * taobao_store_name
     */
    @Column(value = "taobao_store_name")
    private String taobaoStoreName;

    /**
     * taobao_level
     */
    @Column(value = "taobao_level")
    private String taobaoLevel;

    /**
     * jd_store_name
     */
    @Column(value = "jd_store_name")
    private String jdStoreName;

    /**
     * jd_level
     */
    @Column(value = "jd_level")
    private String jdLevel;

    public Integer getEmployerStoreId() {
        return this.employerStoreId;
    }

    public void setEmployerStoreId(Integer employerStoreId) {
        this.employerStoreId = employerStoreId;
    }

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getTaobaoStoreName() {
        return this.taobaoStoreName;
    }

    public void setTaobaoStoreName(String taobaoStoreName) {
        this.taobaoStoreName = taobaoStoreName;
    }

    public String getTaobaoLevel() {
        return this.taobaoLevel;
    }

    public void setTaobaoLevel(String taobaoLevel) {
        this.taobaoLevel = taobaoLevel;
    }

    public String getJdStoreName() {
        return this.jdStoreName;
    }

    public void setJdStoreName(String jdStoreName) {
        this.jdStoreName = jdStoreName;
    }

    public String getJdLevel() {
        return this.jdLevel;
    }

    public void setJdLevel(String jdLevel) {
        this.jdLevel = jdLevel;
    }

}
