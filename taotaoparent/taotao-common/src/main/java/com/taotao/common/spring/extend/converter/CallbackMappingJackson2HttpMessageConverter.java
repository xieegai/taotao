package com.taotao.common.spring.extend.converter;


import com.fasterxml.jackson.core.JsonEncoding;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 扩展@ResponseBody注解，统一处理，解决JSONP跨域问题
 * 重写spring默认的消息转换器，如果存在回调方法，则拼接字符串
 *
 * @author zjj
 * @date 2018/11/25 18:40
 */
public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    // 做jsonp的支持标记，也就是回调函数名
    private String callbackName;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        // 从threadLocal中获取当前request对象
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String callBackParam = request.getParameter(this.callbackName);

        if (StringUtils.isEmpty(callBackParam)){
            // 回调函数为空时，则走父类默认转换器，直接返回json数据
            super.writeInternal(object, outputMessage);
        } else {
            // 否则，拼接回调函数名
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());

            try {
                String result = callBackParam + "(" + super.getObjectMapper().writeValueAsString(object) + ");";

                IOUtils.write(result, outputMessage.getBody(), encoding.getJavaName());
            } catch (IOException e) {
                throw new HttpMessageNotWritableException("could not write JSON:" + e.getMessage(), e);
            }
        }
    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }
}
