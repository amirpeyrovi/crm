package ir.parto.crm.modules.product.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_parameter_group_link")
public class ProductParameterGroupLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productParameterGroupLinkId;

    @ManyToOne
    @JoinColumn(name = "parameter_id", foreignKey = @ForeignKey(name = "product_parameter_group_link_product_parameter_group_fk"))
    private ProductParameterGroup productParameterGroup;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_parameter_group_link_product_fk"))
    private Product product;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public ProductParameterGroupLink() {
    }

    public ProductParameterGroupLink(ProductParameterGroup productParameterGroup, Product product, String createdBy,LocalDateTime createdDate) {
        this.productParameterGroup = productParameterGroup;
        this.product = product;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getProductParameterGroupLinkId() {
        return productParameterGroupLinkId;
    }

    public void setProductParameterGroupLinkId(Long productParameterGroupLinkId) {
        this.productParameterGroupLinkId = productParameterGroupLinkId;
    }

    public ProductParameterGroup getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroup productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

}
