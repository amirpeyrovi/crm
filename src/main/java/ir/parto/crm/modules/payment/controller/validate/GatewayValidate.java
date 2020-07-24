package ir.parto.crm.modules.payment.controller.validate;

import ir.parto.crm.modules.payment.model.entity.Gateway;
import ir.parto.crm.modules.payment.model.repository.GatewayRepository;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class GatewayValidate implements ValidateInterface<Gateway> {
    private GatewayRepository gatewayRepository;
//    private PaymentVendorService paymentVendorService;

    @Autowired
    public GatewayValidate(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    @Override
    public ValidateObject validateAddNewItem(Gateway gateway) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(gateway == null){
            errorList.add("Object is null");
        }else{
            if(gateway.getTitle() == null || gateway.getTitle().isEmpty() ){
                errorList.add("Title is required");
            }
            if(gateway.getAcceptorId() == null || gateway.getAcceptorId().isEmpty() ){
                errorList.add("Acceptor is required");
            }
            if(gateway.getAddress() == null || gateway.getAddress().isEmpty() ){
                errorList.add("Address is required");
            }
            if(gateway.getGatewayNum() == null || gateway.getGatewayNum().isEmpty() ){
                errorList.add("Gateway Num is required");
            }
            if(gateway.getIdentificationCode() == null || gateway.getIdentificationCode().isEmpty() ){
                errorList.add("Identification Code is required");
            }
            if(gateway.getMerchantId() == null || gateway.getMerchantId().isEmpty() ){
                errorList.add("Merchant is required");
            }
            if(gateway.getPaymentVendor() == null || gateway.getPaymentVendor().getVendorId() == 0 ){
                errorList.add("Payment Vendor is required");
            }
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
    public ValidateObject validateUpdateItem(Gateway gateway) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(gateway == null){
            errorList.add("Object is null");
        }else{
            if(gateway.getTitle() == null || gateway.getTitle().isEmpty() ){
                errorList.add("Title is required");
            }
            if(gateway.getAcceptorId() == null || gateway.getAcceptorId().isEmpty() ){
                errorList.add("Acceptor is required");
            }
            if(gateway.getAddress() == null || gateway.getAddress().isEmpty() ){
                errorList.add("Address is required");
            }
            if(gateway.getGatewayNum() == null || gateway.getGatewayNum().isEmpty() ){
                errorList.add("Gateway Num is required");
            }
            if(gateway.getIdentificationCode() == null || gateway.getIdentificationCode().isEmpty() ){
                errorList.add("Identification Code is required");
            }
            if(gateway.getMerchantId() == null || gateway.getMerchantId().isEmpty() ){
                errorList.add("Merchant is required");
            }
            if(gateway.getPaymentVendor() == null || gateway.getPaymentVendor().getVendorId() == 0 ){
                errorList.add("Payment Vendor is required");
            }else{
//                if(!this.paymentVendorService.ex){
//
//                }
            }
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
    public ValidateObject deleteItem(Gateway gateway) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(gateway == null){
            errorList.add("Object is null");
        }else{
            if(gateway.getTitle() == null || gateway.getTitle().isEmpty() ){
                errorList.add("Title is required");
            }
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
    public ValidateObject findOne(Gateway gateway) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(gateway == null){
            errorList.add("Object is null");
        }else{
            if(!this.gatewayRepository.existsByIsDeletedIsNullAndGatewayId(gateway.getGatewayId())){
                errorList.add("Gateway not found");
            }
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
    public ValidateObject findById(Gateway gateway) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(gateway == null){
            errorList.add("Object is null");
        }else{
            if(!this.gatewayRepository.existsByIsDeletedIsNullAndGatewayId(gateway.getGatewayId())){
                errorList.add("Gateway not found");
            }
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
