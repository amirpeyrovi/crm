package ir.parto.crm.modules.reseller.controller.validate;

import ir.parto.crm.modules.order.model.service.OrderItemService;
import ir.parto.crm.modules.reseller.model.entity.ResellerHistory;
import ir.parto.crm.modules.reseller.model.service.ResellerHistoryService;
import ir.parto.crm.modules.reseller.model.service.ResellerService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ResellerHistoryValidate implements ValidateInterface<ResellerHistory> {
    private ResellerHistoryService resellerHistoryService;
    private OrderItemService orderItemService;
    private ResellerService resellerService;

    @Autowired
    public ResellerHistoryValidate(ResellerHistoryService resellerHistoryService, OrderItemService orderItemService, ResellerService resellerService) {
        this.resellerHistoryService = resellerHistoryService;
        this.orderItemService = orderItemService;
        this.resellerService = resellerService;
    }

    @Override
    public ValidateObject validateAddNewItem(ResellerHistory resellerHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerHistory == null) {
            errorList.add("ResellerHistory object is nul");
        } else {
            if (resellerHistory.getReseller() == null) {
                errorList.add("Reseller object is nul");
            } else {
                if (resellerHistory.getOrderItem() == null) {
                    errorList.add("OrderItem object is nul");
                } else {
                    if (!this.resellerService.existsById(resellerHistory.getReseller().getResellerId())) {
                        errorList.add("Reseller Id not defined");
                    }

                    if (!this.orderItemService.existsById(resellerHistory.getOrderItem().getOrderItemId())) {
                        errorList.add("OrderItem Id not defined");
                    }

                    if (resellerHistory.getPercentage() == null) {
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
    public ValidateObject validateUpdateItem(ResellerHistory resellerHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerHistory == null) {
            errorList.add("ResellerHistory object is nul");
        } else {
            if (resellerHistory.getReseller() == null) {
                errorList.add("Reseller object is nul");
            } else {
                if (resellerHistory.getOrderItem() == null) {
                    errorList.add("OrderItem object is nul");
                } else {
                    if (!this.resellerHistoryService.existsById(resellerHistory.getResellerHistoryId())) {
                        errorList.add("ResellerHistory Id not defined");
                    }

                    if (!this.resellerService.existsById(resellerHistory.getReseller().getResellerId())) {
                        errorList.add("Reseller Id not defined");
                    }

                    if (!this.orderItemService.existsById(resellerHistory.getOrderItem().getOrderItemId())) {
                        errorList.add("OrderItem Id not defined");
                    }

                    if (resellerHistory.getPercentage() == null) {
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
    public ValidateObject deleteItem(ResellerHistory resellerHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerHistory == null) {
            errorList.add("ResellerHistory object is nul");
        } else {
            if (!this.resellerHistoryService.existsById(resellerHistory.getResellerHistoryId())) {
                errorList.add("ResellerHistory Id not defined");
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
    public ValidateObject findOne(ResellerHistory resellerHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerHistory == null) {
            errorList.add("ResellerHistory object is nul");
        } else {
            if (!this.resellerHistoryService.existsById(resellerHistory.getResellerHistoryId())) {
                errorList.add("ResellerHistory Id not defined");
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
    public ValidateObject findById(ResellerHistory resellerHistory) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (resellerHistory == null) {
            errorList.add("ResellerHistory object is nul");
        } else {
            if (!this.resellerHistoryService.existsById(resellerHistory.getResellerHistoryId())) {
                errorList.add("ResellerHistory Id not defined");
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
