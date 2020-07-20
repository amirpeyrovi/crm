package ir.parto.crm.modules.contract.model.entity;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_contract_service_link")
public class ContractServiceLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_contract_seq", sequenceName = "crm_contract_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_contract_seq")
    private Long contractServiceLinkId;

    @ManyToOne
    @JoinColumn(name = "contract_id", foreignKey = @ForeignKey(name = "contract_service_link_contract_template_fk"))
    private ContractTemplate contractTemplate;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "contract_service_link_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_addon_id", foreignKey = @ForeignKey(name = "contract_service_link_service_addon_fk"))
    private ServiceAddon serviceAddon;


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
