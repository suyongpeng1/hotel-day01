package cn.kgc.demo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BaseMapper
 * @Description TODO 基础公共Mapper代理对象接口
 *  T : 表示实体的泛型
 * @Author xiaosu
 * @Date 2021/5/6 10:18
 * @Version 1.0
 */
public interface BaseMapper<T> {

    /*根据主键id删除单个数据*/
    int deleteByPrimaryKey(Integer id);

    /*插入单条数据*/
    int insert(T t);
    /*动态插入单条数据*/
    int insertSelective(T t);

    /*根据主键id查询单个数据*/
    T selectByPrimaryKey(Integer id);

    /*根据单个实体，动态修改数据*/

    int updateByPrimaryKeySelective(T t);

    /*根据单个实体，修改数据*/
    int updateByPrimaryKey(T t);

    //嵌套查询 - 分页查询所有数据
    List<T> selAllTByPage();

    //嵌套查询 - 根据条件分页查询数据
    List<T> selAllTByPageParams(T t);

    // 根据多个主键id批量删除数据
    int delBatchTByIds(@Param("ids") Integer[] ids);

    //查询所有的数据
    List<T> selTAll();

    //根据条件查询单个数据
    T selTByParams(T t);

    //根据条件查询多条数据
    List<T> selManyByParams(T t);


    //根据条件批量修改数据
    //如果参数是2个以上，那么必须要给参数添加注解，否则Mybatis无法获取参数名称！
    int updBatchTByIds(@Param("ids") Integer[] ids, @Param("t") T t);

    //根据条件查询数据的个数
    Long selCountByParams(T t);
}
