package ir.parto.crm.modules.client.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_client_contact")
public class ClientContact implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientContactId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "client_contact_fk"))
    private Client client;

    @Column(name = "first_name", columnDefinition = "nvarchar2(100)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "nvarchar2(100)")
    private String lastName;

    @Column(name = "father_name", columnDefinition = "nvarchar2(100)")
    private String fatherName;

    @Column(name = "birth_date", columnDefinition = "datetime")
    private LocalDate birthDate;

    @Column(name = "phone_number", columnDefinition = "nvarchar(32)")
    private String phoneNumber;

    @Column(name = "phone_number", columnDefinition = "nvarchar(32)")
    private String mobileNumber;

    @Column(name = "identity_type", columnDefinition = "nvarchar(32)")
    private String identityType;

    @Column(name = "identity_code1", columnDefinition = "nvarchar(32)")
    private String identityCode1;

    @Column(name = "identity_code2", columnDefinition = "nvarchar(32)")
    private String identityCode2;

    @Column(name = "identity_code3", columnDefinition = "nvarchar(32)")
    private String identityCode3;

    @Column(name = "address", columnDefinition = "nvarchar(500)")
    private String address;

    @Column(name = "address2", columnDefinition = "nvarchar(500)")
    private String address2;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_by", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public ClientContact() {
    }

    public ClientContact(Client client, String firstName, String lastName, String fatherName, LocalDate birthDate, String phoneNumber, String mobileNumber, String identityType, String identityCode1, String identityCode2, String identityCode3, String address, String address2, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.client = client;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.identityType = identityType;
        this.identityCode1 = identityCode1;
        this.identityCode2 = identityCode2;
        this.identityCode3 = identityCode3;
        this.address = address;
        this.address2 = address2;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getClientContactId() {
        return clientContactId;
    }

    public void setClientContactId(Long clientContactId) {
        this.clientContactId = clientContactId;
    }
}