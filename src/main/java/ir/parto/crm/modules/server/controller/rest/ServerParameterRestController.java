package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.validate.ServerParameterValidate;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = {"/register" , "/register/"} , method = RequestMethod.GET)
    public String registerAdminForm(Model model){
        return "admin/register";
    }

}
