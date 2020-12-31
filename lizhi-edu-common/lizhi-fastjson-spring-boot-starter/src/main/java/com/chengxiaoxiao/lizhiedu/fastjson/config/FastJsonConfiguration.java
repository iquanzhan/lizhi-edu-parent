package com.chengxiaoxiao.lizhiedu.fastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * FastJson配置信息
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/27 9:34
 * @Version 1.0
 */
public class FastJsonConfiguration {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.DisableCircularReferenceDetect);
        config.setCharset(Charset.forName("UTF-8"));
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConverter.setFastJsonConfig(config);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(list);

        return new HttpMessageConverters(fastJsonConverter);
    }
}
