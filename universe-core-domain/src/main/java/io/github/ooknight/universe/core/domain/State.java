package io.github.ooknight.universe.core.domain;

import io.ebean.annotation.EnumValue;
import io.ebean.util.AnnotationUtil;

public interface State {

    default int code() {
        try {
            String name = ((Enum<?>) this).name();
            EnumValue value = AnnotationUtil.get(this.getClass().getDeclaredField(name), EnumValue.class);
            if (value == null) {
                return 0;
            } else {
                return Integer.parseInt(value.value());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
