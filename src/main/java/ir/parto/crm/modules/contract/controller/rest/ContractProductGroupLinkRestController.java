package ir.parto.crm.modules.contract.controller.rest;

import ir.parto.crm.modules.contract.controller.validate.ContractProductGroupLinkValidate;
import ir.parto.crm.modules.contract.model.entity.ContractProductGroupLink;
import ir.parto.crm.modules.contract.model.service.ContractProductGroupLinkService;
import ir.parto.crm.modules.contract.model.service.ContractTemplateService;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
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
@RequestMapping("/v1/contract/contractProductGroupLink")
public class ContractProductGroupLinkRestController  implements RestControllerInterface {
    private ContractTemplateService contractTemplateService;
    private ProductGroupService productGroupService;
    private ContractProductGroupLinkService contractProductGroupLinkService;
    private ContractProductGroupLinkValidate contractProductGroupLinkValidate;

    @Autowired
    public ContractProductGroupLinkRestController(ProductGroupService productGroupService ,
                                                  ContractTemplateService contractTemplateService,ContractProductGroupLinkService contractProductGroupLinkService, ContractProductGroupLinkValidate contractProductGroupLinkValidate) {
        this.productGroupService = productGroupService;
        this.contractProductGroupLinkService = contractProductGroupLinkService;
        this.contractProductGroupLinkValidate = contractProductGroupLinkValidate;
        this.contractTemplateService = contractTemplateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ContractProductGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractProductGroupLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.contractProductGroupLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ContractProductGroupLink> contractProductGroupLinkPage = this.contractProductGroupLinkService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "ContractProductGroupLink", sortProperty, sortOrder));
            return new ApiResponse("Success", contractProductGroupLinkPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ContractProductGroupLink contractProductGroupLink) {
        if (CheckPermission.getInstance().check("admin_add", "ContractProductGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractProductGroupLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        contractProductGroupLink.setContractProductGroupLinkId(null);

        ValidateObject validateObject = this.contractProductGroupLinkValidate.validateAddNewItem(contractProductGroupLink);
        if (validateObject.getResult().equals("success")) {
            contractProductGroupLink.setContractTemplate(this.contractTemplateService.findOne(contractProductGroupLink.getContractTemplate()));
            contractProductGroupLink.setProductGroup(this.productGroupService.findOne(
                    contractProductGroupLink.getProductGroup()));
            return new ApiResponse("Success", Arrays.asList(this.contractProductGroupLinkService.addNewItem(contractProductGroupLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ContractProductGroupLink contractProductGroupLink) {
        if (CheckPermission.getInstance().check("admin_update", "ContractProductGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractProductGroupLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        contractProductGroupLink.setContractProductGroupLinkId(id);

        ValidateObject validateObject = this.contractProductGroupLinkValidate.validateUpdateItem(contractProductGroupLink);
        if (validateObject.getResult().equals("success")) {
            try {
                contractProductGroupLink.setContractTemplate(this.contractTemplateService.findOne(contractProductGroupLink.getContractTemplate()));
                contractProductGroupLink.setProductGroup(this.productGroupService.findOne(
                        contractProductGroupLink.getProductGroup()));
                return new ApiResponse("Success", Arrays.asList(this.contractProductGroupLinkService.updateItem(contractProductGroupLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ContractProductGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractProductGroupLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ContractProductGroupLink contractProductGroupLink = new ContractProductGroupLink();
        contractProductGroupLink.setContractProductGroupLinkId(id);
        ValidateObject validateObject = this.contractProductGroupLinkValidate.deleteItem(contractProductGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractProductGroupLinkService.deleteItem(contractProductGroupLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ContractProductGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractProductGroupLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ContractProductGroupLink contractProductGroupLink = new ContractProductGroupLink();
        contractProductGroupLink.setContractProductGroupLinkId(id);
        ValidateObject validateObject = this.contractProductGroupLinkValidate.findOne(contractProductGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractProductGroupLinkService.findOne(contractProductGroupLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


}
