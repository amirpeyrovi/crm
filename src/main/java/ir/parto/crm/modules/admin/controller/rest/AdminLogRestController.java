package ir.parto.crm.modules.admin.controller.rest;
import ir.parto.crm.modules.admin.controller.transientObject.adminLog.AdminLogAddDTO;
import ir.parto.crm.modules.admin.controller.transientObject.adminLog.AdminLogDTO;
import ir.parto.crm.modules.admin.controller.validate.AdminLogValidate;
import ir.parto.crm.modules.admin.controller.validate.AdminValidate;
import ir.parto.crm.modules.admin.model.entity.AdminLog;
import ir.parto.crm.modules.admin.model.service.AdminLogService;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.AdminAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@AdminAnnotation
@RequestMapping(value = "/v1/admin/adminLog", produces = "application/json")
public class AdminLogRestController implements RestControllerInterface {
    private AdminLogService adminLogService;
    private AdminLogValidate adminLogValidate;
    private CheckPermission checkPermission;
    private AdminService adminService;
    private AdminValidate adminValidate;

    @Autowired
    public AdminLogRestController(AdminLogService adminLogService, AdminLogValidate adminLogValidate, CheckPermission checkPermission, AdminService adminService, AdminValidate adminValidate) {
        this.adminLogService = adminLogService;
        this.adminLogValidate = adminLogValidate;
        this.checkPermission = checkPermission;
        this.adminService = adminService;
        this.adminValidate = adminValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "AdminLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminLogValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<AdminLog> findPage = this.adminLogService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "AdminLog", sortProperty, sortOrder));
            List<AdminLogDTO> returnDTO = new ArrayList<>();
            for (AdminLog content : findPage.getContent()) {
                returnDTO.add(content.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(findPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody AdminLogAddDTO adminLogAddDTO) {
        if (CheckPermission.getInstance().check("admin_add", "AdminLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminLog - admin_add - access denied!"))
                    .getFaultResponse();
        }
        AdminLog adminLog = adminLogAddDTO.convert2Object();
        ValidateObject validateObject = this.adminLogValidate.validateAddNewItem(adminLog);
        if (validateObject.getResult().equals("success")) {
            adminLog.setAdmin(this.adminService.findOne(adminLog.getAdmin()));
            return new ApiResponse("Success", Arrays.asList(this.adminLogService.addNewItem(adminLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody AdminLogAddDTO adminLogAddDTO) {
        if (CheckPermission.getInstance().check("admin_update", "AdminLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminLog - admin_update - access denied!"))
                    .getFaultResponse();
        }
        AdminLog adminLog = adminLogAddDTO.convert2Object();
        adminLog.setAdminLogId(id);

        ValidateObject validateObject = this.adminLogValidate.validateUpdateItem(adminLog);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.adminLogService.updateItem(adminLog)))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "AdminLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminLog - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        AdminLog adminLog = new AdminLog();
        adminLog.setAdminLogId(id);
        ValidateObject validateObject = this.adminLogValidate.deleteItem(adminLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminLogService.deleteItem(adminLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "AdminLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        AdminLog adminLog = new AdminLog();
        adminLog.setAdminLogId(id);
        ValidateObject validateObject = this.adminLogValidate.findOne(adminLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminLogService.findOne(adminLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}