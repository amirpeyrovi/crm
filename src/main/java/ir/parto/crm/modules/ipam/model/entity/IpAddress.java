package ir.parto.crm.modules.ipam.model.entity;

import ir.parto.crm.modules.service.model.entity.Service;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ipam_ip")
public class IpAddress implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ipam_ip_seq", sequenceName = "ipam_ip_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ipam_ip_seq")
    private Long ipAddressId;

    @Column(name = "ip_address", columnDefinition = "nvarchar2(32)")
    private String ipAddress;

    @Column(name = "ip_netmask", columnDefinition = "number(1)")
    private Integer ipNetmask;

    @Column(name = "ip_version", columnDefinition = "number(1)")
    private Integer ipVersion;

    @Column(name = "start_ip_address", columnDefinition = "nvarchar2(32)")
    private String startIpAddress;

    @Column(name = "end_ip_address", columnDefinition = "nvarchar2(32)")
    private String endIpAddress;

    @ManyToOne
    @JoinColumn(name = "ip_group_id", foreignKey = @ForeignKey(name = "ip_address_ip_group_fk"))
    private IpGroup ipGroup;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "ip_address_service_fk"))
    private Service service;

    // status => [1: active, 2: deActive, 3: assigned]
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
    private LocalDateTime isDeleted;

    public IpAddress() {
    }

    public IpAddress(String ipAddress, Integer ipNetmask, Integer ipVersion, String startIpAddress, String endIpAddress, IpGroup ipGroup, Service service, Integer status, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, LocalDateTime isDeleted) {
        this.ipAddress = ipAddress;
        this.ipNetmask = ipNetmask;
        this.ipVersion = ipVersion;
        this.startIpAddress = startIpAddress;
        this.endIpAddress = endIpAddress;
        this.ipGroup = ipGroup;
        this.service = service;
        this.status = status;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getIpAddressId() {
        return ipAddressId;
    }

    public void setIpAddressId(Long ipAddressId) {
        this.ipAddressId = ipAddressId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public IpGroup getIpGroup() {
        return ipGroup;
    }

    public void setIpGroup(IpGroup ipGroup) {
        this.ipGroup = ipGroup;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
    }
}
