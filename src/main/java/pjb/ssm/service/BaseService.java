package pjb.ssm.service;

import java.util.List;

/**
 * 创建一个BaseService接口，用泛型解耦
 * Created by dell1 on 2017/7/8.
 */
public interface BaseService<T> {
    void add(T t) throws Exception;
}
