package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.validate.ServerValidate;
import ir.parto.crm.modules.server.model.service.ServerService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerRestController implements RestControllerInterface {
    private ServerService serverService;
    private ServerValidate serverValidate;

    @Autowired
    public ServerRestController(ServerService serverService, ServerValidate serverValidate) {
        this.serverService = serverService;
        this.serverValidate = serverValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll() {
        return "";
    }

}
