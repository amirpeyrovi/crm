package ir.parto.crm.modules.product.controller.transientObject.product;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;

public class ProductAddDTO {

    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;
    private Long productGroupId;
    private Long serverGroupId;
    private Integer haveWorkFlow;
    private Long ticketStage;
    private Long ticketState;
    private Integer haveTax;
    private Integer adminHide;
    private Integer clientHide;
    private Integer retired;

    public ProductAddDTO(String title, String adminDescription, String clientDescription, String cover, Long productGroupId, Long serverGroupId, Integer haveWorkFlow, Long ticketStage, Long ticketState, Integer haveTax, Integer adminHide, Integer clientHide, Integer retired) {
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
        this.productGroupId = productGroupId;
        this.serverGroupId = serverGroupId;
        this.haveWorkFlow = haveWorkFlow;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
        this.haveTax = haveTax;
        this.adminHide = adminHide;
        this.clientHide = clientHide;
        this.retired = retired;
    }

    public ProductAddDTO() {
    }

    public Product convert2Object() {
        Product product = new Product();
        if (this.title != null) product.setTitle(this.title);
        if (this.adminDescription != null) product.setAdminDescription(this.adminDescription);
        if (this.clientDescription != null) product.setClientDescription(this.clientDescription);
        if (this.cover != null) product.setCover(this.cover);
        if (this.productGroupId != null) product.setProductGroup(new ProductGroup(this.productGroupId));
        if (this.serverGroupId != null) product.setServerGroup(new ServerGroup(this.serverGroupId));
        if (this.haveWorkFlow != null) product.setHaveWorkFlow(0);
        if (this.ticketStage != null) product.setTicketStage(new TicketStage(this.ticketStage));
        if (this.ticketState != null) product.setTicketState(new TicketState(this.ticketState));
        if (this.haveTax != null) product.setHaveTax(0);
        if (this.adminHide != null) product.setAdminHide(0);
        if (this.clientHide != null) product.setClientHide(0);
        if (this.retired != null) product.setRetired(0);
        return product;
    }
}
