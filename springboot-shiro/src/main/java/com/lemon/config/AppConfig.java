package com.lemon.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * configurePathMatch：配置路由请求规则
 * configureContentNegotiation：内容协商配置
 * configureDefaultServletHandling：默认静态资源处理器
 * addFormatters：注册自定义转化器
 * addInterceptors：拦截器配置
 * addResourceHandlers：资源处理
 * addCorsMappings：CORS配置
 * addViewControllers：视图跳转控制器
 * configureViewResolvers：配置视图解析
 * addArgumentResolvers：添加自定义方法参数处理器
 * addReturnValueHandlers：添加自定义返回结果处理器
 * configureMessageConverters：配置消息转换器。重载会覆盖默认注册的HttpMessageConverter
 * extendMessageConverters：配置消息转换器。仅添加一个自定义的HttpMessageConverter.
 * configureHandlerExceptionResolvers：配置异常转换器
 * extendHandlerExceptionResolvers：添加异常转化器
 *
 * @author Shuai.Jing
 * @date 2019/4/10
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    /**
     * 配置 FastJson 为返回 json 的转化器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
        FastJsonConfig fj = new FastJsonConfig();
        //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        //List字段如果为null,输出为[],而非null
        fj.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
        //字符类型字段如果为null,输出为"",而非null
        fj.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        //Boolean字段如果为null,输出为false,而非null
        fj.setSerializerFeatures(SerializerFeature.WriteNullBooleanAsFalse);
        //是否输出值为null的字段,默认为false
        fj.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        fjc.setFastJsonConfig(fj);
        converters.add(fjc);
    }
}
