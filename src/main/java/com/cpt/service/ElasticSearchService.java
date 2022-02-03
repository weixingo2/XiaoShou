package com.cpt.service;

import com.alibaba.fastjson.JSON;
import com.cpt.entity.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ElasticSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @Autowired
    private UserService userService;

    public Boolean parseContent(String keywords) throws Exception{

        List<User> list=userService.selectUserList();

        //把数据放入到es中
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("2m");

        for(int i=0;i<list.size();i++){

            bulkRequest.add(new IndexRequest("page_user")
                                .source(JSON.toJSONString(list.get(i)), XContentType.JSON));
        }

        BulkResponse bulkResponse=restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulkResponse.hasFailures();
    }

    //获取这些数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {

        if(pageNo<=1){
            pageNo=1;
        }
        //条件搜索
        SearchRequest searchRequest=new SearchRequest("page_user");

        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);

        sourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder title= QueryBuilders.termQuery("username","admin");

        sourceBuilder.query(title);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行分页
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果
        ArrayList<Map<String,Object>> list=new ArrayList<>();
        for(SearchHit documentFields:searchResponse.getHits().getHits()){

            list.add(documentFields.getSourceAsMap());
        }
                return list;
    }


    public Map<String,Object> searchPage1() throws IOException {
        SearchRequest searchRequest=new SearchRequest();
        searchRequest.types("page_user");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
//        QueryBuilder queryBuilder=new MatchQueryBuilder("username","admin10");
        QueryBuilder queryBuilder=QueryBuilders.matchQuery("username","admin1");

        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
       SearchResponse searchResponse=restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);

       SearchHits searchHits=searchResponse.getHits();

       System.out.println("记录:"+searchHits.getTotalHits());

       SearchHit[] hits=searchHits.getHits();

        Map<String,Object> map=new HashMap<>();

       for(SearchHit documentFields:hits){

            map=documentFields.getSourceAsMap();

           System.out.println(map);
       }

return map;
    }
}
