package ir.parto.crm.modules.client.entity;

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
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Column(name = "identity_code", columnDefinition = "nvarchar(32)")
    private String identityCode;

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
}
