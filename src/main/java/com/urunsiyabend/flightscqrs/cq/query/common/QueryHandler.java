package com.urunsiyabend.flightscqrs.cq.query.common;

public interface QueryHandler<TQuery extends Query, TResult> {
    TResult handle(TQuery query) throws Exception;
}
