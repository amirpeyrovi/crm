package ir.parto.crm.modules.client.controller.transientObject.clientContact;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientContact;

import java.time.LocalDate;

public class ClientContactAddDTO {
    private Long clientId;
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

    public ClientContactAddDTO() {
    }

    public ClientContactAddDTO(Long clientId, String firstName, String lastName, String fatherName, LocalDate birthDate, String phoneNumber, String mobileNumber, String identityType, String identityCode1, String identityCode2, String identityCode3, String address, String address2) {
        this.clientId = clientId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public ClientContact convert2Object() {
        ClientContact dto = new ClientContact();
        if (this.clientId != null) dto.setClient(new Client(this.clientId));
        if (this.firstName != null) dto.setFirstName(this.firstName);
        if (this.lastName != null) dto.setLastName(this.lastName);
        if (this.fatherName != null) dto.setFatherName(this.fatherName);
        if (this.birthDate != null) dto.setBirthDate(this.birthDate);
        if (this.phoneNumber != null) dto.setPhoneNumber(this.phoneNumber);
        if (this.mobileNumber != null) dto.setMobileNumber(this.mobileNumber);
        if (this.identityType != null) dto.setIdentityType(this.identityType);
        if (this.identityCode1 != null) dto.setIdentityCode1(this.identityCode1);
        if (this.identityCode2 != null) dto.setIdentityCode2(this.identityCode2);
        if (this.identityCode3 != null) dto.setIdentityCode3(this.identityCode3);
        if (this.address != null) dto.setAddress(this.address);
        if (this.address2 != null) dto.setAddress2(this.address2);
        return dto;
    }
}