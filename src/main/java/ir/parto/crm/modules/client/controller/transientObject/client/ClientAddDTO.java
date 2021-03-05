package ir.parto.crm.modules.client.controller.transientObject.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ClientAddDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String fatherName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthDate;
    private String phoneNumber;
    private String mobileNumber;
    private Long identityType;
    private String identityCode1;
    private String identityCode2;
    private String identityCode3;
    private String address;
    private String address2;
    private String emailAddress;

    public ClientAddDTO() {
    }

    public ClientAddDTO(String firstName, String lastName, String fatherName, LocalDate birthDate, String phoneNumber, String mobileNumber, Long identityType, String identityCode1, String identityCode2, String identityCode3, String address, String address2, String emailAddress) {
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
        this.emailAddress = emailAddress;
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

    public Long getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Long identityType) {
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Client convert2Object() {
        Client dto = new Client();
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
        if (this.emailAddress != null) dto.setEmailAddress(this.emailAddress);
        return dto;
    }
}