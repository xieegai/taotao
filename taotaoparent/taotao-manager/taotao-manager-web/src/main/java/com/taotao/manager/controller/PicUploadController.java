package com.taotao.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.PicUploadResult;
import com.taotao.manager.service.PropertieService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * @author zjj
 * @date 18-11-3 13:48
 */
@Controller
@RequestMapping("pic")
public class PicUploadController {

    private static final Logger logger = LoggerFactory.getLogger(PicUploadController.class);

    @Autowired
    private PropertieService propertieService;

    private static final ObjectMapper mapper = new ObjectMapper();

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] {".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam("uploadFile")MultipartFile uploadFile, HttpServletResponse response) throws Exception{

        // 格式校验
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)){
                isLegal = true;
                break;
            }
        }

        // 封装结果对象，讲文件的byte数组放置到result对象中
        PicUploadResult result = new PicUploadResult();

        // 状态
        result.setError(isLegal ? 0 : 1);


        // 文件新路径
        String filePath =  getFilePath(uploadFile.getOriginalFilename());

        if (logger.isInfoEnabled()){
            logger.debug("pic file upload . [{}] to [{}] .", uploadFile.getOriginalFilename(), filePath);
        }

        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertieService.REPOSITORY_PATH),"\\","/");
        result.setUrl(propertieService.IMAGE_BASE_URL + picUrl);

        File newFile = new File(filePath);

        // 写文件到磁盘
        uploadFile.transferTo(newFile);

        // 检验图片是否合法，通过宽和高验证是否是图片
        isLegal = false;
        try {
            BufferedImage image = ImageIO.read(newFile);
            if (image != null) {
                result.setWidth(image.getWidth() + "");
                result.setHeight(image.getHeight() + "");
                isLegal = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 状态
        result.setError(isLegal ? 0 : 1);

        // 不合法将文件删除
        if (!isLegal){
            newFile.delete();
        }

        // 设置响应类型
        response.setContentType(MediaType.TEXT_HTML_VALUE);

        // 以对象转换为JSON字符串
        return mapper.writeValueAsString(result);
    }

    private String getFilePath(String originalFilename) {
        String baseFloder = propertieService.REPOSITORY_PATH + File.separator + "images";
        Date now = new Date();
        // yyyy/MM/dd
        String fileFloder = baseFloder
                + File.separator + new DateTime(now).toString("yyyy")
                + File.separator + new DateTime(now).toString("MM")
                + File.separator + new DateTime(now).toString("dd");
        File file = new File(fileFloder);
        if (!file.isDirectory()){
            file.mkdirs();
        }

        // 生成新的文件名
        String fileName = new DateTime(now).toString("yyyyMMddHHmmssSSSS")
                + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(originalFilename, ".");

        return fileFloder + File.separator + fileName;
    }

}
