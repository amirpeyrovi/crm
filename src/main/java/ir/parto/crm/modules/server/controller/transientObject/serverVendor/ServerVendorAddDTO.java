package ir.parto.crm.modules.server.controller.transientObject.serverVendor;

import ir.parto.crm.modules.server.model.entity.ServerVendor;

public class ServerVendorAddDTO {
    private String title;

    public ServerVendorAddDTO() {
    }

    public ServerVendorAddDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ServerVendor convert2Object() {
        ServerVendor serverVendor = new ServerVendor();
        if (this.title != null) serverVendor.setTitle(this.title);
        return serverVendor;
    }
}
