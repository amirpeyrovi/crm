package ir.parto.crm.modules.popSite.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_popsite_radio")
public class PopSiteRadio implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_popsite_seq", sequenceName = "crm_popsite_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_popsite_seq")
    private Long radioId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @ManyToOne
    @JoinColumn(name = "vendor", foreignKey = @ForeignKey(name = "popsite_radio_popsite_vendor_fk"))
    private PopSiteVendor popSiteVendor;

    @ManyToOne
    @JoinColumn(name = "popsite_port_id", foreignKey = @ForeignKey(name = "popsite_radio_popsite_port_fk"))
    private PopSitePort popSitePort;

    @ManyToOne
    @JoinColumn(name = "popsite_tower_branch_id", foreignKey = @ForeignKey(name = "popsite_radio_popsite_tower_branch_fk"))
    private PopSiteTowerBranch popSiteTowerBranch;


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

    public PopSiteRadio() {
    }

    public PopSiteRadio(String title, PopSiteVendor popSiteVendor, PopSitePort popSitePort, PopSiteTowerBranch popSiteTowerBranch, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.title = title;
        this.popSiteVendor = popSiteVendor;
        this.popSitePort = popSitePort;
        this.popSiteTowerBranch = popSiteTowerBranch;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getRadioId() {
        return radioId;
    }

    public void setRadioId(Long radioId) {
        this.radioId = radioId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PopSiteVendor getPopSiteVendor() {
        return popSiteVendor;
    }

    public void setPopSiteVendor(PopSiteVendor popSiteVendor) {
        this.popSiteVendor = popSiteVendor;
    }

    public PopSitePort getPopSitePort() {
        return popSitePort;
    }

    public void setPopSitePort(PopSitePort popSitePort) {
        this.popSitePort = popSitePort;
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

    public PopSiteTowerBranch getPopSiteTowerBranch() {
        return popSiteTowerBranch;
    }

    public void setPopSiteTowerBranch(PopSiteTowerBranch popSiteTowerBranch) {
        this.popSiteTowerBranch = popSiteTowerBranch;
    }
}
