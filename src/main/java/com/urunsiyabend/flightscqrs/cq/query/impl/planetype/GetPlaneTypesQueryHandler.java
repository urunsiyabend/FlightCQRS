package com.urunsiyabend.flightscqrs.cq.query.impl.planetype;

import com.urunsiyabend.flightscqrs.cq.query.common.QueryHandler;
import com.urunsiyabend.flightscqrs.data.elastic.service.ElasticSearchService;
import com.urunsiyabend.flightscqrs.entities.PlaneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPlaneTypesQueryHandler implements QueryHandler<GetPlaneTypesQuery, List<PlaneType>> {

    private final ElasticSearchService elasticSearchService;

    @Autowired
    public GetPlaneTypesQueryHandler(ElasticSearchService elasticsearchService) {
        this.elasticSearchService = elasticsearchService;
    }

    @Override
    public List<PlaneType> handle(GetPlaneTypesQuery query) throws Exception {
        return elasticSearchService.search("plane-type", PlaneType.class);
    }
}