package ir.parto.crm.modules.client.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_client_credit")
public class ClientCredit implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ClientCreditId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "client_contact_fk"))
    private Client client;

    @Column(name = "description", columnDefinition = "nvarchar2(100)")
    private String description;

    @Column(name = "caption", columnDefinition = "nvarchar2(1000)")
    private String caption;

    @Column(name = "type", columnDefinition = "nvarchar2(100)")
    private String type;

    @Column(name = "amount_in", columnDefinition = "number")
    private Long amountIn;

    @Column(name = "amount_out", columnDefinition = "number")
    private Long amountOut;

    @Column(name = "total", columnDefinition = "number")
    private Long total;

    @Column(name = "invoice_transaction_id", columnDefinition = "number")
    private Long invoiceTransactionId;


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
