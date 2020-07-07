package ir.parto.crm.modules.dataCenter.model.entity;

import ir.parto.crm.modules.popSite.model.entity.PopSiteSwitch;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_datacenter_switch_port")
public class DataCenterSwitchPort implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_popsite_seq", sequenceName = "crm_popsite_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_popsite_seq")
    private Long portId;

    @ManyToOne
    @JoinColumn(name = "datacenter_switch_id", foreignKey = @ForeignKey(name = "datacenter_port_datacenter_switch_fk"))
    private DataCenterSwitch dataCenterSwitch;

    // status => [1: active, 2: deActive, 3: reserved, 4: sold]
    @Column(name = "status", columnDefinition = "number(1)")
    private Integer status;

    @Column(name = "port_number", columnDefinition = "number(4)")
    private Integer number;


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

    public DataCenterSwitchPort() {
    }

    public DataCenterSwitchPort(DataCenterSwitch dataCenterSwitch, Integer status, Integer number, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.dataCenterSwitch = dataCenterSwitch;
        this.status = status;
        this.number = number;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public DataCenterSwitch getDataCenterSwitch() {
        return dataCenterSwitch;
    }

    public void setDataCenterSwitch(DataCenterSwitch dataCenterSwitch) {
        this.dataCenterSwitch = dataCenterSwitch;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
