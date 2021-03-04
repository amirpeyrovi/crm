package ir.parto.crm.modules.reseller.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductGroupValidate;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission.ResellerCommissionAddDTO;
import ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission.ResellerCommissionDTO;
import ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission.ResellerCommissionEditDTO;
import ir.parto.crm.modules.reseller.controller.validate.ResellerCommissionValidate;
import ir.parto.crm.modules.reseller.controller.validate.ResellerValidate;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.entity.ResellerCommission;
import ir.parto.crm.modules.reseller.model.service.ResellerCommissionService;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ResellerAnnotation;
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
@ResellerAnnotation
@RequestMapping("/v1/reseller/resellerCommission")
public class ResellerCommissionRestController implements RestControllerInterface {
    private ResellerCommissionValidate resellerCommissionValidate;
    private ProductGroupValidate productGroupValidate;
    private ResellerValidate resellerValidate;
    private ResellerCommissionService resellerCommissionService;
    private ProductGroupService productGroupService;
    private ResellerService resellerService;

    @Autowired
    public ResellerCommissionRestController(ResellerCommissionValidate resellerCommissionValidate, ProductGroupValidate productGroupValidate, ResellerValidate resellerValidate, ResellerCommissionService resellerCommissionService, ProductGroupService productGroupService, ResellerService resellerService) {
        this.resellerCommissionValidate = resellerCommissionValidate;
        this.productGroupValidate = productGroupValidate;
        this.resellerValidate = resellerValidate;
        this.resellerCommissionService = resellerCommissionService;
        this.productGroupService = productGroupService;
        this.resellerService = resellerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerCommissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ResellerCommission> resellerCommissionPage = this.resellerCommissionService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ResellerCommission", sortProperty, sortOrder));
            List<ResellerCommissionDTO> returnDTO = new ArrayList();
            for (ResellerCommission resellerCommission : resellerCommissionPage.getContent()) {
                returnDTO.add(resellerCommission.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(resellerCommissionPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/reseller/{id}", method = RequestMethod.GET)
    public Object findAllByReseller(@PathVariable("id") Long id,
                                    @RequestParam(required = false, defaultValue = "0") String page,
                                    @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                    @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerCommissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Reseller reseller = new Reseller();
            reseller.setResellerId(id);
            ValidateObject validateObjectReseller = this.resellerValidate.findOne(reseller);
            if (validateObjectReseller.getResult().equals("success")) {
                Reseller resellerExist = this.resellerService.findOne(reseller);
                Page<ResellerCommission> resellerCommissionPage = this.resellerCommissionService.findAllItemByReseller(resellerExist, PageableRequest.getInstance().createPageRequest(page, "ResellerCommission", sortProperty, sortOrder));
                List<ResellerCommissionDTO> returnDTO = new ArrayList();
                for (ResellerCommission resellerCommission : resellerCommissionPage.getContent()) {
                    returnDTO.add(resellerCommission.convert2Object());
                }
                return new ApiResponse("Success", PageHelper.getInstance().createResponse(resellerCommissionPage, returnDTO))
                        .getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectReseller.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/reseller/{id}/{pgId}", method = RequestMethod.GET)
    public Object findAllByResellerAndProductGroup(@PathVariable("id") String id, @PathVariable("pgId") String pgId,
                                                   @RequestParam(required = false, defaultValue = "0") String page,
                                                   @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                                   @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.resellerCommissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Reseller reseller = new Reseller();
            reseller.setResellerId(Long.valueOf(id));
            ValidateObject validateObjectReseller = this.resellerValidate.findOne(reseller);
            if (validateObjectReseller.getResult().equals("success")) {
                ProductGroup productGroup = new ProductGroup();
                productGroup.setProductGroupId(Long.valueOf(pgId));
                ValidateObject validateObjectProductGroup = this.productGroupValidate.findOne(productGroup);
                if (validateObjectProductGroup.getResult().equals("success")) {
                    Reseller resellerExist = this.resellerService.findOne(reseller);
                    ProductGroup productGroupExist = this.productGroupService.findOne(productGroup);
                    Page<ResellerCommission> resellerCommissionPage = this.resellerCommissionService.
                            findAllItemByResellerAndProductGroup(resellerExist, productGroupExist, PageableRequest.getInstance().createPageRequest(page, "ResellerCommission", sortProperty, sortOrder));
                    List<ResellerCommissionDTO> returnDTO = new ArrayList();
                    for (ResellerCommission resellerCommission : resellerCommissionPage.getContent()) {
                        returnDTO.add(resellerCommission.convert2Object());
                    }
                    return new ApiResponse("Success", PageHelper.getInstance().createResponse(resellerCommissionPage, returnDTO))
                            .getSuccessResponse();
                } else {
                    return new ApiResponse("Error", 102, validateObjectProductGroup.getMessages())
                            .getFaultResponse();
                }
            } else {
                return new ApiResponse("Error", 102, validateObjectReseller.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ResellerCommissionAddDTO resellerCommissionDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ResellerCommission resellerCommission = resellerCommissionDTO.convert2Object();
        resellerCommission.setResellerCommissionId(null);

        ValidateObject validateObject = this.resellerCommissionValidate.validateAddNewItem(resellerCommission);
        if (validateObject.getResult().equals("success")) {
            resellerCommission.setReseller(this.resellerService.findOne(resellerCommission.getReseller()));
            resellerCommission.setProductGroup(this.productGroupService.findOne(resellerCommission.getProductGroup()));
            return new ApiResponse("Success", Arrays.asList(this.resellerCommissionService.addNewItem(resellerCommission).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ResellerCommissionEditDTO resellerCommissionDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ResellerCommission resellerCommission = resellerCommissionDTO.convert2Object();
        resellerCommission.setResellerCommissionId(Long.valueOf(id));

        ValidateObject validateObject = this.resellerCommissionValidate.validateUpdateItem(resellerCommission);
        if (validateObject.getResult().equals("success")) {
            try {
                resellerCommission.setReseller(this.resellerService.findOne(resellerCommission.getReseller()));
                resellerCommission.setProductGroup(this.productGroupService.findOne(resellerCommission.getProductGroup()));
                return new ApiResponse("Success", Arrays.asList(
                        this.resellerCommissionService.updateItem(resellerCommission).convert2Object()))
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
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "ResellerCommission")) {
            return new ApiResponse("Error", 101, Arrays.asList("ResellerCommission - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ResellerCommission resellerCommission = new ResellerCommission();
        resellerCommission.setResellerCommissionId(Long.valueOf(id));
        ValidateObject validateObject = this.resellerCommissionValidate.deleteItem(resellerCommission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.resellerCommissionService.deleteItem(resellerCommission).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ResellerCommission resellerCommission = new ResellerCommission();
        resellerCommission.setResellerCommissionId(Long.valueOf(id));
        ValidateObject validateObject = this.resellerCommissionValidate.findOne(resellerCommission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.resellerCommissionService.findOne(resellerCommission).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
