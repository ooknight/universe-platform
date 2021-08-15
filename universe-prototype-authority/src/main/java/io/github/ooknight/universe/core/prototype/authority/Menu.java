package io.github.ooknight.universe.core.prototype.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Menu implements Serializable {

    private final String key;
    private final String name;
    private final String url;
    private final String icon;
    private final List<Menu> children = new ArrayList<>();

    public Menu(String key, String name) {
        this(key, name, null, null);
    }

    public Menu(String key, String name, String url, String icon) {
        this.key = key;
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getIcon() {
        return icon;
    }

    public List<Menu> getChildren() {
        return children;
    }
}
