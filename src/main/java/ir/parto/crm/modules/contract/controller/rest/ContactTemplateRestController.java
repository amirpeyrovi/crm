package ir.parto.crm.modules.contract.controller.rest;

import ir.parto.crm.modules.contract.controller.validate.ContractTemplateValidate;
import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.modules.contract.model.service.ContractGroupService;
import ir.parto.crm.modules.contract.model.service.ContractTemplateService;
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
@RequestMapping("/v1/contract/contractTemplate")
public class ContactTemplateRestController implements RestControllerInterface {
    private ContractTemplateValidate contractTemplateValidate;
    private ContractTemplateService contractTemplateService;
    private ContractGroupService contractGroupService;

    @Autowired
    public ContactTemplateRestController(ContractGroupService contractGroupService ,ContractTemplateValidate contractTemplateValidate, ContractTemplateService contractTemplateService) {
        this.contractTemplateValidate = contractTemplateValidate;
        this.contractTemplateService = contractTemplateService;
        this.contractGroupService = contractGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ContractTemplate")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractTemplate - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.contractTemplateValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ContractTemplate> contractTemplatePage = this.contractTemplateService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "ContractTemplate", sortProperty, sortOrder));
            return new ApiResponse("Success", contractTemplatePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ContractTemplate contractTemplate) {
        if (CheckPermission.getInstance().check("admin_add", "ContractTemplate")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractTemplate - admin_add - access denied!"))
                    .getFaultResponse();
        }

        contractTemplate.setContractTemplateId(null);

        ValidateObject validateObject = this.contractTemplateValidate.validateAddNewItem(contractTemplate);
        if (validateObject.getResult().equals("success")) {
            contractTemplate.setContractGroup(this.contractGroupService.findOne(contractTemplate.getContractGroup()));
            return new ApiResponse("Success", Arrays.asList(this.contractTemplateService.addNewItem(contractTemplate)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ContractTemplate contractTemplate) {
        if (CheckPermission.getInstance().check("admin_update", "ContractTemplate")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractTemplate - admin_update - access denied!"))
                    .getFaultResponse();
        }

        contractTemplate.setContractTemplateId(id);

        ValidateObject validateObject = this.contractTemplateValidate.validateUpdateItem(contractTemplate);
        if (validateObject.getResult().equals("success")) {
            try {
                contractTemplate.setContractGroup(this.contractGroupService.findOne(contractTemplate.getContractGroup()));
                return new ApiResponse("Success", Arrays.asList(this.contractTemplateService.updateItem(contractTemplate)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ContractTemplate")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractTemplate - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setContractTemplateId(id);
        ValidateObject validateObject = this.contractTemplateValidate.deleteItem(contractTemplate);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractTemplateService.deleteItem(contractTemplate)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ContractTemplate")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractTemplate - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setContractTemplateId(id);
        ValidateObject validateObject = this.contractTemplateValidate.findOne(contractTemplate);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractTemplateService.findOne(contractTemplate)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }




}
