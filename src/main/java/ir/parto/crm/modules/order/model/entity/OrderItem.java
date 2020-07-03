package ir.parto.crm.modules.order.model.entity;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.service.model.entity.Service;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_order_item")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_seq")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "order_item_order_fk"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "order_item_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "next_product_id", foreignKey = @ForeignKey(name = "order_item_next_product_fk"))
    private Product nextProduct;

    @ManyToOne
    @JoinColumn(name = "next_product_addon_id", foreignKey = @ForeignKey(name = "order_item_next_product_addon_fk"))
    private ProductAddon nextProductAddon;

    @ManyToOne
    @JoinColumn(name = "product_cycle_id", foreignKey = @ForeignKey(name = "order_item_product_cycle_fk"))
    private ProductCycle productCycle;

    @Column(name = "price", columnDefinition = "number(16,0)")
    private Long price;

    @Column(name = "promotion_price", columnDefinition = "number(16,0)")
    private Long promotionPrice;

    @Column(name = "tax", columnDefinition = "number(16,0)")
    private Long tax;

    @Column(name = "tax_rate", columnDefinition = "number(2,0)")
    private Long taxRate;

    @Column(name = "have_tax", columnDefinition = "number(1)")
    private Integer haveTax;

    // type => [1: buy, 2: renew, 3: change]
    @Column(name = "type", columnDefinition = "number(1)")
    private Integer type;

    // if type: 3 then change_time => [1: after Payment, 2: after expire]
    @Column(name = "change_time", columnDefinition = "number(1)")
    private Integer changeTime;

    @Column(name = "description", columnDefinition = "nvarchar2(100)")
    private String description;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public OrderItem() {
    }

    public OrderItem(Order order, Service service, Product nextProduct, ProductCycle productCycle, Long price, Long promotionPrice, Long tax, Long taxRate, Integer haveTax, Integer type, Integer changeTime, String description, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.order = order;
        this.service = service;
        this.nextProduct = nextProduct;
        this.productCycle = productCycle;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.tax = tax;
        this.taxRate = taxRate;
        this.haveTax = haveTax;
        this.type = type;
        this.changeTime = changeTime;
        this.description = description;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Product getNextProduct() {
        return nextProduct;
    }

    public void setNextProduct(Product nextProduct) {
        this.nextProduct = nextProduct;
    }

    public ProductCycle getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(ProductCycle productCycle) {
        this.productCycle = productCycle;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Long promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Long taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getHaveTax() {
        return haveTax;
    }

    public void setHaveTax(Integer haveTax) {
        this.haveTax = haveTax;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }

    public ProductAddon getNextProductAddon() {
        return nextProductAddon;
    }

    public void setNextProductAddon(ProductAddon nextProductAddon) {
        this.nextProductAddon = nextProductAddon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
