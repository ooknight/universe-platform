package io.github.ooknight.universe.core.domain.x;

import io.github.ooknight.universe.core.domain.UEntity;

public final class DOMAIN {

    private DOMAIN() {}

    public static <E extends UEntity> RuntimeException ENTITY_NOT_FOUND(Class<E> clazz, Object param) {
        return new RuntimeException("ENTITY_NOT_FOUND:" + clazz + ":" + param);
    }

    public static <E extends UEntity> RuntimeException ENTITY_NOT_FOUND() {
        return new RuntimeException("ENTITY_NOT_FOUND");
    }
}
