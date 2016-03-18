package org.nutz.mvc.filter;

import java.util.ArrayList;
import java.util.List;

import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.ObjectInfo;

public class FilterInfoList {

    private List<ObjectInfo<? extends ActionFilter>> filters;

    private FilterPolicy policy;

    public FilterInfoList() {
        filters = new ArrayList<ObjectInfo<? extends ActionFilter>>();
    }

    public List<ObjectInfo<? extends ActionFilter>> getFilters() {
        return filters;
    }

    public void setFilters(List<ObjectInfo<? extends ActionFilter>> filters) {
        this.filters = filters;
    }

    public FilterPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(FilterPolicy policy) {
        this.policy = policy;
    }

    public void add(ObjectInfo<? extends ActionFilter> obj) {
        filters.add(obj);
    }
}
