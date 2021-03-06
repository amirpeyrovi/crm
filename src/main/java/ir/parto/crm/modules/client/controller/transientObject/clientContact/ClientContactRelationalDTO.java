package ir.parto.crm.modules.client.controller.transientObject.clientContact;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import java.time.LocalDate;

public class ClientContactRelationalDTO {
    private Long clientContactId;
    private ClientRelationalDTO client;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String mobileNumber;
    private String identityType;
    private String identityCode1;
    private String identityCode2;
    private String identityCode3;
    private String address;
    private String address2;

    public ClientContactRelationalDTO() {
    }

    public ClientContactRelationalDTO(Long clientContactId, ClientRelationalDTO client, String firstName, String lastName, String fatherName, LocalDate birthDate, String phoneNumber, String mobileNumber, String identityType, String identityCode1, String identityCode2, String identityCode3, String address, String address2) {
        this.clientContactId = clientContactId;
        this.client = client;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.identityType = identityType;
        this.identityCode1 = identityCode1;
        this.identityCode2 = identityCode2;
        this.identityCode3 = identityCode3;
        this.address = address;
        this.address2 = address2;
    }

    public Long getClientContactId() {
        return clientContactId;
    }

    public void setClientContactId(Long clientContactId) {
        this.clientContactId = clientContactId;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public void setClient(ClientRelationalDTO client) {
        this.client = client;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityCode1() {
        return identityCode1;
    }

    public void setIdentityCode1(String identityCode1) {
        this.identityCode1 = identityCode1;
    }

    public String getIdentityCode2() {
        return identityCode2;
    }

    public void setIdentityCode2(String identityCode2) {
        this.identityCode2 = identityCode2;
    }

    public String getIdentityCode3() {
        return identityCode3;
    }

    public void setIdentityCode3(String identityCode3) {
        this.identityCode3 = identityCode3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}