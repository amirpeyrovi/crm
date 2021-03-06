package ir.parto.crm.modules.promotion.controller.rest;

import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeAddDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeEditDTO;
import ir.parto.crm.modules.promotion.controller.validate.PromotionCodeValidate;
import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ProductAnnotation;
import ir.parto.crm.utils.annotations.PromotionAnnotation;
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
@PromotionAnnotation
@RequestMapping("/v1/promotion/promotionCode")
public class PromotionCodeRestController implements RestControllerInterface {
    private PromotionCodeValidate promotionCodeValidate;
    private PromotionCodeService promotionCodeService;
    private ClientService clientService;

    @Autowired
    public PromotionCodeRestController(PromotionCodeValidate promotionCodeValidate, PromotionCodeService promotionCodeService, ClientService clientService) {
        this.promotionCodeValidate = promotionCodeValidate;
        this.promotionCodeService = promotionCodeService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PromotionCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCode - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.promotionCodeValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PromotionCode> productPage = this.promotionCodeService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PromotionCode", sortProperty, sortOrder));
            List<PromotionCodeDTO> returnDTO = new ArrayList<>();
            for (PromotionCode promotionCode : productPage.getContent()) {
                returnDTO.add(promotionCode.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PromotionCodeAddDTO promotionCodeDTO) {
        if (CheckPermission.getInstance().check("admin_add", "PromotionCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCode - admin_add - access denied!"))
                    .getFaultResponse();
        }
        PromotionCode promotionCode = promotionCodeDTO.convert2Object();
        promotionCode.setPromotionCodeId(null);

        ValidateObject validateObject = this.promotionCodeValidate.validateAddNewItem(promotionCode);
        if (validateObject.getResult().equals("success")) {
            if (promotionCode.getClient() != null) {
                promotionCode.setClient(this.clientService.findOne(promotionCode.getClient()));
            }
            return new ApiResponse("Success", Arrays.asList(this.promotionCodeService.addNewItem(promotionCode).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody PromotionCodeEditDTO promotionCodeDTO) {
        if (CheckPermission.getInstance().check("admin_update", "PromotionCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCode - admin_update - access denied!"))
                    .getFaultResponse();
        }
        PromotionCode promotionCode = promotionCodeDTO.convert2Object();
        promotionCode.setPromotionCodeId(Long.valueOf(id));

        ValidateObject validateObject = this.promotionCodeValidate.validateUpdateItem(promotionCode);
        if (validateObject.getResult().equals("success")) {
            try {
                if (promotionCode.getClient() != null) {
                    promotionCode.setClient(this.clientService.findOne(promotionCode.getClient()));
                }
                return new ApiResponse("Success", Arrays.asList(this.promotionCodeService.updateItem(promotionCode)))
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
        if (CheckPermission.getInstance().check("admin_delete", "PromotionCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCode - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setPromotionCodeId(Long.valueOf(id));
        ValidateObject validateObject = this.promotionCodeValidate.deleteItem(promotionCode);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.promotionCodeService.deleteItem(promotionCode).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, Arrays.asList("" +
                            "Integrity constraint violated - child record")).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, Arrays.asList("An error occurred during the Delete"))
                            .getFaultResponse();
                }
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "PromotionCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCode - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setPromotionCodeId(Long.valueOf(id));
        ValidateObject validateObject = this.promotionCodeValidate.findOne(promotionCode);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.promotionCodeService.findOne(promotionCode).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
