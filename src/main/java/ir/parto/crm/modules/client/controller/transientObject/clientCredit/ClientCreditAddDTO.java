package ir.parto.crm.modules.client.controller.transientObject.clientCredit;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientCredit;

import java.io.Serializable;

public class ClientCreditAddDTO implements Serializable {
    private Long client;
    private String description;
    private String caption;
    private String type;
    private Double amountIn;
    private Double amountOut;
    private Double total;
    private Long invoiceTransactionId;

    public ClientCreditAddDTO() {
    }

    public ClientCreditAddDTO(Long client, String description, String caption, String type, Double amountIn, Double amountOut, Double total, Long invoiceTransactionId) {
        this.client = client;
        this.description = description;
        this.caption = caption;
        this.type = type;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.total = total;
        this.invoiceTransactionId = invoiceTransactionId;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(Double amountIn) {
        this.amountIn = amountIn;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Double amountOut) {
        this.amountOut = amountOut;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getInvoiceTransactionId() {
        return invoiceTransactionId;
    }

    public void setInvoiceTransactionId(Long invoiceTransactionId) {
        this.invoiceTransactionId = invoiceTransactionId;
    }

    public ClientCredit convert2Object() {
        ClientCredit dto = new ClientCredit();
        if (this.client != null) dto.setClient(new Client(this.client));
        if (this.description != null) dto.setDescription(this.description);
        if (this.caption != null) dto.setCaption(this.caption);
        if (this.type != null) dto.setType(this.type);
        if (this.amountIn != null) dto.setAmountIn(this.amountIn);
        if (this.amountOut != null) dto.setAmountOut(this.amountOut);
        if (this.total != null) dto.setTotal(this.total);
        if (this.invoiceTransactionId != null) dto.setInvoiceTransactionId(this.invoiceTransactionId);

        return dto;
    }
}