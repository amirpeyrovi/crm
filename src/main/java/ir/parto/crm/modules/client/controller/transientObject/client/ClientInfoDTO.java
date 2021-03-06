package ir.parto.crm.modules.client.controller.transientObject.client;

import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ClientInfoDTO implements Serializable {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String fatherName;
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
    private List<ClientExternalCode> externalCodes;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Integer isDeleted;

    public ClientInfoDTO() {
    }

    public ClientInfoDTO(Long clientId) {
        this.clientId = clientId;
    }

    public ClientInfoDTO(Long clientId, String firstName, String lastName, String fatherName, LocalDate birthDate, String phoneNumber, String mobileNumber, Long identityType, String identityCode1, String identityCode2, String identityCode3, String address, String address2, String emailAddress, List<ClientExternalCode> externalCodes, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
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
        this.emailAddress = emailAddress;
        this.externalCodes = externalCodes;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
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

    public List<ClientExternalCode> getExternalCodes() {
        return externalCodes;
    }

    public void setExternalCodes(List<ClientExternalCode> externalCodes) {
        this.externalCodes = externalCodes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ClientRelationalDTO convert2RelationalObject() {
        ClientRelationalDTO dto = new ClientRelationalDTO();
        if(this.clientId != null) dto.setClientId(this.clientId);
        if(this.firstName != null) dto.setFirstName(this.firstName);
        if(this.lastName != null) dto.setLastName(this.lastName);
        if(this.identityType != null) dto.setIdentityType(this.identityType);
        if(this.identityCode1 != null) dto.setIdentityCode1(this.identityCode1);
        return dto;
    }

    public ClientRelationalDTO convert2Object() {
        ClientRelationalDTO dto = new ClientRelationalDTO();
        if(this.clientId != null) dto.setClientId(this.clientId);
        if(this.firstName != null) dto.setFirstName(this.firstName);
        if(this.lastName != null) dto.setLastName(this.lastName);
        if(this.identityType != null) dto.setIdentityType(this.identityType);
        if(this.identityCode1 != null) dto.setIdentityCode1(this.identityCode1);
        return dto;
    }
}
