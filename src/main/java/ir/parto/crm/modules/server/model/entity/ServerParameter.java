package ir.parto.crm.modules.server.model.entity;

import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterInfoDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_server_parameter")
public class ServerParameter implements Serializable {
    @Id
    @SequenceGenerator(name = "server_seq", sequenceName = "server_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "server_seq")
    private Long serverParameterId;

    @ManyToOne
    @JoinColumn(name = "server_group_id", foreignKey = @ForeignKey(name = "server_parameter_server_group_fk"))
    private ServerGroup serverGroup;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(200)")
    private String description;

    @Column(name = "type", columnDefinition = "nvarchar2(16)")
    private String type;

    @Column(name = "options", columnDefinition = "nvarchar2(500)")
    private String options;

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

    public ServerParameter() {
    }

    public ServerParameter(ServerGroup serverGroup, String title, String description, String type, String options, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.serverGroup = serverGroup;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public ServerParameter(Long serverParameterId) {
        this.serverParameterId = serverParameterId;
    }

    public Long getServerParameterId() {
        return serverParameterId;
    }

    public void setServerParameterId(Long serverParameterId) {
        this.serverParameterId = serverParameterId;
    }

    public ServerGroup getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroup serverGroup) {
        this.serverGroup = serverGroup;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public ServerParameterRelationalDTO convert2RelationalObject() {
        ServerParameterRelationalDTO dto = new ServerParameterRelationalDTO();
        if (this.serverParameterId != null) dto.setServerParameterId(this.serverParameterId);
        if (this.serverGroup != null) dto.setServerGroup(this.serverGroup.convert2RelationalObject());
        if (this.title != null) dto.setTitle(this.title);
        if (this.description != null) dto.setDescription(this.description);
        if (this.type != null) dto.setType(this.type);
        if (this.options != null) dto.setOptions(this.options);
        return dto;
    }

    public ServerParameterDTO convert2Object() {
        ServerParameterDTO dto = new ServerParameterDTO();
        if (this.serverParameterId != null) dto.setServerParameterId(this.serverParameterId);
        if (this.serverGroup != null) dto.setServerGroup(this.serverGroup.convert2RelationalObject());
        if (this.title != null) dto.setTitle(this.title);
        if (this.description != null) dto.setDescription(this.description);
        if (this.type != null) dto.setType(this.type);
        if (this.options != null) dto.setOptions(this.options);
        return dto;
    }

    public ServerParameterInfoDTO convert2InfoObject() {
        ServerParameterInfoDTO dto = new ServerParameterInfoDTO();
        if (this.serverParameterId != null) dto.setServerParameterId(this.serverParameterId);
        if (this.serverGroup != null) dto.setServerGroup(this.serverGroup.convert2RelationalObject());
        if (this.title != null) dto.setTitle(this.title);
        if (this.description != null) dto.setDescription(this.description);
        if (this.type != null) dto.setType(this.type);
        if (this.options != null) dto.setOptions(this.options);
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedAt != null) dto.setUpdatedAt(this.updatedAt);
        if (this.deletedAt != null) dto.setDeletedAt(this.deletedAt);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }
}
