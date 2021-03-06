package ir.parto.crm.modules.client.controller.transientObject.clientExternalCode;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientInfoDTO;
import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientExternalCodeInfoDTO implements Serializable {
    private Long clientExternalCodeId;
    private ClientRelationalDTO client;
    private String title;
    private String externalCode;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private java.time.LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Integer isDeleted;

    public ClientExternalCodeInfoDTO() {
    }

    public ClientExternalCodeInfoDTO(Long clientExternalCodeId, ClientRelationalDTO client, String title, String externalCode, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.clientExternalCodeId = clientExternalCodeId;
        this.client = client;
        this.title = title;
        this.externalCode = externalCode;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getClientExternalCodeId() {
        return clientExternalCodeId;
    }

    public void setClientExternalCodeId(Long clientExternalCodeId) {
        this.clientExternalCodeId = clientExternalCodeId;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public void setClient(ClientRelationalDTO client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
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