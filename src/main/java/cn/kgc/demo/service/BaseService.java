package cn.kgc.demo.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Description TODO 基础公共业务层接口
 * @Author xiaosu
 * @Date 2021/5/6 10:49
 * @Version 1.0
 */
public interface BaseService<T> {

    //分页查询数据
    Map<String, Object> findTByPage(Integer page, Integer limit);

    //根据条件进行分页查询数据
    Map<String, Object> findTByPageAndParams(Integer page, Integer limit, T t);

    //根据主键id删除信息
    String removeTById(Integer id);

    // 根据多个主键id批量删除数据
    String removeBatchTByIds(Integer[] ids);

    //保存数据
    String saveT(T t);

    //动态修改数据
    String modifyT(T t);

    //查询所有的数据
    List<T> findTAll();

    ////根据条件查询单个数据
    T findTByParams(T t);

    //根据条件查询多条数据
    List<T> findManyByParams(T t);

    //根据条件批量修改数据
    String modifyBatchTByIds(Integer[] ids, T t);

    //根据条件查询数据的个数
    Long findCountByParams(T t);
}
