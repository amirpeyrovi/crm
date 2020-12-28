package ir.parto.crm.modules.client.controller.transientObject.client;

public class ClientRelationalDTO {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String identityType;
    private String identityCode1;

    public ClientRelationalDTO() {
    }

    public ClientRelationalDTO(Long clientId, String firstName, String lastName, String identityType, String identityCode1) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityType = identityType;
        this.identityCode1 = identityCode1;
    }

    public Long getClientId() {
        return clientId;
    }

    public ClientRelationalDTO setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientRelationalDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientRelationalDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getIdentityType() {
        return identityType;
    }

    public ClientRelationalDTO setIdentityType(String identityType) {
        this.identityType = identityType;
        return this;
    }

    public String getIdentityCode1() {
        return identityCode1;
    }

    public ClientRelationalDTO setIdentityCode1(String identityCode1) {
        this.identityCode1 = identityCode1;
        return this;
    }
}
