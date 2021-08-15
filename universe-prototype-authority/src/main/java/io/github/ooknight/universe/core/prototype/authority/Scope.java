package io.github.ooknight.universe.core.prototype.authority;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public final class Scope implements Serializable {

    private static final Condition ALWAYS_TRUE = new Condition("1", "1");
    private static final Condition ALWAYS_FALSE = new Condition("1", "0");
    //
    private Long uid;
    private Long rid;
    private Long gid;
    private Set<String> restricteds;
    private Map<Class, Condition> conditions;
    private Condition defaultCondition;

    private Scope(Long uid, Long rid, Long gid, Set<String> restricteds, Map<Class, Condition> conditions, boolean supervisor) {
        this.uid = uid;
        this.rid = rid;
        this.gid = gid;
        this.restricteds = (restricteds == null ? Sets.newHashSet() : restricteds);
        this.conditions = (conditions == null ? Maps.newHashMap() : conditions);
        this.defaultCondition = supervisor ? ALWAYS_TRUE : ALWAYS_FALSE;
    }

    public static Scope DUMMY() {
        return new Scope(0L, 0L, 0L, null, null, false);
    }

    public static Scope BUILD(Long uid, Long rid, Long gid, boolean supervisor) {
        return new Scope(uid, rid, gid, null, null, supervisor);
    }

    public static Scope BUILD(Long uid, Long rid, Long gid, Set<String> restricteds, Map<Class, Condition> conditions, boolean supervisor) {
        return new Scope(uid, rid, gid, restricteds, conditions, supervisor);
    }

    public Long uid() {
        return uid;
    }

    public Long rid() {
        return rid;
    }

    public Long gid() {
        return gid;
    }

    public Set<String> restricteds() {
        return this.restricteds;
    }

    public Map<Class, Condition> expressions() {
        return this.conditions;
    }

    public Condition condition(Class clz) {
        return conditions.getOrDefault(clz, defaultCondition);
    }

    public static class Condition implements Serializable {

        private String left;
        private String right;

        private Condition(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String raw() {
            return left + "=" + right;
        }
    }
}
