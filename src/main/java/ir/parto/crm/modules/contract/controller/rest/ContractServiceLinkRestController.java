package ir.parto.crm.modules.contract.controller.rest;

import ir.parto.crm.modules.contract.controller.validate.ContractServiceLinkValidate;
import ir.parto.crm.modules.contract.model.entity.ContractServiceLink;
import ir.parto.crm.modules.contract.model.service.ContractServiceLinkService;
import ir.parto.crm.modules.contract.model.service.ContractTemplateService;
import ir.parto.crm.modules.service.model.service.ServiceAddonService;
import ir.parto.crm.modules.service.model.service.ServiceService;
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
@RequestMapping("/v1/contract/contractServiceLink")
public class ContractServiceLinkRestController implements RestControllerInterface {
    private ContractServiceLinkService contractServiceLinkService;
    private ServiceService serviceService;
    private ServiceAddonService serviceAddonService;
    private ContractTemplateService contractTemplateService;
    private ContractServiceLinkValidate contractServiceLinkValidate;

    @Autowired
    public ContractServiceLinkRestController(ServiceService serviceService, ServiceAddonService serviceAddonService,
                                             ContractTemplateService contractTemplateService,ContractServiceLinkService contractServiceLinkService, ContractServiceLinkValidate contractServiceLinkValidate) {
        this.serviceService = serviceService;
        this.serviceAddonService = serviceAddonService;
        this.contractTemplateService = contractTemplateService;
        this.contractServiceLinkService = contractServiceLinkService;
        this.contractServiceLinkValidate = contractServiceLinkValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ContractServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.contractServiceLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ContractServiceLink> contractServiceLinkPage = this.contractServiceLinkService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "ContractServiceLink", sortProperty, sortOrder));
            return new ApiResponse("Success", contractServiceLinkPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ContractServiceLink contractServiceLink) {
        if (CheckPermission.getInstance().check("admin_add", "ContractServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractServiceLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        contractServiceLink.setContractServiceLinkId(null);

        ValidateObject validateObject = this.contractServiceLinkValidate.validateAddNewItem(contractServiceLink);
        if (validateObject.getResult().equals("success")) {
            contractServiceLink.setContractTemplate(this.contractTemplateService.findOne(
                    contractServiceLink.getContractTemplate()));
            contractServiceLink.setService(this.serviceService.findOne(contractServiceLink.getService()));
            contractServiceLink.setServiceAddon(this.serviceAddonService.findOne(contractServiceLink.getServiceAddon()));
            return new ApiResponse("Success", Arrays.asList(this.contractServiceLinkService.addNewItem(contractServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ContractServiceLink contractServiceLink) {
        if (CheckPermission.getInstance().check("admin_update", "ContractServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractServiceLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        contractServiceLink.setContractServiceLinkId(id);

        ValidateObject validateObject = this.contractServiceLinkValidate.validateUpdateItem(contractServiceLink);
        if (validateObject.getResult().equals("success")) {
            try {
                contractServiceLink.setContractTemplate(this.contractTemplateService.findOne(
                        contractServiceLink.getContractTemplate()));
                contractServiceLink.setService(this.serviceService.findOne(contractServiceLink.getService()));
                return new ApiResponse("Success", Arrays.asList(this.contractServiceLinkService.updateItem(contractServiceLink)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ContractServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractServiceLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ContractServiceLink contractServiceLink = new ContractServiceLink();
        contractServiceLink.setContractServiceLinkId(id);
        ValidateObject validateObject = this.contractServiceLinkValidate.deleteItem(contractServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractServiceLinkService.deleteItem(contractServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ContractServiceLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ContractServiceLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ContractServiceLink contractServiceLink = new ContractServiceLink();
        contractServiceLink.setContractServiceLinkId(id);
        ValidateObject validateObject = this.contractServiceLinkValidate.findOne(contractServiceLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.contractServiceLinkService.findOne(contractServiceLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }



}
