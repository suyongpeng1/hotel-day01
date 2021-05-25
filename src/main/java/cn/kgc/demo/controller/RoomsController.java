package cn.kgc.demo.controller;

import cn.kgc.demo.pojo.Rooms;
import cn.kgc.demo.utils.QiniuUploadUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO 客房信息的控制器

@Controller
@RequestMapping("rooms")
public class RoomsController extends BaseController<Rooms> {

    //客房图片上传功能
    //上传完成之后，应该返回上传文件的名称，用来保存到数据库
    //返回code：状态码
    //myFile: 表示文件上传的对象
    //path :文件上传的路径
    @RequestMapping("uploadRoomPic")
    @ResponseBody
    public Map<String,Object> uploadRoomPic(String path, MultipartFile myFile){
        System.out.println("path = " + path);
        System.out.println("myFile = " + myFile);
        //创建map
        Map<String,Object> map = new HashMap<>();
        /**************文件上传开始******************/
        try {
            return QiniuUploadUtils.upload(myFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        /*try{
            //---修改文件名称
            //得到文件名称，包括后缀
            //123.jpg
            String oldFileName = myFile.getOriginalFilename();
            System.out.println("oldFileName:"+oldFileName);
            //得到文件的后缀 jpg
            //证件照：照片的格式：jpg jpeg png pneg bmp
            String prefixPath = FilenameUtils.getExtension(oldFileName);

            //得到新文件名
            String newFileName = UUID.randomUUID().toString().replace("-","") +"."+prefixPath;
            //创建路径，上传文件
            File file = new File(path,newFileName);
            //判断该路径是否存在
            if(!file.exists()){
                //创建路径
                file.mkdirs();
            }
           //文件上传方法
            myFile.transferTo(file);
            map.put("code",0); //向map中装入上传的状态，成功
            //回显上传文件名称，保存到数据库去
            map.put("newFileName",newFileName); //向map中装入上传的目标文件名
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",200); //向map中装入上传的状态，异常
        }*/
            /**************文件上传结束******************/
        }
    }
}
