package ir.parto.crm.utils.transientObject;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Convert2Object {
    public static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(false)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public Convert2Object() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass) {
        return entityList.stream()
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }


}
