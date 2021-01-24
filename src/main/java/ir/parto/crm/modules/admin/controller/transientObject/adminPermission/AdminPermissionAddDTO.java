package ir.parto.crm.modules.admin.controller.transientObject.adminPermission;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;

import java.io.Serializable;

public class AdminPermissionAddDTO implements Serializable {
    private String title;
    private String showName;

    public AdminPermissionAddDTO() {
    }

    public AdminPermissionAddDTO(String title, String showName) {
        this.title = title;
        this.showName = showName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public AdminPermission convert2Object(){
        AdminPermission adminPermission = new AdminPermission();
        if(this.title != null) adminPermission.setTitle(this.title);
        if(this.showName != null) adminPermission.setShowName(this.showName);
        return adminPermission;
    }
}
