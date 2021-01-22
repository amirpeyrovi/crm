package ir.parto.crm.modules.admin.model.entity;

import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminInfoDTO;
import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminRelationalDTO;
import ir.parto.crm.modules.client.model.entity.Client;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "crm_admin")
public class Admin implements UserDetails , Serializable{
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq")
    private Long adminId;

    @Column(name = "username", columnDefinition = "nvarchar2(100)")
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar2(200)")
    private String password;

    @Column(name = "first_name", columnDefinition = "nvarchar2(100)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "nvarchar2(100)")
    private String lastName;

    @Column(name = "identify_code", columnDefinition = "nvarchar2(32)")
    private String identifyCode;

    @Column(name = "phone_number", columnDefinition = "nvarchar2(16)")
    private String phoneNumber;

    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "admin_role_fk"))
    private AdminRole adminRole;

    // authority => [ADMIN, RESELLER, USER]
    @Column(name = "authority", columnDefinition = "nvarchar2(16)")
    private String auth;

    @Transient
    private Collection<? extends GrantedAuthority> authority;

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

    public Admin() {
    }

    public Admin(String username, String password, String firstName, String lastName, String identifyCode, String phoneNumber,
                 AdminRole adminRole, Collection<? extends GrantedAuthority> authority, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifyCode = identifyCode;
        this.phoneNumber = phoneNumber;
        this.adminRole = adminRole;
        this.authority = authority;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Admin(Long adminId, String firstName, String lastName, String username, String password, List<GrantedAuthority> authorities) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authority = authorities;
    }

    public Admin(String firstName, String username, String password) {
    }

    public Admin(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
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

    public static Admin create(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<String>(Arrays.asList("admin")).stream().map(role ->
                new SimpleGrantedAuthority("admin")
        ).collect(Collectors.toList());
        return new Admin(
                admin.getAdminId(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getUsername(),
                admin.getPassword(),
                authorities
        );
    }

    public AdminRelationalDTO convert2RelationalObject() {
        AdminRelationalDTO dto = new AdminRelationalDTO();
        if(this.adminId != null) dto.setAdminId(this.adminId);
        if(this.username != null) dto.setUsername(this.username);
        if(this.firstName != null) dto.setFirstName(this.firstName);
        if(this.lastName != null) dto.setLastName(this.lastName);
        if(this.adminRole != null) dto.setAdminRole(this.adminRole.convert2Object());
        return dto;
    }

    public AdminInfoDTO convert2Object(){
        AdminInfoDTO dto = new AdminInfoDTO();
        if(this.adminId != null) dto.setAdminId(this.adminId);
        if(this.firstName != null) dto.setFirstName(this.firstName);
        if(this.lastName != null) dto.setLastName(this.lastName);
        if(this.username != null) dto.setUsername(this.username);
        if(this.identifyCode != null) dto.setIdentifyCode(this.identifyCode);
        if(this.phoneNumber != null) dto.setPhoneNumber(this.phoneNumber);
        if(this.adminRole != null) dto.setAdminRole(this.adminRole.convert2Object());
        if(this.updatedAt != null) dto.setUpdatedAt(this.updatedAt);
        if(this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if(this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if(this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if(this.deletedAt != null) dto.setDeletedAt(this.deletedAt);
        if(this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        return dto;
    }
}