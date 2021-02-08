package ir.parto.crm.modules.server.controller.transientObject.serverParameter;

import ir.parto.crm.modules.server.controller.transientObject.serverGroup.ServerGroupRelationalDTO;

import java.time.LocalDateTime;

public class ServerParameterInfoDTO {
    private Long serverParameterId;
    private ServerGroupRelationalDTO serverGroup;
    private String title;
    private String description;
    private String type;
    private String options;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Integer isDeleted;

    public ServerParameterInfoDTO() {
    }

    public ServerParameterInfoDTO(Long serverParameterId, ServerGroupRelationalDTO serverGroup, String title, String description, String type, String options, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.serverParameterId = serverParameterId;
        this.serverGroup = serverGroup;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServerParameterId() {
        return serverParameterId;
    }

    public void setServerParameterId(Long serverParameterId) {
        this.serverParameterId = serverParameterId;
    }

    public ServerGroupRelationalDTO getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroupRelationalDTO serverGroup) {
        this.serverGroup = serverGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
