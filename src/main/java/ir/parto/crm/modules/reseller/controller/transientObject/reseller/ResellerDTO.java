package ir.parto.crm.modules.reseller.controller.transientObject.reseller;

import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminRelationalDTO;

public class ResellerDTO {
    private Long resellerId;
    private AdminRelationalDTO admin;

    public ResellerDTO() {
    }

    public ResellerDTO(Long resellerId, AdminRelationalDTO admin) {
        this.resellerId = resellerId;
        this.admin = admin;
    }

    public Long getResellerId() {
        return resellerId;
    }

    public void setResellerId(Long resellerId) {
        this.resellerId = resellerId;
    }

    public AdminRelationalDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminRelationalDTO admin) {
        this.admin = admin;
    }
}
