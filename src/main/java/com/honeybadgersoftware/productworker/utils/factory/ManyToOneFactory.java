package com.honeybadgersoftware.productworker.utils.factory;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public interface ManyToOneFactory<T, R, S> {

    S map(T t, R r);

    default List<S> map(
            List<T> t,
            List<R> r,
            BiPredicate<T, R> matchCriterion) {
        return t.stream().flatMap(
                        o1 -> r.stream()
                                .filter(o2 -> matchCriterion.test(o1, o2))
                                .map(o2 -> map(o1, o2)))
                .collect(Collectors.toList());

    }
}
