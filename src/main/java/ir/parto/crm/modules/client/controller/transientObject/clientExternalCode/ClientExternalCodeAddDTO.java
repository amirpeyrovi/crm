package ir.parto.crm.modules.client.controller.transientObject.clientExternalCode;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientExternalCodeAddDTO implements Serializable {
    private Long clientId;
    private String title;
    private String externalCode;

    public ClientExternalCodeAddDTO() {
    }

    public ClientExternalCodeAddDTO(Long clientId, String title, String externalCode) {
        this.clientId = clientId;
        this.title = title;
        this.externalCode = externalCode;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }


    public ClientExternalCode convert2Object() {
        ClientExternalCode dto = new ClientExternalCode();
        if (this.clientId != null) dto.setClient(new Client(this.clientId));
        if (this.title != null) dto.setTitle(this.title);
        if (this.externalCode != null) dto.setExternalCode(this.externalCode);
        return dto;
    }
}
