package cn.kgc.demo.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author xiaosu
 * @Date 2021/5/17  15:06
 * TODO 七牛云上传文件的工具类
 */
public class QiniuUploadUtils {
    //设置好账号的ACCESS_KEY和SECRET_KEY;这两个登录七牛账号里面可以找到
    public static String ACCESS_KEY = "jw1jci6MOwBsRXRdqlngpGg8CKpR8toxIrAY-0dX";
    public static String SECRET_KEY = "nvq5juyQb7KmWNwZowHhTGCDtfKVjVTcUFJvSI-m";
    //要上传的空间;对应到七牛上（自己建文件夹 注意设置公开）
    public static String bucketname = "soframe2";
    //上传文件的路径 ;本地要上传文件路径
    //String FilePath = "E:\\img\\renwu004.jpg";
    //密钥配置
    public static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    //zone2(): 表示对应就是华南区
    public static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    //普通上传
    public static Map<String,Object> upload(MultipartFile myFile) throws IOException {
        Map<String,Object> map = new HashMap<>();
        try {
            //上传到七牛后保存的文件名
            //myFile.getOriginalFilename() : 得到上传的文件的名称  123.jpg
            //FilenameUtils.getExtension(...) : 得到上传文件的名称的后缀  jpg
            //xxxx.jpg
            String key = UUID.randomUUID().toString().replace("-", "")+
                    "."+ FilenameUtils.getExtension(myFile.getOriginalFilename());
            //调用put方法上传
            //注意：这里使用  FilePath 路径来上传图片，但是以后接入到项目中时，会使用字节数组的方式直接上传图片
            //res : 表示图片上传之后，返回的对象
            //res 会返回 hash 和 key
            //key : 就是上传图片之后，返回的图片的名称
            //myFile : 就是文件上传的对象
            Response res = uploadManager.put(myFile.getBytes(), key, getUpToken());
            map.put("code",0); //向map中装入上传的状态，成功
            //回显上传文件名称，保存到数据库去
            //res.jsonToMap().get("key"): 表示先把json格式转换为map对象，然后通过key获取key的值
            map.put("newFileName",res.jsonToMap().get("key")); //向map中装入上传的目标文件名
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            map.put("code",200); //向map中装入上传的状态，异常
        }
        return map;
    }
}
