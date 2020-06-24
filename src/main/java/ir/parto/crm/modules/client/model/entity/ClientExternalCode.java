package ir.parto.crm.modules.client.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_client_external_code")
public class ClientExternalCode implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "client_seq")
    private Long clientExternalCodeId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "client_external_code_client_fk"))
    private Client client;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "external_code", columnDefinition = "nvarchar2(100)")
    private String externalCode;

    public ClientExternalCode() {
    }

    public ClientExternalCode(Client client, String title, String externalCode) {
        this.client = client;
        this.title = title;
        this.externalCode = externalCode;
    }

    public Long getClientExternalCodeId() {
        return clientExternalCodeId;
    }

    public void setClientExternalCodeId(Long clientExternalCodeId) {
        this.clientExternalCodeId = clientExternalCodeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
}
