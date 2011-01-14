package com.blogspot.fuud.java.experemental.hibernate.dsl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class DetachedCriteriaBuilder<T> {
    private DetachedCriteria detachedCriteria;
    protected T object;
    private List<String> propertyPath = new ArrayList<String>();
    private BiMap<String, String> aliases = HashBiMap.create();

    public DetachedCriteriaBuilder(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        clearPropertyName();
        detachedCriteria = DetachedCriteria.forClass(clazz, "object");
        aliases.put("object", "object");
        object = getWrapperObject(clazz);
    }

    private T getWrapperObject(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(clazz);
        f.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return m.getParameterTypes().length == 0 && (m.getName().startsWith("get"));
            }
        });
        Class c = f.createClass();
        MethodHandler methodHandler = new MethodHandler() {
            public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
                final String name = m.getName().substring("get".length());

                final String property = name.substring(0, 1).toLowerCase() + name.substring(1);
                propertyPath.add(property);
                System.out.println(property);
                return Modifier.isFinal(m.getReturnType().getModifiers()) ? null : getWrapperObject(m.getReturnType());
            }
        };
        T object = (T) c.newInstance();
        ((ProxyObject) object).setHandler(methodHandler);
        return object;
    }

    private void clearPropertyName() {
        propertyPath.clear();
        propertyPath.add("object");
    }

    public DetachedCriteria getCriteria() {
        return detachedCriteria;
    }

    protected void where(Criterion criterion) {
        detachedCriteria = detachedCriteria.add(criterion);
    }

    protected <V> Criterion eq(V objectValue, V expected) {
        final String propertyName = createAliasesAndGetPropertyName();
        clearPropertyName();
        return Restrictions.eq(propertyName, expected);
    }

    private String createAliasesAndGetPropertyName() {
        /*
        for object.child.title we need alias only for object.child -> child
         */
        for (int i = 0; i < propertyPath.size() - 2; i++) {
            String property = propertyPath.get(i) + "." + propertyPath.get(i + 1);
            if (aliases.get(property) == null) {
                String alias = propertyPath.get(i + 1);
                int counter = 1;
                while (aliases.containsValue(alias)) {
                    alias = propertyPath.get(i) + counter;
                    counter++;
                }
                detachedCriteria = detachedCriteria.createAlias(property, alias);
                aliases.put(property, alias);
                propertyPath.set(i + 1, alias);
            }
        }
        return propertyPath.get(propertyPath.size() - 2) + "." + propertyPath.get(propertyPath.size() - 1);
    }

    protected Criterion or(Criterion first, Criterion second) {
        return Restrictions.or(first, second);
    }
}
