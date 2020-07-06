package ir.parto.crm.modules.dataCenter.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_datacenter_server_config_value")
public class DataCenterServerConfigValue implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_datacenter_seq", sequenceName = "crm_datacenter_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_datacenter_seq")
    private Long configValueId;

    @ManyToOne
    @JoinColumn(name = "datacenter_server_config_id", foreignKey = @ForeignKey(name = "datacenter_server_config_value_datacenter_server_config_fk"))
    private DataCenterServerConfigGroup dataCenterServerConfigGroup;

    @ManyToOne
    @JoinColumn(name = "datacenter_server_id", foreignKey = @ForeignKey(name = "datacenter_server_config_value_datacenter_server_fk"))
    private DataCenterServer dataCenterServer;

    @Column(name = "value", columnDefinition = "nvarchar2(100)")
    private String value;


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

    public DataCenterServerConfigValue() {
    }

    public DataCenterServerConfigValue(DataCenterServerConfigGroup dataCenterServerConfigGroup, DataCenterServer dataCenterServer, String value, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.dataCenterServerConfigGroup = dataCenterServerConfigGroup;
        this.dataCenterServer = dataCenterServer;
        this.value = value;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getConfigId() {
        return configValueId;
    }

    public void setConfigId(Long configValueId) {
        this.configValueId = configValueId;
    }

    public DataCenterServerConfigGroup getDataCenterServerConfigGroup() {
        return dataCenterServerConfigGroup;
    }

    public void setDataCenterServerConfigGroup(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        this.dataCenterServerConfigGroup = dataCenterServerConfigGroup;
    }

    public DataCenterServer getDataCenterServer() {
        return dataCenterServer;
    }

    public void setDataCenterServer(DataCenterServer dataCenterServer) {
        this.dataCenterServer = dataCenterServer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
