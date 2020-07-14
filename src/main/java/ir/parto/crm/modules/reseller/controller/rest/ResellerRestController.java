package ir.parto.crm.modules.reseller.controller.rest;

import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.modules.reseller.controller.validate.ResellerValidate;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ResellerAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@ResellerAnnotation
@RequestMapping("/v1/reseller/reseller")
public class ResellerRestController implements RestControllerInterface {
    private ResellerValidate resellerValidate;
    private ResellerService resellerService;
    private AdminService adminService;

    @Autowired
    public ResellerRestController(ResellerValidate resellerValidate, ResellerService resellerService, AdminService adminService) {
        this.resellerValidate = resellerValidate;
        this.resellerService = resellerService;
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Reseller")) {
            return new ApiResponse("Error", 101, Arrays.asList("Reseller - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Reseller> productPage = this.resellerService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Reseller", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Reseller reseller) {
        if (CheckPermission.getInstance().check("admin_add", "Reseller")) {
            return new ApiResponse("Error", 101, Arrays.asList("Reseller - admin_add - access denied!"))
                    .getFaultResponse();
        }

        reseller.setResellerId(null);

        ValidateObject validateObject = this.resellerValidate.validateAddNewItem(reseller);
        if (validateObject.getResult().equals("success")) {
            reseller.setAdmin(this.adminService.findOne(reseller.getAdmin()));
            return new ApiResponse("Success", Arrays.asList(this.resellerService.addNewItem(reseller)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Reseller reseller) {
        if (CheckPermission.getInstance().check("admin_update", "Reseller")) {
            return new ApiResponse("Error", 101, Arrays.asList("Reseller - admin_update - access denied!"))
                    .getFaultResponse();
        }

        return new ApiResponse("Error", 100, Arrays.asList("request not valid!"))
                .getFaultResponse();

//        reseller.setResellerId(id);
//
//        ValidateObject validateObject = this.resellerValidate.validateUpdateItem(reseller);
//        if (validateObject.getResult().equals("success")) {
//            try {
//                reseller.setAdmin(this.adminService.findOne(reseller.getAdmin()));
//                return new ApiResponse("Success", Arrays.asList(this.resellerService.updateItem(reseller)))
//                        .getSuccessResponse();
//            } catch (InvocationTargetException e) {
//                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
//                        .getFaultResponse();
//            } catch (IllegalAccessException e) {
//                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
//                        .getFaultResponse();
//            }
//        } else {
//            return new ApiResponse("Error", 102, validateObject.getMessages())
//                    .getFaultResponse();
//        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "Reseller")) {
            return new ApiResponse("Error", 101, Arrays.asList("Reseller - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Reseller reseller = new Reseller();
        reseller.setResellerId(id);
        ValidateObject validateObject = this.resellerValidate.deleteItem(reseller);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.resellerService.deleteItem(reseller)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Reseller")) {
            return new ApiResponse("Error", 101, Arrays.asList("Reseller - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Reseller reseller = new Reseller();
        reseller.setResellerId(id);
        ValidateObject validateObject = this.resellerValidate.findOne(reseller);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.resellerService.findOne(reseller)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
