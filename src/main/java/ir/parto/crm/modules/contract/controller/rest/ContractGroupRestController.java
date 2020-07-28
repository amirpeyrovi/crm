package ir.parto.crm.modules.contract.controller.rest;

import ir.parto.crm.modules.contract.controller.validate.ContractGroupValidate;
import ir.parto.crm.modules.contract.model.entity.ContractGroup;
import ir.parto.crm.modules.contract.model.service.ContractGroupService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ContractAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@ContractAnnotation
@RequestMapping("/v1/contract/contractGroup")
public class ContractGroupRestController implements RestControllerInterface {
    private ContractGroupService contractGroupService;
    private ContractGroupValidate contractGroupValidate;

    @Autowired
    public ContractGroupRestController(ContractGroupService contractGroupService, ContractGroupValidate contractGroupValidate) {
        this.contractGroupService = contractGroupService;
        this.contractGroupValidate = contractGroupValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ContractGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.contractGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ContractGroup> contractGroupPage = this.contractGroupService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "ContractGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", contractGroupPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ContractGroup contractGroup) {
        if (CheckPermission.getInstance().check("admin_add", "ContractGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        contractGroup.setContractGroupId(null);

        ValidateObject validateObject = this.contractGroupValidate.validateAddNewItem(contractGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractGroupService.addNewItem(contractGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ContractGroup contractGroup) {
        if (CheckPermission.getInstance().check("admin_update", "ContractGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        contractGroup.setContractGroupId(id);

        ValidateObject validateObject = this.contractGroupValidate.validateUpdateItem(contractGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.contractGroupService.updateItem(contractGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ContractGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ContractGroup contractGroup = new ContractGroup();
        contractGroup.setContractGroupId(id);
        ValidateObject validateObject = this.contractGroupValidate.deleteItem(contractGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractGroupService.deleteItem(contractGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ContractGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ContractGroup contractGroup = new ContractGroup();
        contractGroup.setContractGroupId(id);
        ValidateObject validateObject = this.contractGroupValidate.findOne(contractGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractGroupService.findOne(contractGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
