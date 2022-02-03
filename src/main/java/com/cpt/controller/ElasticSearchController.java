package com.cpt.controller;

import com.cpt.service.ElasticSearchService;



import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ElasticSearchController {


    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping("/get/{keyword}")
    public Boolean parse(@PathVariable("keyword")String keyword) throws Exception {

        return elasticSearchService.parseContent(keyword);
    }


    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable("keyword")String keyword,@PathVariable("pageNo")Integer pageNo,@PathVariable("pageSize")Integer pageSize) throws IOException {
        return elasticSearchService.searchPage(keyword,pageNo,pageSize);

    }

    @GetMapping("/get")
    public Map<String,Object>  search1() throws IOException {
        return elasticSearchService.searchPage1();

    }






}
