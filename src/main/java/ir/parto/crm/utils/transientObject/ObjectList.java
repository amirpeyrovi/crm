package ir.parto.crm.utils.transientObject;

import java.util.List;

public class ObjectList<T> {
    private List<T> list;

    public ObjectList() {
    }

    public List<T> getList() {
        return list;
    }

    public ObjectList<T> setList(List<T> list) {
        this.list = list;
        return this;
    }
}
