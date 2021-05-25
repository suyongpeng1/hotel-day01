package cn.kgc.demo.service.impl;

import cn.kgc.demo.dao.BaseMapper;
import cn.kgc.demo.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO 公共信息实现类
 * @Author xiaosu
 * @Date 2021/5/6 10:56
 * @Version 1.0
 */
//注意：BaseServiceImpl不能添加@Service注解，因为没有指定具体的实现类型，启动报错。
public class BaseServiceImpl<T> implements BaseService<T> {

    /*引入基本公共的Mapper代理对象*/
    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public Map<String, Object> findTByPage(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        //1.开启分页查询
        PageHelper.startPage(page, limit);
        //2.查询数据
        // 嵌套查询 - 懒加载
        List<T> list = baseMapper.selAllTByPage();
        //3.封装数据
        PageInfo pageInfo = new PageInfo(list);
        //注意：count的名称必须跟前端layui要求的返回格式完全一致
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @Override
    public Map<String, Object> findTByPageAndParams(Integer page, Integer limit, T t) {
        Map<String, Object> map = new HashMap<>();
        //1.开启分页查询
        PageHelper.startPage(page, limit);
        //2.查询数据
        // 嵌套查询 - 懒加载
        List<T> list = baseMapper.selAllTByPageParams(t);
        //3.封装数据
        PageInfo pageInfo = new PageInfo(list);
        //注意：count的名称必须跟前端layui要求的返回格式完全一致
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @Override
    public String removeTById(Integer id) {
        if(baseMapper.deleteByPrimaryKey(id) > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String removeBatchTByIds(Integer[] ids) {
        if(baseMapper.delBatchTByIds(ids) > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String saveT(T t) {
        if(baseMapper.insert(t) > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String modifyT(T t) {
        if(baseMapper.updateByPrimaryKeySelective(t) > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public List<T> findTAll() {
        return baseMapper.selTAll();
    }

    @Override
    public T findTByParams(T t){
        return baseMapper.selTByParams(t);
    }


    @Override
    public List<T> findManyByParams(T t) {
        return baseMapper.selManyByParams(t);
    }

    @Override
    public String modifyBatchTByIds(Integer[] ids, T t) {
        if(baseMapper.updBatchTByIds(ids, t) > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public Long findCountByParams(T t) {
        return baseMapper.selCountByParams(t);
    }
}
