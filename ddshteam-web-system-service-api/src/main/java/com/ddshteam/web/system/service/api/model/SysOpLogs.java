package com.ddshteam.web.system.service.api.model;

import java.io.Serializable;
import java.util.Date;

public class SysOpLogs implements Serializable {
    private String id;

    private String account;

    private String name;

    private String ip;

    private String uri;

    private Integer excuteTime;

    private String resp;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(Integer excuteTime) {
        this.excuteTime = excuteTime;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp == null ? null : resp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", name=").append(name);
        sb.append(", ip=").append(ip);
        sb.append(", uri=").append(uri);
        sb.append(", excuteTime=").append(excuteTime);
        sb.append(", resp=").append(resp);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}