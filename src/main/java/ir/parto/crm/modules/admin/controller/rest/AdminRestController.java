package ir.parto.crm.modules.admin.controller.rest;


import ir.parto.crm.modules.admin.controller.validate.AdminValidate;
import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminRestController {
    private CheckPermission checkPermission;
    private AdminService adminService;
    private AdminValidate adminValidate;

    @Autowired
    public AdminRestController(CheckPermission checkPermission, AdminService adminService, AdminValidate adminValidate) {
        this.checkPermission = checkPermission;
        this.adminService = adminService;
        this.adminValidate = adminValidate;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Object findAllClient(Pageable pageable){
        ValidateObject validateObject = this.adminValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<Admin> adminList = this.adminService.findAllItem(pageable0);
        return new ApiResponse("success",adminList).getSuccessResponse();
    }

}
