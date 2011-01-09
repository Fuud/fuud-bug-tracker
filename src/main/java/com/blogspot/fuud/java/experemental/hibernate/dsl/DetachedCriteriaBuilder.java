package com.blogspot.fuud.java.experemental.hibernate.dsl;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Method;

public class DetachedCriteriaBuilder<T> {
    private DetachedCriteria detachedCriteria;
    private Class<T> clazz;
    protected T object;
    private String propertyName;
    private Object expectedValue;

    public DetachedCriteriaBuilder(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        detachedCriteria = DetachedCriteria.forClass(clazz, "object");
        propertyName = "object";

        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(clazz);
        f.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return m.getParameterTypes().length == 0
                        && (m.getName().startsWith("get"));
            }
        });
        Class c = f.createClass();
        MethodHandler methodHandler = new MethodHandler() {
            public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
                final String name = m.getName().substring("get".length());

                propertyName += "." + name.substring(0, 1).toLowerCase() + name.substring(1);
                System.out.println(propertyName);
                return proceed.invoke(self, args);  // execute the original method.
            }
        };
        object = (T) c.newInstance();
        ((ProxyObject) object).setHandler(methodHandler);
    }

    public DetachedCriteria getCriteria() {
        return detachedCriteria;
    }

    protected void where(Criterion criterion) {
        detachedCriteria = detachedCriteria.add(Restrictions.eq(propertyName, expectedValue));
    }

    protected <V> Criterion eq(V objectValue, V expected) {
        expectedValue = expected;
        return null;
    }


    protected interface Criterion {
    }
}
