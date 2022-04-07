package com.mzlxl.jdbc.config.es;

import com.mzlxl.jdbc.constants.Constants;
import java.util.List;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mzlxl
 * @date 2022/04/06
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = Constants.ES_JDBC)
public class EsJdbcProperties {

    private List<String> hosts;
    private String username;
    private String password;
    @Default
    private Integer maxScrollSize = 30000;
    @Default
    private Integer scrollPageSize = 1000;
    @Default
    private Integer connectTimeOutMillis = 4000;
    @Default
    private Integer socketTimeOutMillis = 30000;
    @Default
    private Integer requestTimeOutMillis = 2000;

}
