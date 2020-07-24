package ir.parto.crm.modules.payment.model.service;

import ir.parto.crm.modules.payment.controller.validate.GatewayValidate;
import ir.parto.crm.modules.payment.model.entity.Gateway;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class GatewayService implements ServiceInterface<Gateway> {

    private GatewayValidate gatewayValidate;

    @Autowired
    public GatewayService(GatewayValidate gatewayValidate) {
        this.gatewayValidate = gatewayValidate;
    }

    @Override
    public Gateway addNewItem(Gateway gateway) {
        return null;
    }

    @Override
    public Gateway updateItem(Gateway gateway) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    @Override
    public Gateway deleteItem(Gateway gateway) {
        return null;
    }

    @Override
    public List<Gateway> findAllItem() {
        return null;
    }

    @Override
    public Page<Gateway> findAllItem(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Gateway> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Gateway findOne(Gateway gateway) {
        return null;
    }

    @Override
    public Gateway findById(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }
}
