package com.mzlxl.jdbc.config.es;

import cn.hutool.core.util.NumberUtil;
import com.mzlxl.jdbc.client.EsClient;
import com.mzlxl.jdbc.constants.Constants;
import com.mzlxl.jdbc.exception.AwesomeJdbcConfigException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author mzlxl
 * @date 2022/04/06
 **/
@Configuration
@ConditionalOnProperty(prefix = Constants.ES_JDBC)
@EnableConfigurationProperties(EsJdbcProperties.class)
public class EsJdbcAutoConfiguration {

    @Bean
    public RestHighLevelClient restHighLevelClient(EsJdbcProperties properties) {
        if (CollectionUtils.isEmpty(properties.getHosts())) {
            throw new AwesomeJdbcConfigException("es properties config error");
        }
        List<HttpHost> hosts = properties.getHosts().stream().map(this::buildHttpHost).collect(Collectors.toList());
        RestClientBuilder restClientBuilder = RestClient.builder(hosts.toArray(new HttpHost[]{}))
            .setRequestConfigCallback(builder -> {
                builder.setConnectTimeout(properties.getConnectTimeOutMillis());
                builder.setSocketTimeout(properties.getSocketTimeOutMillis());
                builder.setConnectionRequestTimeout(properties.getRequestTimeOutMillis());
                return builder;
            });

        if (!StringUtils.isEmpty(properties.getUsername()) && !StringUtils.isEmpty(properties.getPassword())) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
            restClientBuilder.setHttpClientConfigCallback(it -> it.setDefaultCredentialsProvider(provider));
        }

        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost buildHttpHost(String host) {
        String[] hostSplitArr = host.split(":");
        if (hostSplitArr.length != Constants.TWO || !NumberUtil.isInteger(hostSplitArr[1])) {
            throw new AwesomeJdbcConfigException("es hosts config error");
        }
        return new HttpHost(hostSplitArr[0], Integer.parseInt(hostSplitArr[1]));
    }

    @Bean
    public EsClient esClient(RestHighLevelClient restHighLevelClient) {
        return new EsClient(restHighLevelClient);
    }

}
