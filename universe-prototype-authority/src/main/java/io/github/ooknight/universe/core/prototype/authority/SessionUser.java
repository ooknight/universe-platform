package io.github.ooknight.universe.core.prototype.authority;

import java.io.Serializable;
import java.util.Set;

public interface SessionUser extends Serializable {

    /*
    //String TAG_ADMIN = "admin";
    //String SESSION_PARAMETER_NAME = "who";
    */

    Long uid();

    Long rid();

    Long gid();

    String name();

    InteriorUserType type();

    Set<Long> permissions();

    Set<Long> resources();

    Set<String> tags();

    /*
    Scope scope();
    */
}
