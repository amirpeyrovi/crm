package ir.parto.crm.modules.server.controller.validate;

import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.service.ServerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ValidationAnnotation
@Component
public class ServerValidate implements ValidateInterface<Server> {
    private ServerService serverService;

    @Autowired
    public ServerValidate(ServerService serverService) {
        this.serverService = serverService;
    }

    @Override
    public ValidateObject validateAddNewItem(Server server) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(Server server) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(Server server) {
        return null;
    }

    @Override
    public ValidateObject findOne(Server server) {
        return null;
    }

    @Override
    public ValidateObject findById(Server server) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
