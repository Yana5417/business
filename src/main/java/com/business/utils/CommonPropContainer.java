package com.business.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zuozheng.hzz
 * @date 2018/07/20
 */
@Component
@ConfigurationProperties(prefix = "common.prop")
public class CommonPropContainer {

    private String adminKey;
    private String selfAccessKey;
    private Long rootUserId;
    private String ferryServiceUrl;

    private String aptServiceUrl;
    private String callSystem;
    private static String passwordKey;
    private static Boolean plainProject;
    private String smsAk;
    private String smsSk;
    public String getSmsAk() {
        return smsAk;
    }

    public void setSmsAk(String smsAk) {
        this.smsAk = smsAk;
    }

    public String getSmsSk() {
        return smsSk;
    }

    public void setSmsSk(String smsSk) {
        this.smsSk = smsSk;
    }

    public static String getPasswordKey() {
        return passwordKey;
    }

    public  void setPasswordKey(String passwordKey) {
        CommonPropContainer.passwordKey = passwordKey;
    }

    public String getCallSystem() {
        return callSystem;
    }

    public void setCallSystem(String callSystem) {
        this.callSystem = callSystem;
    }

    public String getFerryServiceUrl() {
        return ferryServiceUrl;
    }
    public void setFerryServiceUrl(String ferryServiceUrl) {
        this.ferryServiceUrl = ferryServiceUrl;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public String getSelfAccessKey() {
        return selfAccessKey;
    }

    public void setSelfAccessKey(String selfAccessKey) {
        this.selfAccessKey = selfAccessKey;
    }

    public Long getRootUserId() {
        return rootUserId;
    }

    public void setRootUserId(Long rootUserId) {
        this.rootUserId = rootUserId;
    }


    public String getAptServiceUrl() {
        return aptServiceUrl;
    }

    public void setAptServiceUrl(String aptServiceUrl) {
        this.aptServiceUrl = aptServiceUrl;
    }
    public static Boolean getPlainProject() {
        return plainProject;
    }

    public static void setPlainProject(Boolean plainProject) {
        CommonPropContainer.plainProject = plainProject;
    }
}
