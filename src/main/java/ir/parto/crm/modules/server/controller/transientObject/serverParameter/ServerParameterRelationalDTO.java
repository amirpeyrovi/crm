package ir.parto.crm.modules.server.controller.transientObject.serverParameter;

import ir.parto.crm.modules.server.controller.transientObject.serverGroup.ServerGroupRelationalDTO;

public class ServerParameterRelationalDTO {
    private Long serverParameterId;
    private ServerGroupRelationalDTO serverGroup;
    private String title;
    private String description;
    private String type;
    private String options;

    public ServerParameterRelationalDTO() {
    }

    public ServerParameterRelationalDTO(Long serverParameterId, ServerGroupRelationalDTO serverGroup, String title, String description, String type, String options) {
        this.serverParameterId = serverParameterId;
        this.serverGroup = serverGroup;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
    }

    public Long getServerParameterId() {
        return serverParameterId;
    }

    public void setServerParameterId(Long serverParameterId) {
        this.serverParameterId = serverParameterId;
    }

    public ServerGroupRelationalDTO getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroupRelationalDTO serverGroup) {
        this.serverGroup = serverGroup;
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
}
