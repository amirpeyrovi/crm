package ir.parto.crm.utils.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Component
public @interface ValidationAnnotation {
}
