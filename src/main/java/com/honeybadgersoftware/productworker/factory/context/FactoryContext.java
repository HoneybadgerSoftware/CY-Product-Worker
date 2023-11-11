package com.honeybadgersoftware.productworker.factory.context;

import com.honeybadgersoftware.productworker.utils.ManyToOneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FactoryContext {

    private final Map<Class<?>, ManyToOneFactory<?, ?, ?>> factories;

    @Autowired
    public FactoryContext(Map<Class<?>, ManyToOneFactory<?, ?, ?>> factories) {
        this.factories = factories;
    }

    @SuppressWarnings("unchecked")
    public <T, R, S> ManyToOneFactory<T, R, S> getFactory(Class<S> type) {
        return (ManyToOneFactory<T, R, S>) factories.get(type);
    }
}
