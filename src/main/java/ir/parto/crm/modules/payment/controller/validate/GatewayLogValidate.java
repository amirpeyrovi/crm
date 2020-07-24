package ir.parto.crm.modules.payment.controller.validate;

import ir.parto.crm.modules.payment.model.entity.GatewayLog;
import ir.parto.crm.modules.payment.model.service.GatewayLogService;
import ir.parto.crm.modules.payment.model.service.GatewayService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class GatewayLogValidate  implements ValidateInterface<GatewayLog> {
    private GatewayLogService gatewayLogService;
    private GatewayService gatewayService;
    @Autowired
    public GatewayLogValidate(GatewayLogService gatewayLogService, GatewayService gatewayService) {
        this.gatewayLogService = gatewayLogService;
        this.gatewayService = gatewayService;
    }

    @Override
    public ValidateObject validateAddNewItem(GatewayLog gatewayLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (gatewayLog == null) {
            errorList.add("object is null");
        } else {
            if (gatewayLog.getStatus() == null || gatewayLog.getStatus() == 0) {
                errorList.add("Status is required");
            }
            if (gatewayLog.getResponse() == null || gatewayLog.getResponse().isEmpty()) {
                errorList.add("Response is required");
            }
            if (gatewayLog.getRequest() == null || gatewayLog.getRequest().isEmpty()) {
                errorList.add("Request is required");
            }
            if (gatewayLog.getGateway() == null ) {
                errorList.add("Gateway is required");
            }else{
                if(!this.gatewayService.existsById(gatewayLog.getGateway().getGatewayId())){
                    errorList.add("Gateway Id is required");
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
    public ValidateObject validateUpdateItem(GatewayLog gatewayLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (gatewayLog == null) {
            errorList.add("object is null");
        } else {
            if (gatewayLog.getStatus() == null || gatewayLog.getStatus() == 0) {
                errorList.add("Status is required");
            }
            if (gatewayLog.getResponse() == null || gatewayLog.getResponse().isEmpty()) {
                errorList.add("Response is required");
            }
            if (gatewayLog.getRequest() == null || gatewayLog.getRequest().isEmpty()) {
                errorList.add("Request is required");
            }
            if (gatewayLog.getGateway() == null ) {
                errorList.add("Gateway is required");
            }else{
                if(!this.gatewayService.existsById(gatewayLog.getGateway().getGatewayId())){
                    errorList.add("Gateway Id is required");
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
    public ValidateObject deleteItem(GatewayLog gatewayLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (gatewayLog == null) {
            errorList.add("object is null");
        } else {
            if (!this.gatewayLogService.existsById(gatewayLog.getGatewayLogId())) {
                errorList.add("GatewayLog Id not defined");
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
    public ValidateObject findOne(GatewayLog gatewayLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (gatewayLog == null) {
            errorList.add("object is null");
        } else {
            if (!this.gatewayLogService.existsById(gatewayLog.getGatewayLogId())) {
                errorList.add("GatewayLog Id not defined");
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
    public ValidateObject findById(GatewayLog gatewayLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (gatewayLog == null) {
            errorList.add("object is null");
        } else {
            if (!this.gatewayLogService.existsById(gatewayLog.getGatewayLogId())) {
                errorList.add("GatewayLog Id not defined");
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
