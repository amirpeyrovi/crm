package ir.parto.crm.modules.dataCenter.controller.validate;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServer;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterRackService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterServerService;
import ir.parto.crm.modules.dataCenter.model.service.DataCenterVendorService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class DataCenterServerValidate implements ValidateInterface<DataCenterServer> {
    private DataCenterServerService dataCenterServerService;
    private DataCenterRackService dataCenterRackService;
    private DataCenterVendorService dataCenterVendorService;

    public DataCenterServerValidate(DataCenterServerService dataCenterServerService, DataCenterRackService dataCenterRackService, DataCenterVendorService dataCenterVendorService) {
        this.dataCenterServerService = dataCenterServerService;
        this.dataCenterRackService = dataCenterRackService;
        this.dataCenterVendorService = dataCenterVendorService;
    }

    @Override
    public ValidateObject validateAddNewItem(DataCenterServer dataCenterServer) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServer == null) {
            errorList.add("Server object is nul");
        } else {
            if (dataCenterServer.getDataCenterRack() == null) {
                errorList.add("Rack object is nul");
            } else {
                if (dataCenterServer.getDataCenterVendor() == null) {
                    errorList.add("Server object is nul");
                } else {
                    if (!this.dataCenterRackService.existsById(dataCenterServer.getDataCenterRack().getRackId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (!this.dataCenterVendorService.existsById(dataCenterServer.getDataCenterVendor().getVendorId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (dataCenterServer.getTotalUnit() == null || dataCenterServer.getTotalUnit() == 0) {
                        errorList.add("TotalUnit is required");
                    }

                    if (dataCenterServer.getStartUnit() == null || dataCenterServer.getStartUnit() == 0) {
                        errorList.add("StartUnit is required");
                    }

                    if (dataCenterServer.getEndUnit() == null || dataCenterServer.getEndUnit() == 0) {
                        errorList.add("EndUnit is required");
                    }

                    if (dataCenterServer.getTitle() == null || dataCenterServer.getTitle().isEmpty()) {
                        errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(DataCenterServer dataCenterServer) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServer == null) {
            errorList.add("Server object is nul");
        } else {
            if (dataCenterServer.getDataCenterRack() == null) {
                errorList.add("Rack object is nul");
            } else {
                if (dataCenterServer.getDataCenterVendor() == null) {
                    errorList.add("Server object is nul");
                } else {
                    if (!this.dataCenterServerService.existsById(dataCenterServer.getServerId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (!this.dataCenterRackService.existsById(dataCenterServer.getDataCenterRack().getRackId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (!this.dataCenterVendorService.existsById(dataCenterServer.getDataCenterVendor().getVendorId())) {
                        errorList.add("Server Id not defined");
                    }

                    if (dataCenterServer.getTotalUnit() == null || dataCenterServer.getTotalUnit() == 0) {
                        errorList.add("TotalUnit is required");
                    }

                    if (dataCenterServer.getStartUnit() == null || dataCenterServer.getStartUnit() == 0) {
                        errorList.add("StartUnit is required");
                    }

                    if (dataCenterServer.getEndUnit() == null || dataCenterServer.getEndUnit() == 0) {
                        errorList.add("EndUnit is required");
                    }

                    if (dataCenterServer.getTitle() == null || dataCenterServer.getTitle().isEmpty()) {
                        errorList.add("Title is required");
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
    public ValidateObject deleteItem(DataCenterServer dataCenterServer) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServer == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerService.existsById(dataCenterServer.getServerId())) {
                errorList.add("Server Id not defined");
            }
            List<DataCenterRack> dataCenterRacks = this.dataCenterRackService.findAllByIdAndIsDeletedIsNull(dataCenterServer.getDataCenterRack().getRackId());
            if(dataCenterRacks.size()>0){
                errorList.add("Related Racks");
            }

            List<DataCenterVendor> dataCenterVendors = this.dataCenterVendorService.findAllByIdAndIsDeletedIsNull(
                    dataCenterServer.getDataCenterVendor().getVendorId());
            if(dataCenterVendors.size()>0){
                errorList.add("Related Vendors");
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
    public ValidateObject findOne(DataCenterServer dataCenterServer) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServer == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerService.existsById(dataCenterServer.getServerId())) {
                errorList.add("Server Id not defined");
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
    public ValidateObject findById(DataCenterServer dataCenterServer) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (dataCenterServer == null) {
            errorList.add("object is nul");
        } else {
            if (!this.dataCenterServerService.existsById(dataCenterServer.getServerId())) {
                errorList.add("Server Id not defined");
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
