package ir.parto.crm.modules.server.controller.transientObject.serverParameter;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerParameter;

public class ServerParameterEditDTO {
    private Long serverGroupId;
    private String title;
    private String description;
    private String type;
    private String options;

    public ServerParameterEditDTO() {
    }

    public ServerParameterEditDTO(Long serverGroupId, String title, String description, String type, String options) {
        this.serverGroupId = serverGroupId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
    }

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public ServerParameter convert2Object() {
        ServerParameter obj = new ServerParameter();
        if (this.serverGroupId != null) obj.setServerGroup(new ServerGroup(this.serverGroupId));
        if (this.title != null) obj.setTitle(this.title);
        if (this.description != null) obj.setDescription(this.description);
        if (this.type != null) obj.setType(this.type);
        if (this.options != null) obj.setOptions(this.options);
        return obj;
    }
}
