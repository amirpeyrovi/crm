package ir.parto.crm.modules.payment.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_payment_gateway")
public class Gateway implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_payment_seq", sequenceName = "crm_payment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_payment_seq")
    private Long gatewayId;

    @Column(name = "title", columnDefinition = "nvarchar2(50)")
    private String title;

    @Column(name = "address", columnDefinition = "nvarchar2(100)")
    private String address;

    @Column(name = "merchant_id", columnDefinition = "nvarchar2(100)")
    private String merchantId;

    @Column(name = "customer_id", columnDefinition = "nvarchar2(100)")
    private String customerId;

    @Column(name = "acceptor_id", columnDefinition = "nvarchar2(100)")
    private String acceptorId;

    @Column(name = "gateway_number", columnDefinition = "nvarchar2(100)")
    private String gatewayNum;

    @Column(name = "identification_code", columnDefinition = "nvarchar2(100)")
    private String identificationCode;


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
}
