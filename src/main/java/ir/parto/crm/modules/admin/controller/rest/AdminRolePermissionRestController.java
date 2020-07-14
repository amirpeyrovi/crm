package ir.parto.crm.modules.admin.controller.rest;


import ir.parto.crm.utils.annotations.AdminAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AdminAnnotation
@RequestMapping("/v1/admin/admin")
public class AdminRolePermissionRestController {
}
