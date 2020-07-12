package ir.parto.crm.modules.financial.controller.validate;

import ir.parto.crm.modules.financial.model.entity.Invoice;
import ir.parto.crm.modules.financial.model.service.InvoiceService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class InvoiceValidate implements ValidateInterface<Invoice> {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceValidate(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public ValidateObject validateAddNewItem(Invoice invoice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(invoice == null  || invoice.getOrder() == null || invoice.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(invoice == null  || invoice.getStatus() == null || invoice.getStatus() == 0){
            errorList.add("Status is required");
        }
        if(invoice == null  || invoice.getOfficialType() == null || invoice.getOfficialType() == 0){
            errorList.add("OfficialType is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(Invoice invoice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(invoice != null  && invoice.getOrder() != null && invoice.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(invoice != null  && invoice.getStatus() != null && invoice.getStatus() == 0){
            errorList.add("Status is required");
        }
        if(invoice != null  && invoice.getOfficialType() != null && invoice.getOfficialType() == 0){
            errorList.add("OfficialType is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(Invoice invoice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(invoice == null  || !this.invoiceService.existsById(invoice.getInvoiceId())){
            errorList.add("Invoice not found");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findOne(Invoice invoice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(invoice == null  || !this.invoiceService.existsById(invoice.getInvoiceId())){
            errorList.add("Invoice not found");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findById(Invoice invoice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(invoice == null  || !this.invoiceService.existsById(invoice.getInvoiceId())){
            errorList.add("Invoice not found");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
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
