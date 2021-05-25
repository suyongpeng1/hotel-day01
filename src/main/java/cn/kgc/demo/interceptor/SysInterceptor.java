package cn.kgc.demo.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SysInterceptor
 * @Description TODO 自定义拦截器类
 * @Author xiaosu
 * @Date 2021/5/20 19:42
 * @Version 1.0
 */
public class SysInterceptor extends HandlerInterceptorAdapter {

    //拦截器的核心方法
    //该方法，在请求到达控制器之前就会被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...拦截器的核心方法...在请求到达控制器之前就会被调用");
        //1.得到session保存的用户数据
        Object loginUser = request.getSession().getAttribute("loginUser");
        //2.判断该用户数据是否存在
        if(loginUser!=null){
            //3.如果存在 - 放行
            return true;
        }else{
            //添加提示信息
            request.setAttribute("loginUIMsg","loginUIMsg");
            //返回到登录页面
            //转发
            request.getRequestDispatcher("/model/toLoginUI").forward(request,response);
            //4.如果不存在 - 不放行，请求返回
            return false;
        }
    }

    //该方法，在控制器调用完成之后，执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...该方法，在控制器调用完成之后，执行...");
    }

    //该方法，在调用完毕，清空资源的时候，执行。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...该方法，在调用完毕，清空资源的时候，执行。");
    }
}
