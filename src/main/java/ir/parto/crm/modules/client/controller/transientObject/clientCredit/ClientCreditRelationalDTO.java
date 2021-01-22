package ir.parto.crm.modules.client.controller.transientObject.clientCredit;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import ir.parto.crm.modules.client.model.entity.Client;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientCreditRelationalDTO implements Serializable {
    private Long clientCreditId;
    private ClientRelationalDTO client;
    private String description;
    private String caption;
    private String type;
    private Double amountIn;
    private Double amountOut;
    private Double total;
    private Long invoiceTransactionId;

    public ClientCreditRelationalDTO() {
    }

    public ClientCreditRelationalDTO(Long clientCreditId, ClientRelationalDTO client, String description, String caption, String type, Double amountIn, Double amountOut, Double total, Long invoiceTransactionId) {
        this.clientCreditId = clientCreditId;
        this.client = client;
        this.description = description;
        this.caption = caption;
        this.type = type;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.total = total;
        this.invoiceTransactionId = invoiceTransactionId;
    }

    public Long getClientCreditId() {
        return clientCreditId;
    }

    public void setClientCreditId(Long clientCreditId) {
        this.clientCreditId = clientCreditId;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public void setClient(ClientRelationalDTO client) {
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
}