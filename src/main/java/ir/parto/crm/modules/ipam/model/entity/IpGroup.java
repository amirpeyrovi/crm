package ir.parto.crm.modules.ipam.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ipam_group")
public class IpGroup implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_ipam_ip_seq", sequenceName = "crm_ipam_ip_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_ipam_ip_seq")
    private Long ipGroupId;

    @Column(name = "title", columnDefinition = "nvarchar2(32)")
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(32)")
    private String description;

    // owner => [1: ownership, 2: leased]
    @Column(name = "owner", columnDefinition = "number(1)")
    private Integer owner;

    @Column(name = "owner_name", columnDefinition = "nvarchar2(64)")
    private String ownerName;

    @Column(name = "start_ip_address", columnDefinition = "nvarchar2(32)")
    private String startIpAddress;

    @Column(name = "end_ip_address", columnDefinition = "nvarchar2(32)")
    private String endIpAddress;

    @Column(name = "ip_netmask", columnDefinition = "number(1)")
    private Integer ipNetmask;

    @Column(name = "ip_version", columnDefinition = "number(1)")
    private Integer ipVersion;

    // status => [1: active, 2: deActive]
    @Column(name = "status", columnDefinition = "number(1)")
    private Integer status;


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
    private LocalDateTime updatedDate;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedDate;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public IpGroup() {
    }

    public IpGroup(String title, String description, Integer owner, String ownerName, String startIpAddress, String endIpAddress, Integer ipNetmask, Integer ipVersion, Integer status, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.ownerName = ownerName;
        this.startIpAddress = startIpAddress;
        this.endIpAddress = endIpAddress;
        this.ipNetmask = ipNetmask;
        this.ipVersion = ipVersion;
        this.status = status;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getIpGroupId() {
        return ipGroupId;
    }

    public void setIpGroupId(Long ipGroupId) {
        this.ipGroupId = ipGroupId;
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

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStartIpAddress() {
        return startIpAddress;
    }

    public void setStartIpAddress(String startIpAddress) {
        this.startIpAddress = startIpAddress;
    }

    public String getEndIpAddress() {
        return endIpAddress;
    }

    public void setEndIpAddress(String endIpAddress) {
        this.endIpAddress = endIpAddress;
    }

    public Integer getIpNetmask() {
        return ipNetmask;
    }

    public void setIpNetmask(Integer ipNetmask) {
        this.ipNetmask = ipNetmask;
    }

    public Integer getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(Integer ipVersion) {
        this.ipVersion = ipVersion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
