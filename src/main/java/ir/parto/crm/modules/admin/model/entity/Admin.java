package ir.parto.crm.modules.admin.model.entity;

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
public class Admin implements UserDetails {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq")
    private Long adminId;

    @Column(name = "username", columnDefinition = "nvarchar2(100)", unique = true)
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

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "admin_client_fk"))
    private Client client;

    // authority => [ADMIN, RESELLER, USER]
    @Column(name = "authority", columnDefinition = "nvarchar2(16)")
    private Collection<? extends GrantedAuthority> authority;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public Admin() {
    }

    public Admin(String username, String password, String firstName, String lastName, String identifyCode, String phoneNumber, AdminRole adminRole, Client client, Collection<? extends GrantedAuthority> authority, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifyCode = identifyCode;
        this.phoneNumber = phoneNumber;
        this.adminRole = adminRole;
        this.client = client;
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
    }

    public Admin(String firstName, String username, String password) {
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
    }

    public static Admin create(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<String>(Arrays.asList("admin" , "client" , "reseller")).stream().map(role ->
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

}
