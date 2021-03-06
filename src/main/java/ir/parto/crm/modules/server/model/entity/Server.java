package ir.parto.crm.modules.server.model.entity;

import ir.parto.crm.modules.server.controller.transientObject.server.ServerDTO;
import ir.parto.crm.modules.server.controller.transientObject.server.ServerInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_server")
public class Server implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "server_seq", sequenceName = "server_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "server_seq")
    private Long serverId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "address", columnDefinition = "nvarchar2(200)")
    private String address;

    @Column(name = "username", columnDefinition = "nvarchar2(50)")
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar2(50)")
    private String password;

    @Column(name = "hash_access", columnDefinition = "nvarchar2(50)")
    private String accessHash;

    @Column(name = "ssl", columnDefinition = "number(1)")
    private Integer ssl;

    @ManyToOne
    @JoinColumn(name = "server_group_id", foreignKey = @ForeignKey(name = "server_server_group_fk"))
    private ServerGroup serverGroup;


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

    public Server() {
    }

    public Server(String title, String address, String username, String password, String accessHash, Integer ssl, ServerGroup serverGroup, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.title = title;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accessHash = accessHash;
        this.ssl = ssl;
        this.serverGroup = serverGroup;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(String accessHash) {
        this.accessHash = accessHash;
    }

    public Integer getSsl() {
        return ssl;
    }

    public void setSsl(Integer ssl) {
        this.ssl = ssl;
    }

    public ServerGroup getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroup serverGroup) {
        this.serverGroup = serverGroup;
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

    public ServerDTO convert2Object() {
        ServerDTO dto = new ServerDTO();
        if (this.serverId != null) dto.setServerId(this.serverId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.address != null) dto.setAddress(this.address);
        if (this.username != null) dto.setUsername(this.username);
        if (this.password != null) dto.setPassword(this.password);
        if (this.accessHash != null) dto.setAccessHash(this.accessHash);
        if (this.ssl != null) dto.setSsl(this.ssl);
        if (this.serverGroup != null) dto.setServerGroup(this.serverGroup.convert2RelationalObject());
        return dto;
    }

    public ServerInfoDTO convert2InfoObject() {
        ServerInfoDTO dto = new ServerInfoDTO();
        if (this.serverId != null) dto.setServerId(this.serverId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.address != null) dto.setAddress(this.address);
        if (this.username != null) dto.setUsername(this.username);
        if (this.password != null) dto.setPassword(this.password);
        if (this.accessHash != null) dto.setAccessHash(this.accessHash);
        if (this.ssl != null) dto.setSsl(this.ssl);
        if (this.serverGroup != null) dto.setServerGroup(this.serverGroup.convert2RelationalObject());
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
