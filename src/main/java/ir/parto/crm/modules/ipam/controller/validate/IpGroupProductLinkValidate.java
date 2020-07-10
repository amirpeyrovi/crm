package ir.parto.crm.modules.ipam.controller.validate;

import ir.parto.crm.modules.ipam.model.entity.IpGroupProductLink;
import ir.parto.crm.modules.ipam.model.service.IpGroupProductLinkService;
import ir.parto.crm.modules.ipam.model.service.IpGroupService;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class IpGroupProductLinkValidate implements ValidateInterface<IpGroupProductLink> {
    private IpGroupProductLinkService ipGroupProductLinkService;
    private IpGroupService ipGroupService;
    private ProductAddonService productAddonService;

    @Autowired
    public IpGroupProductLinkValidate(IpGroupProductLinkService ipGroupProductLinkService, IpGroupService ipGroupService, ProductAddonService productAddonService) {
        this.ipGroupProductLinkService = ipGroupProductLinkService;
        this.ipGroupService = ipGroupService;
        this.productAddonService = productAddonService;
    }

    @Override
    public ValidateObject validateAddNewItem(IpGroupProductLink ipGroupProductLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroupProductLink == null) {
            errorList.add("IpGroupProductLink object is nul");
        } else {
            if (ipGroupProductLink.getIpGroup() == null) {
                errorList.add("IpGroup object is nul");
            } else {
                if (ipGroupProductLink.getProductAddon() == null) {
                    errorList.add("ProductAddon object is nul");
                } else {
                    if (!this.ipGroupService.existsById(ipGroupProductLink.getIpGroup().getIpGroupId())) {
                        errorList.add("IpGroup Id not defined");
                    }

                    if (!this.productAddonService.existsById(ipGroupProductLink.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon Id not defined");
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
    public ValidateObject validateUpdateItem(IpGroupProductLink ipGroupProductLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroupProductLink == null) {
            errorList.add("IpGroupProductLink object is nul");
        } else {
            if (ipGroupProductLink.getIpGroup() == null) {
                errorList.add("IpGroup object is nul");
            } else {
                if (ipGroupProductLink.getProductAddon() == null) {
                    errorList.add("ProductAddon object is nul");
                } else {
                    if (!this.ipGroupProductLinkService.existsById(ipGroupProductLink.getIpGroupProductLinkId())) {
                        errorList.add("IpGroupProductLink Id not defined");
                    }

                    if (!this.ipGroupService.existsById(ipGroupProductLink.getIpGroup().getIpGroupId())) {
                        errorList.add("IpGroup Id not defined");
                    }

                    if (!this.productAddonService.existsById(ipGroupProductLink.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon Id not defined");
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
    public ValidateObject deleteItem(IpGroupProductLink ipGroupProductLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroupProductLink == null) {
            errorList.add("IpGroupProductLink object is nul");
        } else {
            if (!this.ipGroupProductLinkService.existsById(ipGroupProductLink.getIpGroupProductLinkId())) {
                errorList.add("IpGroupProductLink Id not defined");
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
    public ValidateObject findOne(IpGroupProductLink ipGroupProductLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroupProductLink == null) {
            errorList.add("IpGroupProductLink object is nul");
        } else {
            if (!this.ipGroupProductLinkService.existsById(ipGroupProductLink.getIpGroupProductLinkId())) {
                errorList.add("IpGroupProductLink Id not defined");
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
    public ValidateObject findById(IpGroupProductLink ipGroupProductLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (ipGroupProductLink == null) {
            errorList.add("IpGroupProductLink object is nul");
        } else {
            if (!this.ipGroupProductLinkService.existsById(ipGroupProductLink.getIpGroupProductLinkId())) {
                errorList.add("IpGroupProductLink Id not defined");
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
