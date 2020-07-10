package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterSwitchService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterSwitchValidate implements ValidateInterface<DataCenterSwitch> {
    private DataCenterSwitchService dataCenterSwitchService;
    private DataCenterRackService dataCenterRackService;

    @Autowired
    public DataCenterSwitchValidate(DataCenterSwitchService dataCenterSwitchService, DataCenterRackService dataCenterRackService) {
        this.dataCenterSwitchService = dataCenterSwitchService;
        this.dataCenterRackService = dataCenterRackService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterSwitch dataCenterSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitch == null) {
            errorList.add("Switch object is nul");
        } else {
            if (dataCenterSwitch.getDataCenterRack() == null) {
                errorList.add("Rack object is nul");
            } else {
                if (!this.dataCenterRackService.existsById(dataCenterSwitch.getDataCenterRack().getRackId())) {
                    errorList.add("Rack Id not defined");
                }

                if (dataCenterSwitch.getTotalUnit() == null || dataCenterSwitch.getTotalUnit() == 0) {
                    errorList.add("TotalUnit is required");
                }

                if (dataCenterSwitch.getStartUnit() == null || dataCenterSwitch.getStartUnit() == 0) {
                    errorList.add("StartUnit is required");
                }

                if (dataCenterSwitch.getEndUnit() == null || dataCenterSwitch.getEndUnit() == 0) {
                    errorList.add("EndUnit is required");
                }

                if (dataCenterSwitch.getPortCount() == null || dataCenterSwitch.getPortCount() == 0) {
                    errorList.add("PortCount is required");
                }

                if (dataCenterSwitch.getTitle() == null || dataCenterSwitch.getTitle().isEmpty()) {
                    errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(DataCenterSwitch dataCenterSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitch == null) {
            errorList.add("Switch object is nul");
        } else {
            if (dataCenterSwitch.getDataCenterRack() == null) {
                errorList.add("Rack object is nul");
            } else {
                if (!this.dataCenterSwitchService.existsById(dataCenterSwitch.getSwitchId())) {
                    errorList.add("Switch Id not defined");
                }

                if (!this.dataCenterRackService.existsById(dataCenterSwitch.getDataCenterRack().getRackId())) {
                    errorList.add("Rack Id not defined");
                }

                if (dataCenterSwitch.getTotalUnit() == null || dataCenterSwitch.getTotalUnit() == 0) {
                    errorList.add("TotalUnit is required");
                }

                if (dataCenterSwitch.getStartUnit() == null || dataCenterSwitch.getStartUnit() == 0) {
                    errorList.add("StartUnit is required");
                }

                if (dataCenterSwitch.getEndUnit() == null || dataCenterSwitch.getEndUnit() == 0) {
                    errorList.add("EndUnit is required");
                }

                if (dataCenterSwitch.getPortCount() == null || dataCenterSwitch.getPortCount() == 0) {
                    errorList.add("PortCount is required");
                }

                if (dataCenterSwitch.getTitle() == null || dataCenterSwitch.getTitle().isEmpty()) {
                    errorList.add("Title is required");
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
    public ValidateObject deleteItem(DataCenterSwitch dataCenterSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitch == null) {
            errorList.add("Switch object is nul");
        } else {
            if (!this.dataCenterSwitchService.existsById(dataCenterSwitch.getSwitchId())) {
                errorList.add("Switch Id not defined");
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
    public ValidateObject findOne(DataCenterSwitch dataCenterSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitch == null) {
            errorList.add("Switch object is nul");
        } else {
            if (!this.dataCenterSwitchService.existsById(dataCenterSwitch.getSwitchId())) {
                errorList.add("Switch Id not defined");
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
    public ValidateObject findById(DataCenterSwitch dataCenterSwitch) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterSwitch == null) {
            errorList.add("Switch object is nul");
        } else {
            if (!this.dataCenterSwitchService.existsById(dataCenterSwitch.getSwitchId())) {
                errorList.add("Switch Id not defined");
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
