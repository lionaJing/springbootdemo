package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注意： 因为开启了自动驼峰命名(从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射)
 * 所以注意实体类属性的命名要和数据库一致(包括实体类名)
 *
 *  swagger2 集成，访问路径： http://localhost:8080/swagger-ui.htm
 *  相关常用注解解释：
 *  -@Api               修饰整个类，描述Controller的作用
 *  -@ApiOperation      描述一个类的一个方法，或者说一个接口
 *  -@ApiParam          单个参数描述
 *  -@ApiModel          用对象来接收参数
 *  -@ApiProperty       用对象接收参数时，描述对象的一个字段
 *  -@ApiImplicitParam  用于方法，表示单独的请求参数
 *  -@ApiImplicitParams 用于方法，包含多个 @ApiImplicitParam
 *
 * @author Shuai.Jing
 * @date 2019/5/28
 */
@SpringBootApplication
public class ApplicationMyBatisPlus {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMyBatisPlus.class, args);
    }
}
