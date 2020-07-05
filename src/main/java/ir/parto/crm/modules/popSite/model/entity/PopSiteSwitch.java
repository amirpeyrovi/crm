package ir.parto.crm.modules.popSite.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_popsite_switch")
public class PopSiteSwitch implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_popsite_seq", sequenceName = "crm_popsite_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_popsite_seq")
    private Long switchId;

    @ManyToOne
    @JoinColumn(name = "popsite_rack_id", foreignKey = @ForeignKey(name = "popsite_switch_popsite_rack_fk"))
    private PopSiteRack popSiteRack;

    @Column(name = "port_count", columnDefinition = "number(4)")
    private Integer port_count;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "vendor", columnDefinition = "nvarchar2(100)")
    private String vendor;

    @Column(name = "start_unit", columnDefinition = "number(4)")
    private Integer startUnit;

    @Column(name = "end_unit", columnDefinition = "number(4)")
    private Integer endUnit;

    @Column(name = "total_unit", columnDefinition = "number(4)")
    private Integer totalUnit;


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

    public PopSiteSwitch() {
    }

    public PopSiteSwitch(PopSiteRack popSiteRack, Integer port_count, String title, String vendor, Integer startUnit, Integer endUnit, Integer totalUnit, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.popSiteRack = popSiteRack;
        this.port_count = port_count;
        this.title = title;
        this.vendor = vendor;
        this.startUnit = startUnit;
        this.endUnit = endUnit;
        this.totalUnit = totalUnit;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Long switchId) {
        this.switchId = switchId;
    }

    public PopSiteRack getPopSiteRack() {
        return popSiteRack;
    }

    public void setPopSiteRack(PopSiteRack popSiteRack) {
        this.popSiteRack = popSiteRack;
    }

    public Integer getPort_count() {
        return port_count;
    }

    public void setPort_count(Integer port_count) {
        this.port_count = port_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getStartUnit() {
        return startUnit;
    }

    public void setStartUnit(Integer startUnit) {
        this.startUnit = startUnit;
    }

    public Integer getEndUnit() {
        return endUnit;
    }

    public void setEndUnit(Integer endUnit) {
        this.endUnit = endUnit;
    }

    public Integer getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(Integer totalUnit) {
        this.totalUnit = totalUnit;
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
