package ir.parto.crm.modules.client.model.entity;

import ir.parto.crm.modules.client.controller.transientObject.clientExternalCode.ClientExternalCodeInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_client_external_code")
public class ClientExternalCode implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "client_seq")
    private Long clientExternalCodeId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "client_external_code_client_fk"))
    private Client client;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "external_code", columnDefinition = "nvarchar2(100)")
    private String externalCode;

    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public ClientExternalCode() {
    }

    public ClientExternalCode(Client client, String title, String externalCode) {
        this.client = client;
        this.title = title;
        this.externalCode = externalCode;
    }

    public Long getClientExternalCodeId() {
        return clientExternalCodeId;
    }

    public void setClientExternalCodeId(Long clientExternalCodeId) {
        this.clientExternalCodeId = clientExternalCodeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public ClientExternalCodeInfoDTO convert2Object() {
        ClientExternalCodeInfoDTO clientExternalCodeInfoDTO = new ClientExternalCodeInfoDTO();
        if (this.clientExternalCodeId != null)
            clientExternalCodeInfoDTO.setClientExternalCodeId(this.clientExternalCodeId);
        if (this.title != null)
            clientExternalCodeInfoDTO.setTitle(this.title);
        if (this.client != null)
            clientExternalCodeInfoDTO.setClient(this.client.convert2RelationalObject());
        if (this.externalCode != null)
            clientExternalCodeInfoDTO.setExternalCode(this.externalCode);
        if (this.createdBy != null)
            clientExternalCodeInfoDTO.setCreatedBy(this.createdBy);
        if (this.createdDate != null)
            clientExternalCodeInfoDTO.setCreatedDate(this.createdDate);
        if (this.updatedAt != null)
            clientExternalCodeInfoDTO.setUpdatedAt(this.updatedAt);
        if (this.getUpdatedBy() != null)
            clientExternalCodeInfoDTO.setUpdatedBy(this.updatedBy);
        if (this.deletedAt != null)
            clientExternalCodeInfoDTO.setDeletedAt(this.deletedAt);
        if (this.deletedBy != null)
            clientExternalCodeInfoDTO.setDeletedBy(this.deletedBy);
        if (this.isDeleted != null)
            clientExternalCodeInfoDTO.setIsDeleted(this.isDeleted);
        return clientExternalCodeInfoDTO;
    }
}
