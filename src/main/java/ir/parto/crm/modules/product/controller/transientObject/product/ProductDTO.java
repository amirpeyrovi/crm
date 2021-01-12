package ir.parto.crm.modules.product.controller.transientObject.product;

import ir.parto.crm.modules.product.controller.transientObject.ProductGroup.ProductGroupRelationalDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverGroup.ServerGroupRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateRelationalDTO;

public class ProductDTO {
    private Long productId;
    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;
    private ProductGroupRelationalDTO productGroup;
    private ServerGroupRelationalDTO serverGroup;
    private Integer haveWorkFlow;
    private TicketStageRelationalDTO ticketStage;
    private TicketStateRelationalDTO ticketState;
    private Integer haveTax;
    private Integer adminHide;
    private Integer clientHide;
    private Integer retired;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String title, String adminDescription, String clientDescription, String cover, ProductGroupRelationalDTO productGroup, ServerGroupRelationalDTO serverGroup, Integer haveWorkFlow, TicketStageRelationalDTO ticketStage, TicketStateRelationalDTO ticketState, Integer haveTax, Integer adminHide, Integer clientHide, Integer retired) {
        this.productId = productId;
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

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
    }

    public ServerGroupRelationalDTO getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroupRelationalDTO serverGroup) {
        this.serverGroup = serverGroup;
    }

    public Integer getHaveWorkFlow() {
        return haveWorkFlow;
    }

    public void setHaveWorkFlow(Integer haveWorkFlow) {
        this.haveWorkFlow = haveWorkFlow;
    }

    public TicketStageRelationalDTO getTicketStage() {
        return ticketStage;
    }

    public void setTicketStage(TicketStageRelationalDTO ticketStage) {
        this.ticketStage = ticketStage;
    }

    public TicketStateRelationalDTO getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketStateRelationalDTO ticketState) {
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
}
