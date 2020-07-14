package ir.parto.crm.modules.admin.controller.rest;
import ir.parto.crm.modules.admin.controller.validate.AdminPermissionValidate;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.AdminAnnotation;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@AdminAnnotation
@RequestMapping("/v1/admin/admin")
public class AdminPermissionRestController {
    private AdminPermissionService adminPermissionService;
    private AdminPermissionValidate adminPermissionValidate;
    private CheckPermission checkPermission;

    @Autowired
    public AdminPermissionRestController(AdminPermissionService adminPermissionService, AdminPermissionValidate adminPermissionValidate, CheckPermission checkPermission) {
        this.adminPermissionService = adminPermissionService;
        this.adminPermissionValidate = adminPermissionValidate;
        this.checkPermission = checkPermission;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminPermission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminPermissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<AdminPermission> adminPermissionPage = this.adminPermissionService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Admin", sortProperty, sortOrder));
            return new ApiResponse("Success", adminPermissionPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody AdminPermission adminPermission) {
        if (CheckPermission.getInstance().check("admin_add", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminPermission - admin_add - access denied!"))
                    .getFaultResponse();
        }
        adminPermission.setPermissionId(null);
        ValidateObject validateObject = this.adminPermissionValidate.validateAddNewItem(adminPermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminPermissionService.addNewItem(adminPermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
