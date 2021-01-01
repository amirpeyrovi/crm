package ir.parto.crm.modules.server.controller.transientObject.serverGroup;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerVendor;

public class ServerGroupAddDTO {

    private String title;
    private Long serverVendorId;

    public ServerGroupAddDTO() {
    }

    public ServerGroupAddDTO(String title, Long serverVendorId) {
        this.title = title;
        this.serverVendorId = serverVendorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getServerVendorId() {
        return serverVendorId;
    }

    public void setServerVendorId(Long serverVendorId) {
        this.serverVendorId = serverVendorId;
    }

    public ServerGroup convert2Object() {
        ServerGroup serverGroup = new ServerGroup();
        if (this.title != null) serverGroup.setTitle(this.title);
        if (this.serverVendorId != null) serverGroup.setServerVendor(new ServerVendor(this.serverVendorId));
        return serverGroup;
    }
}
