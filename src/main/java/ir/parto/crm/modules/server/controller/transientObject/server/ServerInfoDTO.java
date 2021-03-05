package ir.parto.crm.modules.server.controller.transientObject.server;

import ir.parto.crm.modules.server.controller.transientObject.serverGroup.ServerGroupRelationalDTO;

import java.time.LocalDateTime;

public class ServerInfoDTO {
    private Long serverId;
    private String title;
    private String address;
    private String username;
    private String password;
    private Integer accessHash;
    private Integer ssl;
    private ServerGroupRelationalDTO serverGroup;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private int isDeleted;

    public ServerInfoDTO() {
    }

    public ServerInfoDTO(Long serverId, String title, String address, String username, String password, Integer accessHash, Integer ssl, ServerGroupRelationalDTO serverGroup, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, int isDeleted) {
        this.serverId = serverId;
        this.title = title;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accessHash = accessHash;
        this.ssl = ssl;
        this.serverGroup = serverGroup;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(Integer accessHash) {
        this.accessHash = accessHash;
    }

    public Integer getSsl() {
        return ssl;
    }

    public void setSsl(Integer ssl) {
        this.ssl = ssl;
    }

    public ServerGroupRelationalDTO getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroupRelationalDTO serverGroup) {
        this.serverGroup = serverGroup;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
