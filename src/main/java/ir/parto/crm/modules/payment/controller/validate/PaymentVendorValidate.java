package ir.parto.crm.modules.payment.controller.validate;

import ir.parto.crm.modules.payment.model.entity.PaymentVendor;
import ir.parto.crm.modules.payment.model.service.PaymentVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PaymentVendorValidate implements ValidateInterface<PaymentVendor> {
    private PaymentVendorService paymentVendorService;
    @Autowired
    public PaymentVendorValidate(PaymentVendorService paymentVendorService) {
        this.paymentVendorService = paymentVendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(PaymentVendor paymentVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (paymentVendor == null) {
            errorList.add("object is null");
        } else {
            if (paymentVendor.getTitle() == null || paymentVendor.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }
            if (paymentVendor.getName() == null || paymentVendor.getName().isEmpty()) {
                errorList.add("Name is required");
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
    public ValidateObject validateUpdateItem(PaymentVendor paymentVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (paymentVendor == null) {
            errorList.add("object is null");
        } else {
            if (paymentVendor.getTitle() == null || paymentVendor.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }
            if (paymentVendor.getName() == null || paymentVendor.getName().isEmpty()) {
                errorList.add("Name is required");
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
    public ValidateObject deleteItem(PaymentVendor paymentVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (paymentVendor == null) {
            errorList.add("object is null");
        } else {
            if (!this.paymentVendorService.existsById(paymentVendor.getVendorId())) {
                errorList.add("PaymentVendor Id not defined");
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
    public ValidateObject findOne(PaymentVendor paymentVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (paymentVendor == null) {
            errorList.add("object is null");
        } else {
            if (!this.paymentVendorService.existsById(paymentVendor.getVendorId())) {
                errorList.add("PaymentVendor Id not defined");
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
    public ValidateObject findById(PaymentVendor paymentVendor) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (paymentVendor == null) {
            errorList.add("object is null");
        } else {
            if (!this.paymentVendorService.existsById(paymentVendor.getVendorId())) {
                errorList.add("PaymentVendor Id not defined");
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
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }
}
