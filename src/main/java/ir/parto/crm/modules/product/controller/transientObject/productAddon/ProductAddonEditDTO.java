package ir.parto.crm.modules.product.controller.transientObject.productAddon;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;

public class ProductAddonEditDTO {
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

    public ProductAddonEditDTO() {
    }

    public ProductAddonEditDTO(String title, String adminDescription, String clientDescription, String cover, Long productGroupId, Long serverGroupId, Integer haveWorkFlow, Long ticketStageId, Long ticketStateId, Integer haveTax, Integer adminHide, Integer clientHide, Integer retired) {
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

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
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

    public ProductAddon convert2Object() {
        ProductAddon productAddon = new ProductAddon();
        if (this.title != null) productAddon.setTitle(this.title);
        if (this.adminDescription != null) productAddon.setAdminDescription(this.adminDescription);
        if (this.clientDescription != null) productAddon.setClientDescription(this.clientDescription);
        if (this.cover != null) productAddon.setCover(this.cover);
        if (this.productGroupId != null) productAddon.setProductGroup(new ProductGroup(this.productGroupId));
        if (this.serverGroupId != null) productAddon.setServerGroup(new ServerGroup(this.serverGroupId));
        if (this.haveWorkFlow != null) productAddon.setHaveWorkFlow(this.haveWorkFlow);
        if (this.ticketStageId != null) productAddon.setTicketStage(new TicketStage(this.ticketStageId));
        if (this.ticketStateId != null) productAddon.setTicketState(new TicketState(this.ticketStateId));
        if (this.haveTax != null) productAddon.setHaveTax(this.haveTax);
        if (this.adminHide != null) productAddon.setAdminHide(this.adminHide);
        if (this.clientHide != null) productAddon.setClientHide(this.clientHide);
        if (this.retired != null) productAddon.setRetired(this.retired);
        return productAddon;
    }
}
