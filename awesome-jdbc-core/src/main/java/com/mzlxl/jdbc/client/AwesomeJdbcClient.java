package com.mzlxl.jdbc.client;

import lombok.Data;

/**
 * @author mzlxl
 * @date 2022/04/06
 **/
@Data
public abstract class AwesomeJdbcClient<T> {

    private T client;

    public AwesomeJdbcClient(T client) {
        this.client = client;
    }

}
