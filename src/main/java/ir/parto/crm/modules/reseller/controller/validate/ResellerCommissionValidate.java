package ir.parto.crm.modules.reseller.controller.validate;

import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.modules.reseller.model.entity.ResellerCommission;
import ir.parto.crm.modules.reseller.model.service.ResellerCommissionService;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ResellerCommissionValidate implements ValidateInterface<ResellerCommission> {
    private ResellerCommissionService resellerCommissionService;
    private ResellerService resellerService;
    private ProductGroupService productGroupService;

    @Autowired
    public ResellerCommissionValidate(ResellerCommissionService resellerCommissionService, ResellerService resellerService, ProductGroupService productGroupService) {
        this.resellerCommissionService = resellerCommissionService;
        this.resellerService = resellerService;
        this.productGroupService = productGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(ResellerCommission resellerCommission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerCommission == null) {
            errorList.add("ResellerCommission object is nul");
        } else {
            if (resellerCommission.getReseller() == null) {
                errorList.add("Reseller object is nul");
            } else {
                if (resellerCommission.getProductGroup() == null) {
                    errorList.add("ProductGroup object is nul");
                } else {
                    if (!this.productGroupService.existsById(resellerCommission.getProductGroup().getProductGroupId())) {
                        errorList.add("ProductGroup Id not defined");
                    }

                    if (!this.resellerService.existsById(resellerCommission.getReseller().getResellerId())) {
                        errorList.add("Reseller Id not defined");
                    }

                    if (resellerCommission.getPercentage() == null || resellerCommission.getPercentage() < 0) {
                        errorList.add("Percentage is required");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(ResellerCommission resellerCommission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerCommission == null) {
            errorList.add("ResellerCommission object is nul");
        } else {
            if (resellerCommission.getReseller() == null) {
                errorList.add("Reseller object is nul");
            } else {
                if (resellerCommission.getProductGroup() == null) {
                    errorList.add("ProductGroup object is nul");
                } else {
                    if (!this.resellerCommissionService.existsById(resellerCommission.getResellerCommissionId())) {
                        errorList.add("ResellerCommission Id not defined");
                    }

                    if (!this.productGroupService.existsById(resellerCommission.getProductGroup().getProductGroupId())) {
                        errorList.add("ProductGroup Id not defined");
                    }

                    if (!this.resellerService.existsById(resellerCommission.getReseller().getResellerId())) {
                        errorList.add("Reseller Id not defined");
                    }

                    if (resellerCommission.getPercentage() == null || resellerCommission.getPercentage() < 0) {
                        errorList.add("Percentage is required");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(ResellerCommission resellerCommission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerCommission == null) {
            errorList.add("ResellerCommission object is nul");
        } else {
            if (!this.resellerCommissionService.existsById(resellerCommission.getResellerCommissionId())) {
                errorList.add("ResellerCommission Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(ResellerCommission resellerCommission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerCommission == null) {
            errorList.add("ResellerCommission object is nul");
        } else {
            if (!this.resellerCommissionService.existsById(resellerCommission.getResellerCommissionId())) {
                errorList.add("ResellerCommission Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(ResellerCommission resellerCommission) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerCommission == null) {
            errorList.add("ResellerCommission object is nul");
        } else {
            if (!this.resellerCommissionService.existsById(resellerCommission.getResellerCommissionId())) {
                errorList.add("ResellerCommission Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
