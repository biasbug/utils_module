package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import utils.UploadUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    /**
     *
     * @param fileupload 上传的文件
     * @param request 客户端请求
     * @return
     */
    @RequestMapping("/fileupload")
    public String fileUpload(MultipartFile fileupload,HttpServletRequest request){
        String realPath = UploadUtil.getRealPath(request, "upload");//获取系统绝对路径
        String filename = UploadUtil.getUUIDName(fileupload.getOriginalFilename());//获取唯一文件名
        try {
            fileupload.transferTo(new File(realPath,filename));//上传文件
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    /**
     *
     * @param dirupload 上传的文件夹
     * @param request
     * @return
     */
    @RequestMapping("/dirupload")
    public String dirUpload(MultipartFile[] dirupload,HttpServletRequest request){
        String realPath = UploadUtil.getRealPath(request, "upload");//获取系统绝对路径
        String uuidName = UploadUtil.getUUIDName();
        for (MultipartFile multipartFile : dirupload) {
            CommonsMultipartFile cmf = (CommonsMultipartFile)multipartFile;//转换成CommonsMultipartFile以便获取目录结构
            String pathName = uuidName+"-"+cmf.getFileItem().getName();//获取目录结构，并在重写命名根目录名称，确保根目录名唯一
            System.out.println(pathName);
            File f1 = new File(realPath, pathName);
            try {
                if(!f1.exists()){
                    f1.mkdirs();
                    f1.createNewFile();//创建目录和文件
                }
                cmf.transferTo(f1);//上传文件
            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }
        }
        return "success";
    }

}
