package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchPort;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchPortService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterSwitchPortValidate implements ValidateInterface<DataCenterSwitchPort> {
    private DataCenterSwitchPortService dataCenterSwitchPortService;
    private DataCenterSwitchService dataCenterSwitchService;

    @Autowired
    public DataCenterSwitchPortValidate(DataCenterSwitchPortService dataCenterSwitchPortService, DataCenterSwitchService dataCenterSwitchService) {
        this.dataCenterSwitchPortService = dataCenterSwitchPortService;
        this.dataCenterSwitchService = dataCenterSwitchService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterSwitchPort dataCenterSwitchPort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchPort == null) {
            errorList.add("SwitchPort object is nul");
        } else {
            if (dataCenterSwitchPort.getDataCenterSwitch() == null) {
                errorList.add("Switch object is nul");
            } else {
                if (!this.dataCenterSwitchService.existsById(dataCenterSwitchPort.getDataCenterSwitch().getSwitchId())) {
                    errorList.add("Switch Id not defined");
                }

                if (dataCenterSwitchPort.getNumber() == null || dataCenterSwitchPort.getNumber() == 0) {
                    errorList.add("Number is required");
                }

                if (dataCenterSwitchPort.getStatus() == null || dataCenterSwitchPort.getStatus() == 0) {
                    errorList.add("Status is required");
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
    public ValidateObject validateUpdateItem(DataCenterSwitchPort dataCenterSwitchPort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchPort == null) {
            errorList.add("SwitchPort object is nul");
        } else {
            if (dataCenterSwitchPort.getDataCenterSwitch() == null) {
                errorList.add("Switch object is nul");
            } else {
                if (!this.dataCenterSwitchPortService.existsById(dataCenterSwitchPort.getPortId())) {
                    errorList.add("SwitchPort Id not defined");
                }

                if (!this.dataCenterSwitchService.existsById(dataCenterSwitchPort.getDataCenterSwitch().getSwitchId())) {
                    errorList.add("Switch Id not defined");
                }

                if (dataCenterSwitchPort.getNumber() == null || dataCenterSwitchPort.getNumber() == 0) {
                    errorList.add("Number is required");
                }

                if (dataCenterSwitchPort.getStatus() == null || dataCenterSwitchPort.getStatus() == 0) {
                    errorList.add("Status is required");
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
    public ValidateObject deleteItem(DataCenterSwitchPort dataCenterSwitchPort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchPort == null) {
            errorList.add("SwitchPort object is nul");
        } else {
            if (!this.dataCenterSwitchPortService.existsById(dataCenterSwitchPort.getPortId())) {
                errorList.add("SwitchPort Id not defined");
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
    public ValidateObject findOne(DataCenterSwitchPort dataCenterSwitchPort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchPort == null) {
            errorList.add("SwitchPort object is nul");
        } else {
            if (!this.dataCenterSwitchPortService.existsById(dataCenterSwitchPort.getPortId())) {
                errorList.add("SwitchPort Id not defined");
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
    public ValidateObject findById(DataCenterSwitchPort dataCenterSwitchPort) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitchPort == null) {
            errorList.add("SwitchPort object is nul");
        } else {
            if (!this.dataCenterSwitchPortService.existsById(dataCenterSwitchPort.getPortId())) {
                errorList.add("SwitchPort Id not defined");
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
