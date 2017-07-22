package pjb.ssm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dell1 on 2017/7/10.
 */
public class UserActionLog implements Serializable {
    private long id;
    private String loginId, sessionId, ipAddrV4,osName, osVersion, broName, broVersion, requestBody, description, other, method;
    private Long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddrV4() {
        return ipAddrV4;
    }

    public void setIpAddrV4(String ipAddrV4) {
        this.ipAddrV4 = ipAddrV4;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBroName() {
        return broName;
    }

    public void setBroName(String broName) {
        this.broName = broName;
    }

    public String getBroVersion() {
        return broVersion;
    }

    public void setBroVersion(String broVersion) {
        this.broVersion = broVersion;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserActionLog{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", ipAddrV4='" + ipAddrV4 + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", broName='" + broName + '\'' +
                ", broVersion='" + broVersion + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", description='" + description + '\'' +
                ", other='" + other + '\'' +
                ", method='" + method + '\'' +
                ", time=" + time +
                '}';
    }
}
