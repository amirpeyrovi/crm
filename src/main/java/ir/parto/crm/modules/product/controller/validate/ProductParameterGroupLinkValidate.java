package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupLinkService;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductParameterGroupLinkValidate implements ValidateInterface<ProductParameterGroupLink> {
    private ProductParameterGroupLinkService productParameterGroupLinkService;
    private ProductParameterGroupService productParameterGroupService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductParameterGroupLinkValidate(ProductParameterGroupLinkService productParameterGroupLinkService, ProductParameterGroupService productParameterGroupService, ProductAddonService productAddonService, ProductService productService) {
        this.productParameterGroupLinkService = productParameterGroupLinkService;
        this.productParameterGroupService = productParameterGroupService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductParameterGroupLink productParameterGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroupLink == null) {
            errorList.add("ProductParameterGroupLink object is null");
        } else {
            if (productParameterGroupLink.getProductParameterGroup() == null) {
                errorList.add("ProductParameterGroup object is null");
            } else {
                if (productParameterGroupLink.getProduct() == null || productParameterGroupLink.getProductAddon() == null) {
                    errorList.add("Product and ProductAddon object is null");
                } else {
                    if (productParameterGroupLink.getProduct() != null && productParameterGroupLink.getProductAddon() != null) {
                        errorList.add("Product and ProductAddon object is not null");
                    } else {
                        if (!this.productParameterGroupService.existsById(productParameterGroupLink.getProductParameterGroup().getProductParameterGroupId())) {
                            errorList.add("ProductAddon not found");
                        }

                        if (productParameterGroupLink.getProductAddon() != null &&
                                !this.productAddonService.existsById(productParameterGroupLink.getProductAddon().getProductAddonId())) {
                            errorList.add("ProductAddon not found");
                        }

                        if (productParameterGroupLink.getProduct() != null && !this.productService.existsById(productParameterGroupLink.getProduct().getProductId())) {
                            errorList.add("Product not found");
                        }
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
    public ValidateObject validateUpdateItem(ProductParameterGroupLink productParameterGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroupLink == null) {
            errorList.add("ProductParameterGroupLink object is null");
        } else {
            if (productParameterGroupLink.getProductParameterGroup() == null) {
                errorList.add("ProductParameterGroup object is null");
            } else {
                if (productParameterGroupLink.getProduct() == null && productParameterGroupLink.getProductAddon() == null) {
                    errorList.add("Product and ProductAddon object is null");
                } else {
                    if (productParameterGroupLink.getProduct() != null && productParameterGroupLink.getProductAddon() != null) {
                        errorList.add("Product and ProductAddon object is not null");
                    } else {
                        if (!this.productParameterGroupLinkService.existsById(productParameterGroupLink.getProductParameterGroupLinkId())) {
                            errorList.add("ProductParameterGroupLink not found");
                        }

                         if (!this.productParameterGroupService.existsById(productParameterGroupLink.getProductParameterGroup().getProductParameterGroupId())) {
                            errorList.add("ProductParameterGroup not found");
                        }

                        if (productParameterGroupLink.getProductAddon() != null && !this.productAddonService.existsById(productParameterGroupLink.getProductAddon().getProductAddonId())) {
                            errorList.add("ProductAddon not found");
                        }

                        if (productParameterGroupLink.getProduct() != null && !this.productService.existsById(productParameterGroupLink.getProduct().getProductId())) {
                            errorList.add("Product not found");
                        }
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
    public ValidateObject deleteItem(ProductParameterGroupLink productParameterGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroupLink == null) {
            errorList.add("ProductParameterGroupLink object is null");
        } else {
            if (!this.productParameterGroupLinkService.existsById(productParameterGroupLink.getProductParameterGroupLinkId())) {
                errorList.add("ProductParameterGroupLink not found");
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
    public ValidateObject findOne(ProductParameterGroupLink productParameterGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroupLink == null) {
            errorList.add("ProductParameterGroupLink object is null");
        } else {
            if (!this.productParameterGroupLinkService.existsById(productParameterGroupLink.getProductParameterGroupLinkId())) {
                errorList.add("ProductParameterGroupLink not found");
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
    public ValidateObject findById(ProductParameterGroupLink productParameterGroupLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroupLink == null) {
            errorList.add("ProductParameterGroupLink object is null");
        } else {
            if (!this.productParameterGroupLinkService.existsById(productParameterGroupLink.getProductParameterGroupLinkId())) {
                errorList.add("ProductParameterGroupLink not found");
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
