package com.mzlxl.jdbc.client;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author mzlxl
 * @date 2022/04/06
 **/
public class EsClient extends AwesomeJdbcClient<RestHighLevelClient> {

    public EsClient(RestHighLevelClient client) {
        super(client);
    }
}
