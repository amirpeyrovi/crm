package ir.parto.crm.modules.server.model.entity;

import ir.parto.crm.modules.server.controller.transientObject.serverVendor.ServerVendorDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_server_vendor")
public class ServerVendor implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "server_vendor_seq", sequenceName = "server_vendor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "server_seq")
    private Long serverVendorId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;


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

    public ServerVendor() {
    }

    public ServerVendor(Long id) {
        this.serverVendorId = id;
    }

    public ServerVendor(String title, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.title = title;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServerVendorId() {
        return serverVendorId;
    }

    public ServerVendor setServerVendorId(Long serverVendorId) {
        this.serverVendorId = serverVendorId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServerVendor setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ServerVendor setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public ServerVendor setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public ServerVendor setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public ServerVendor setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ServerVendor setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public ServerVendor setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public ServerVendor setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public ServerVendorDTO convert2Object() {
        ServerVendorDTO serverVendorDTO = new ServerVendorDTO();
        if (this.serverVendorId != null) serverVendorDTO.setServerVendorId(this.serverVendorId);
        if (this.title != null) serverVendorDTO.setTitle(this.title);
        return serverVendorDTO;
    }
}
