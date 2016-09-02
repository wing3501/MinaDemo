package com.tccv.pojo;

import java.util.Date;

public class FileVersion {
    private Long id;

    private String appName;

    private String appKey;

    private String appUrl;

    private String appVersion;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Integer isForced;

    private Integer isReleased;

    private String displayVersion;

    private String description;
    
    

    @Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("FileVersion [id=");
		builder.append(id);
		builder.append(", appName=");
		builder.append(appName);
		builder.append(", appKey=");
		builder.append(appKey);
		builder.append(", appUrl=");
		builder.append(appUrl);
		builder.append(", appVersion=");
		builder.append(appVersion);
		builder.append(", isDelete=");
		builder.append(isDelete);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", isForced=");
		builder.append(isForced);
		builder.append(", isReleased=");
		builder.append(isReleased);
		builder.append(", displayVersion=");
		builder.append(displayVersion);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsForced() {
        return isForced;
    }

    public void setIsForced(Integer isForced) {
        this.isForced = isForced;
    }

    public Integer getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(Integer isReleased) {
        this.isReleased = isReleased;
    }

    public String getDisplayVersion() {
        return displayVersion;
    }

    public void setDisplayVersion(String displayVersion) {
        this.displayVersion = displayVersion == null ? null : displayVersion.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}