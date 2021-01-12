package ir.parto.crm.modules.product.controller.transientObject.product;


import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;

public class ProductEditDTO {
    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;
    private Long productGroupId;
    private Long serverGroupId;
    private Integer haveWorkFlow;
    private Long ticketStageId;
    private Long ticketStateId;
    private Integer haveTax;
    private Integer adminHide;
    private Integer clientHide;
    private Integer retired;

    public ProductEditDTO() {
    }

    public ProductEditDTO(String title, String adminDescription, String clientDescription, String cover, Long productGroupId, Long serverGroupId, Integer haveWorkFlow, Long ticketStageId, Long ticketStateId, Integer haveTax, Integer adminHide, Integer clientHide, Integer retired) {
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
        this.productGroupId = productGroupId;
        this.serverGroupId = serverGroupId;
        this.haveWorkFlow = haveWorkFlow;
        this.ticketStageId = ticketStageId;
        this.ticketStateId = ticketStateId;
        this.haveTax = haveTax;
        this.adminHide = adminHide;
        this.clientHide = clientHide;
        this.retired = retired;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
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

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public Integer getHaveWorkFlow() {
        return haveWorkFlow;
    }

    public void setHaveWorkFlow(Integer haveWorkFlow) {
        this.haveWorkFlow = haveWorkFlow;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public void setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public void setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
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

    public Product convert2Object() {
        Product product = new Product();
        if (this.title != null) product.setTitle(this.title);
        if (this.adminDescription != null) product.setAdminDescription(this.adminDescription);
        if (this.clientDescription != null) product.setClientDescription(this.clientDescription);
        if (this.cover != null) product.setCover(this.cover);
        if (this.productGroupId != null) product.setProductGroup(new ProductGroup(this.productGroupId));
        if (this.serverGroupId != null) product.setServerGroup(new ServerGroup(this.serverGroupId));
        if (this.haveWorkFlow != null) product.setHaveWorkFlow(this.haveWorkFlow);
        if (this.ticketStageId != null) product.setTicketStage(new TicketStage(this.ticketStageId));
        if (this.ticketStateId != null) product.setTicketState(new TicketState(this.ticketStateId));
        if (this.haveTax != null) product.setHaveTax(this.haveTax);
        if (this.adminHide != null) product.setAdminHide(this.adminHide);
        if (this.clientHide != null) product.setClientHide(this.clientHide);
        if (this.retired != null) product.setRetired(this.retired);
        return product;
    }
}
