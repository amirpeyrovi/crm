package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.validate.ServerParameterValidate;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerParameterRestController implements RestControllerInterface {
    private ServerParameterService serverParameterService;
    private ServerParameterValidate serverParameterValidate;

    @Autowired
    public ServerParameterRestController(ServerParameterService serverParameterService, ServerParameterValidate serverParameterValidate) {
        this.serverParameterService = serverParameterService;
        this.serverParameterValidate = serverParameterValidate;
    }
}
