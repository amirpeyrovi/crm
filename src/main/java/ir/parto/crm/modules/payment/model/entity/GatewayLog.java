package ir.parto.crm.modules.payment.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_payment_gateway_log")
public class GatewayLog implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_payment_seq", sequenceName = "crm_payment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_payment_seq")
    private Long gatewayId;

    // status => [1: success, 2: error, 3: attack]
    @Column(name = "status", columnDefinition = "number(1)")
    private Integer status;

    @Column(name = "request", columnDefinition = "nvarchar2(1000)")
    private String request;

    @Column(name = "response", columnDefinition = "nvarchar2(1000)")
    private String response;

    @ManyToOne
    @JoinColumn(name = "gateway_id", foreignKey = @ForeignKey(name = "gateway_log_gateway_fk"))
    private Gateway gateway;


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
