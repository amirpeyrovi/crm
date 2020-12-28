package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.ProductGroup.ProductGroupRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product")
public class Product implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "admin_description", columnDefinition = "nvarchar2(2000)")
    private String adminDescription;

    @Column(name = "client_description", columnDefinition = "nvarchar2(2000)")
    private String clientDescription;

    @Column(name = "cover", columnDefinition = "nvarchar2(150)")
    private String cover;

    @ManyToOne
    @JoinColumn(name = "product_group_id", foreignKey = @ForeignKey(name = "product_product_group_fk"))
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "server_group_id", foreignKey = @ForeignKey(name = "product_server_group_fk"))
    private ServerGroup serverGroup;

    @Column(name = "have_work_flow", columnDefinition = "number(1)")
    private Integer haveWorkFlow ;

    @ManyToOne
    @JoinColumn(name = "ticket_stage_id", foreignKey = @ForeignKey(name = "product_ticket_stage_fk"))
    private TicketStage ticketStage;

    @ManyToOne
    @JoinColumn(name = "ticket_state_id", foreignKey = @ForeignKey(name = "product_ticket_state_fk"))
    private TicketState ticketState;

    @Column(name = "have_tax", columnDefinition = "number(1)")
    private Integer haveTax;

    @Column(name = "admin_hide", columnDefinition = "number(1)")
    private Integer adminHide;

    @Column(name = "client_hide", columnDefinition = "number(1)")
    private Integer clientHide;

    @Column(name = "retired", columnDefinition = "number(1)")
    private Integer retired;


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

    public Product() {
    }

    public Product(String title, String adminDescription, String clientDescription, String cover, ProductGroup productGroup, ServerGroup serverGroup, Integer haveWorkFlow, TicketStage ticketStage, TicketState ticketState, Integer haveTax, Integer adminHide, Integer clientHide, Integer retired, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
        this.productGroup = productGroup;
        this.serverGroup = serverGroup;
        this.haveWorkFlow = haveWorkFlow;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
        this.haveTax = haveTax;
        this.adminHide = adminHide;
        this.clientHide = clientHide;
        this.retired = retired;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminDescription() {
        return adminDescription;
    }

    public void setAdminDescription(String adminDescription) {
        this.adminDescription = adminDescription;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public ServerGroup getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroup serverGroup) {
        this.serverGroup = serverGroup;
    }

    public Integer getHaveWorkFlow() {
        return haveWorkFlow;
    }

    public void setHaveWorkFlow(Integer haveWorkFlow) {
        this.haveWorkFlow = haveWorkFlow;
    }

    public TicketStage getTicketStage() {
        return ticketStage;
    }

    public void setTicketStage(TicketStage ticketStage) {
        this.ticketStage = ticketStage;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public Integer getHaveTax() {
        return haveTax;
    }

    public void setHaveTax(Integer haveTax) {
        this.haveTax = haveTax;
    }

    public Integer getAdminHide() {
        return adminHide;
    }

    public void setAdminHide(Integer adminHide) {
        this.adminHide = adminHide;
    }

    public Integer getClientHide() {
        return clientHide;
    }

    public void setClientHide(Integer clientHide) {
        this.clientHide = clientHide;
    }

    public Integer getRetired() {
        return retired;
    }

    public void setRetired(Integer retired) {
        this.retired = retired;
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

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ProductRelationalDTO convert2RelationalObject() {
        ProductRelationalDTO dto = new ProductRelationalDTO();
        if (this.productId != null) dto.setProductId(this.productId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.productGroup != null) dto.setProductGroup(this.productGroup.convert2RelationalObject());
        return dto;
    }
}
