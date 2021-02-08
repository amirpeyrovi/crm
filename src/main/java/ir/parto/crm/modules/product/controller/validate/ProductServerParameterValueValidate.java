package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductServerParameterValueService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductServerParameterValueValidate implements ValidateInterface<ProductServerParameterValue> {
    private ProductServerParameterValueService productServerParameterValueService;
    private ServerParameterService serverParameterService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductServerParameterValueValidate(ProductServerParameterValueService productServerParameterValueService, ServerParameterService serverParameterService, ProductAddonService productAddonService, ProductService productService) {
        this.productServerParameterValueService = productServerParameterValueService;
        this.serverParameterService = serverParameterService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductServerParameterValue productServerParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productServerParameterValue == null) {
            errorList.add("ProductServerParameterValue object is null");
        } else {
            if (productServerParameterValue.getServerParameter() == null) {
                errorList.add("ServerParameter object is null");
            } else {
                if (productServerParameterValue.getProduct() == null && productServerParameterValue.getProductAddon() == null) {
                    errorList.add("Product and ProductAddon object is null");
                } else {
                    if (productServerParameterValue.getProduct() != null && !this.productService.existsById(productServerParameterValue.getProduct().getProductId())) {
                        errorList.add("Product not found");
                    }

                    if (productServerParameterValue.getProductAddon() != null && !this.productAddonService.existsById(productServerParameterValue.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon not found");
                    }

                    if (!this.serverParameterService.existsById(productServerParameterValue.getServerParameter().getServerParameterId())) {
                        errorList.add("ServerParameter not found");
                    }

                    if (productServerParameterValue.getValue() == null || productServerParameterValue.getValue().isEmpty()) {
                        errorList.add("Value is required");
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
    public ValidateObject validateUpdateItem(ProductServerParameterValue productServerParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productServerParameterValue == null) {
            errorList.add("ProductServerParameterValue object is null");
        } else {
            if (productServerParameterValue.getServerParameter() != null &&
                    (productServerParameterValue.getServerParameter().getServerParameterId() == null
                            || productServerParameterValue.getServerParameter().getServerParameterId() == 0)) {
                errorList.add("ServerParameter object is null");
            } else {
                if ((productServerParameterValue.getProduct() != null &&
                        (productServerParameterValue.getProduct().getProductId() == null
                                || productServerParameterValue.getProduct().getProductId() == 0)) ||
                        (productServerParameterValue.getProductAddon() != null && (productServerParameterValue.getProductAddon().getProductAddonId() == null
                                || productServerParameterValue.getProductAddon().getProductAddonId() == 0))) {
                    errorList.add("Product and productAddon object is null");
                } else {
                    if (!this.productServerParameterValueService.existsById(productServerParameterValue.getProductServerParameterId())) {
                        errorList.add("ProductServerParameterValue not found");
                    }

                    if (productServerParameterValue.getProduct() != null && (productServerParameterValue.getProduct().getProductId() == null
                            || productServerParameterValue.getProduct().getProductId() == 0)) {
                        errorList.add("Product not found");
                    } else {
                        if (productServerParameterValue.getProduct() != null &&
                                productServerParameterValue.getProduct().getProductId() != null
                                && !this.productService.existsById(productServerParameterValue.getProduct().getProductId())) {
                            errorList.add("Product not found");
                        }
                    }


                    if (productServerParameterValue.getProductAddon() != null &&
                            (productServerParameterValue.getProductAddon().getProductAddonId() == null
                                    || productServerParameterValue.getProductAddon().getProductAddonId() == 0)) {
                        errorList.add("ProductAddon not found");
                    } else {
                        if (productServerParameterValue.getProductAddon() != null &&
                                productServerParameterValue.getProductAddon().getProductAddonId() != null &&
                                !this.productAddonService.existsById(productServerParameterValue.getProductAddon().getProductAddonId())) {
                            errorList.add("ProductAddon not found");
                        }
                    }

                    if (productServerParameterValue.getServerParameter() != null && !this.serverParameterService.existsById(productServerParameterValue.getServerParameter().getServerParameterId())) {
                        errorList.add("ServerParameter not found");
                    }

                    if (productServerParameterValue.getValue() == null || productServerParameterValue.getValue().isEmpty()) {
                        errorList.add("Value is required");
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
    public ValidateObject deleteItem(ProductServerParameterValue productServerParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productServerParameterValue == null) {
            errorList.add("ProductServerParameterValue object is null");
        } else {
            if (!this.productServerParameterValueService.existsById(productServerParameterValue.getProductServerParameterId())) {
                errorList.add("ProductServerParameterValue not found");
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
    public ValidateObject findOne(ProductServerParameterValue productServerParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productServerParameterValue == null) {
            errorList.add("ProductServerParameterValue object is null");
        } else {
            if (!this.productServerParameterValueService.existsById(productServerParameterValue.getProductServerParameterId())) {
                errorList.add("ProductServerParameterValue not found");
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
    public ValidateObject findById(ProductServerParameterValue productServerParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productServerParameterValue == null) {
            errorList.add("ProductServerParameterValue object is null");
        } else {
            if (!this.productServerParameterValueService.existsById(productServerParameterValue.getProductServerParameterId())) {
                errorList.add("ProductServerParameterValue not found");
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
