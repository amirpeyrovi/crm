package ir.parto.crm.modules.reseller.controller.transientObject.reseller;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.reseller.model.entity.Reseller;

public class ResellerAddDTO {
    private Long adminId;

    public ResellerAddDTO() {
    }

    public ResellerAddDTO(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Reseller convert2Object() {
        Reseller obj = new Reseller();
        if (this.adminId != null) obj.setAdmin(new Admin(this.adminId));
        return obj;
    }
}
