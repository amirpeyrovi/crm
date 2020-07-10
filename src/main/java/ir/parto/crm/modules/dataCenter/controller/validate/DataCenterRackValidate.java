package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DataCenterRackValidate implements ValidateInterface<DataCenterRack> {
    private DataCenterRackService dataCenterRackService;
    private DataCenterService dataCenterService;

    @Autowired
    public DataCenterRackValidate(DataCenterRackService dataCenterRackService, DataCenterService dataCenterService) {
        this.dataCenterRackService = dataCenterRackService;
        this.dataCenterService = dataCenterService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterRack dataCenterRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterRack == null) {
            errorList.add("object is nul");
        } else {
            if (dataCenterRack.getDataCenter() == null) {
                errorList.add("DataCenter object is nul");
            } else {
                if (!this.dataCenterService.existsById(dataCenterRack.getDataCenter().getDataCenterId())) {
                    errorList.add("DataCenter Id not defined");
                }

                if (dataCenterRack.getTitle() == null || dataCenterRack.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (dataCenterRack.getRackNumber() == null || dataCenterRack.getRackNumber() == 0) {
                    errorList.add("RackNumber is required");
                }

                if (dataCenterRack.getTotalUnit() == null || dataCenterRack.getTotalUnit() == 0) {
                    errorList.add("TotalUnit is required");
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
    public ValidateObject validateUpdateItem(DataCenterRack dataCenterRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterRack == null) {
            errorList.add("object is nul");
        } else {
            if (dataCenterRack.getDataCenter() == null) {
                errorList.add("DataCenter object is nul");
            } else {
                if (!this.dataCenterRackService.existsById(dataCenterRack.getRackId())) {
                    errorList.add("Rack Id not defined");
                }

                if (!this.dataCenterService.existsById(dataCenterRack.getDataCenter().getDataCenterId())) {
                    errorList.add("DataCenter Id not defined");
                }

                if (dataCenterRack.getTitle() == null || dataCenterRack.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (dataCenterRack.getRackNumber() == null || dataCenterRack.getRackNumber() == 0) {
                    errorList.add("RackNumber is required");
                }

                if (dataCenterRack.getTotalUnit() == null || dataCenterRack.getTotalUnit() == 0) {
                    errorList.add("TotalUnit is required");
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
    public ValidateObject deleteItem(DataCenterRack dataCenterRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterRack == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterRackService.existsById(dataCenterRack.getRackId())) {
                errorList.add("DataCenterRack Id not defined");
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
    public ValidateObject findOne(DataCenterRack dataCenterRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterRack == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterRackService.existsById(dataCenterRack.getRackId())) {
                errorList.add("DataCenterRack Id not defined");
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
    public ValidateObject findById(DataCenterRack dataCenterRack) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterRack == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterRackService.existsById(dataCenterRack.getRackId())) {
                errorList.add("DataCenterRack Id not defined");
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
