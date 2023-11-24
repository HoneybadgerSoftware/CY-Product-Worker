package com.honeybadgersoftware.productworker.factory.context;

import com.honeybadgersoftware.productworker.utils.factory.ManyToOneFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FactoryContext {

    private final Map<Class<?>, ManyToOneFactory<?, ?, ?>> factories;

    public FactoryContext(Map<Class<?>, ManyToOneFactory<?, ?, ?>> factories) {
        this.factories = factories;
    }

    @SuppressWarnings("unchecked")
    public <T, R, S> ManyToOneFactory<T, R, S> getFactory(Class<S> type) {
        return (ManyToOneFactory<T, R, S>) factories.get(type);
    }
}

