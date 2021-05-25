package cn.kgc.demo.controller;

import cn.kgc.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Description TODO 基础公共控制器
 * @Author xiaosu
 * @Date 2021/5/6 11:43
 * @Version 1.0
 */
//注意：BaseController不能添加@Controller注解，因为没有指定具体的类型，启动报错。
public class BaseController<T> {

    @Autowired
    private BaseService<T> baseService;

    /**
     * 异步请求加载数据
     * @param page  当前页码
     * @param limit 每页条数
     * @return  layui所需要的封装的数据
     */
    @RequestMapping("loadData")
    @ResponseBody  //只返回数据JSON格式
    public Map<String,Object> loadData(Integer page, Integer limit){
        System.out.println("page = " + page);
        System.out.println("limit = " + limit);
        Map<String,Object> map = null;
        try{
            //分页查询数据
            map = baseService.findTByPage(page, limit);
            map.put("code", 0); //设置状态码：0 表示请求返回成功
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", 200); //设置状态码：200 表示请求返回失败
            map.put("msg","数据访问失败！");
        }
        return map;
    }


    /**
     * 通过查询条件分页查询数据
     * @param page 当前页码
     * @param limit 每页条数
     * @param t 封装了查询条件
     * @return
     * */

    @RequestMapping("loadDataByParams")
    @ResponseBody  //只返回数据JSON格式
    public Map<String,Object> loadDataByParams(Integer page, Integer limit, T t){
        System.out.println("page = " + page);
        System.out.println("limit = " + limit);
        Map<String,Object> map = null;
        try{
            //分页查询数据
            map = baseService.findTByPageAndParams(page, limit, t);
            map.put("code", 0); //设置状态码：0 表示请求返回成功
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", 200); //设置状态码：200 表示请求返回失败
            map.put("msg","数据访问失败！");
        }
        return map;
    }

    //根据主键id删除单个数据
    @RequestMapping("delTById")
    @ResponseBody
    public String delTById(Integer id){
        try{
            return baseService.removeTById(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //根据多个主键Id删除数据
    @RequestMapping("delBatchTByIds")
    @ResponseBody
    public String delBatchTByIds(Integer[] ids){
        try{
            return baseService.removeBatchTByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //添加单个数据
    @RequestMapping("saveT")
    @ResponseBody
    public String saveT(T t){
        try{
            return baseService.saveT(t);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //动态修改数据
    @RequestMapping("updT")
    @ResponseBody
    public String updT(T t){
        try{
            return baseService.modifyT(t);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //根据条件批量修改数据
    @RequestMapping("updBatchTByIds")
    @ResponseBody
    public String updBatchTByIds(Integer[] ids, T t){
        try{
            return baseService.modifyBatchTByIds(ids, t);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //异步请求加载所有数据
    @RequestMapping("loadAllT")
    @ResponseBody
    public List<T> loadAllT(){
        try{
            return baseService.findTAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //根据条件查询单个数据
    @RequestMapping("loadTByParams")
    @ResponseBody
    public T loadTByParams(T t){
        try {
            return baseService.findTByParams(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //根据条件查询多条数据
    @RequestMapping("loadManyByParams")
    @ResponseBody
    public List<T> loadManyByParams(T t){
        try{
            return baseService.findManyByParams(t);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //根据条件查询数据的个数
    @RequestMapping("getCountByParams")
    @ResponseBody
    public Long getCountByParams(T t){
        try{
            return baseService.findCountByParams(t);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}







