立志在线学习系统，是基于java+vue开发的在线教育平台，将开发PC、小程序、手机端，集成RABC权限+在线考试+文档预览+视频播放+代码生成器等功能。目标是基于此项目可以巩固学习spring boot、vue、Mybatis等技术，欢迎star哟~~

# 一、技术架构

版本控制：git

依赖管理：maven

接口文档：Swagger

权限验证：Spring Security

数据库：MySql、Druid连接池

数据访问层：Mybatis、Mybatis-Plus 3.1.0

框架：Spring Boot 2.2.6.RELEASE、Spring Cloud Hoxton.SR6、Spring Cloud Alibaba 2.2.0.RELEASE

工具类：commons-lang3、lombok、hutool工具类、swagger、jwt、oshi-core（系统监控框架）、UserAgentUtils

# 二、开发规范

请参考 ：

1.[开发规范](./lizhi-edu-doc/规范/开发规范.md)

2.MyBatis Plus手册：https://mp.baomidou.com/

# 三、工程结构

```
lizhi-edu-parent
│
├─lizhi-edu-admin-server  Spring Boot Admin监控服务端
│
├─lizhi-edu-auth  评估服务
│  ├─api 文档接口
│  ├─common 公共包
│  ├─config 配置包
│  ├─controller 控制器
│  ├─dto 数据传输对象
│  │  ├─query 数据查询对象
│  │  ├─vo 数据展示对象
│  ├─entity 数据库对象
│  ├─mapper Mybatis Mapper
│  ├─service 业务逻辑层
│  │  ├─impl 具体实现类
│  ├─AssessApplication Spring Boot启动器
│
│
├─lizhi-edu-common  公共模块
│  ├─lizhi-edu-common-core 公共核心模块
│  ├─lizhi-database-spring-boot-starter 数据库启动器
│  ├─lizhi-log-spring-boot-starter 日志启动器
│  ├─lizhi-security-spring-boot-starter 权限启动器
│  ├─lizhi-swagger-spring-boot-starter 接口文档启动器
│
├─lizhi-edu-dto  公共数据传输对象
│
├─lizhi-edu-gateway  网关
├─lizhi-edu-generator  代码生成器
│
├─lizhi-edu-rpc  调用第三方rpc接口
```

# 四、公共模块使用

## 1.lizhi-database-spring-boot-starter 数据库模块

### 1.1基本介绍

lizhi-database-spring-boot-starter提供了对MySql数据库的访问及监控能力，内置Mybatis Plus，及Druid连接池，并为其提供了默认的优化配置，开发者无需了解具体的实现细节，只需引入此模块便可使用Mybatis Plus和Druid的高级特性和功能。

#### 功能预览

1.逻辑删除

2.乐观锁控制

3.字段填充

4.Druid连接池监控、优化

### 1.2使用

#### 1）导入依赖

在pom.xml文件中导入以下依赖

```maven
        <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-database-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）配置文件中添加配置信息

##### 1.数据库配置

可在配置文件中配置数据源信息。

```yml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/database?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8
      username: root
      password: 123456789
```

##### 2.Druid连接池配置

Druid监控界面用户名密码可通过以下进行配置。默认情况下用户：admin，密码：admin

```yml
lizhi:
  database:
    #Druid连接池配置
    druid:
      #登录用户名
      user-name: admin
      #登录密码
      password: admin
```

同时，Druid连接池已默认增加如下默认配置，也可在配置文件中覆盖。

```yml
spring:
  datasource:
    druid:
      # 初始化物理连接个数
      initial-size: 1
      # 最大连接池数量
      max-active: 20
      # 最小连接池数量
      min-idle: 5
      # 获取连接时最大等待时间(ms)
      max-wait: 60000
      # 开启缓存preparedStatement(PSCache)
      pool-prepared-statements: true
      # 启用PSCache后，指定每个连接上PSCache的大小
      max-pool-prepared-statement-per-connection-size: 20
      # 用来检测连接是否有效的sql
      validation-query: select 'x'
      # 申请连接时不检测连接是否有效
      test-on-borrow: false
      # 归还连接时不检测连接是否有效
      test-on-return: false
      # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）
      test-while-idle: true
      # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接
      time-between-eviction-runs-millis: 60000
      # 连接保持空闲而不被驱逐的最小时间(ms)
      min-evictable-idle-time-millis: 300000
      # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall
      filters: stat,log4j,wall
      # 支持合并多个DruidDataSource的监控数据
      use-global-data-source-stat: true
      # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
```

##### 3.逻辑删除、创建时间、修改时间、版本号

模块内置对逻辑删除、创建时间、更新时间、版本的支持。并提供对其进行完全控制。

以下为默认值，可在配置文件中将其覆盖。

```yml
lizhi:
  database:
    config:
      #是否开启逻辑删除
      enable-logic-delete: true
      #标识逻辑删除的字段，此处用驼峰
      logic-delete-field: deleteStatus
      #标识未删除的值
      logic-not-delete-value: 0
      #标识删除的值
      logic-delete-value: 1
      #是否开启创建时间填充
      enable-create-time: true
      #标识创建时间的字段，此处用驼峰
      create-time-field: createTime
      #是否开启更新时间填充
      enable-update-time: true
      #标识更新时间的字段，此处用驼峰
      update-time-field: updateTime
      #是否开启乐观锁版本号填充
      enable-version: true
      #标识乐观锁版本号的字段，此处用驼峰
      version-field: version
```

### 3）Druid监控页面

在浏览器输入网址：http://${ip}:${port}/druid/ 。即可进入Druid登录界面。输入配置的用户名和密码即可登录。

可在界面查看慢SQL、访问统计、SQL监控等功能。

## 2.lizhi-log-spring-boot-starter 日志模块

### 2.1基本介绍

lizhi-log-spring-boot-starter提供了对系统日志及操作日志门面。开发者可使用其内置注解，监听所有的接口请求。便于操作追溯及问题复现。

功能预览

1.系统日志的控制

2.操作日志的控制

### 2.2使用

#### 1）导入依赖

在pom.xml文件中导入以下依赖

```maven
        <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-log-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）配置文件中添加配置信息

##### 1.开关配置

可通过以下配置修改日志的开启与关闭。默认情况下操作日志及HTTP请求日志为开启状态

```yml
lizhi:
  #日志配置
  logging:
    #HTTP请求日志开关
    http-log:
      enabled: true
    #系统操作日志开关
    sys-log:
      enabled: true
```

#### 3）使用

系统操作日志可在controller中的请求方法上添加`@SysLog`注解，如下所示

```java
@RestController
public class TestController {
    @SysLog("测试接口")
    @RequestMapping("/test")
    public Result<Integer> test() {
        return Result.success(1);
    }
}
```

开发者可在添加日志模块依赖后，实现`SysLogOperateService`中的`writeLog`方法，监控到异步日志信息。可在此方法中添加日志入库或其他逻辑。

## 3.lizhi-swagger-spring-boot-starter 接口文档模块

### 3.1基本介绍

lizhi-swagger-spring-boot-starter提供了Swagger接口文档模块功能，开发者只需引入此项目依赖，并添加简单的配置，即可使服务具有Swagger接口文档，便于交互、测试、导入MOCK平台使用

### 3.2使用

#### 1）导入依赖

```pom
        <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-swagger-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）添加配置信息

可通过以下配置进行自定义Swagger设置：

```yml
lizhi:
  #接口文档
  swagger:
    #是否开启接口文档
    enabled: true
    #扫描的包，一般为controller所在包名
    base-package: com.chengxiaoxiao.lizhicloud.auth.controller
    #接口文档界面显示的标题
    title: 用户权限模块
    #接口文档界面显示的描述信息
    description: 提供用户鉴权、验证、权限分发的功能
    #接口文档的版本号
    version: 1.0.0
    #是否开启权限验证
    enabled-auth: true
    #TOKEN在HEADER中的值
    token-header: TOKEN
    #联系人信息
    contact:
      email: example@chengxiaoxiao.com
      name: 联系人
      url: http://www.chengxiaoxiao.com
    
```

其中base-package、title为必填项，其他可不填已包含默认值。

#### 3）使用Swagger接口文档

在各服务api包中添加swagger注解，并用controller实现其中的方法。即可在接口文档中展示对应的接口。

在浏览器输入网址：http://${ip}:${port}/swagger-ui/ 。即可进入接口文档展示界面。

## 4.lizhi-fastjson-spring-boot-starter 统一结果处理模块

### 4.1基本介绍

lizhi-fastjson-spring-boot-starter提供了对JSON响应数据的统一化配置与展示策略。主要包括以下功能：

1.统一编码为UTF-8

2.统一日期格式为：yyyy-MM-dd HH:mm:ss

3.空数据策略：

​	1）字符串Null值转换为“”

​	2）数字Null转换为0

​	3）Boolean Null值转换为false

​	4）集合Null转换为空集合

### 4.2使用

#### 1）导入依赖

```pom
        <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-fastjson-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）测试

导入并引入依赖后，可测试默认返回的JSON，校验是否生效。

## 5.lizhi-login-spring-boot-starter 登录中心模块

### 4.1基本介绍

lizhi-login-spring-boot-starter提供了对用户权限校验及用户信息解析器的功能，开发者只需引入此模块，便可实现对用户权限校验、获取当前登录用户的功能。

### 4.2使用

#### 1）导入依赖

```pom
       <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-login-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）使用

1.引入依赖后，此服务便开启了用户权限校验的功能。必须按照规定格式，传入TOKEN才可以访问本服务接口。

2.引入依赖后，可以在本服务controller接口参数中添加LoginUser对象参数，实现获取登录用户功能。例如：

```java
@RestController
@RequestMapping("/operator-info")
public class OperatorInfoController implements OperatorInfoControllerApi {
    @Override
    @GetMapping("/{id}")
    public Result detail(@PathVariable String id, LoginUser loginUser) {
        return Result.success(operatorInfoService.detailById(id));
    }
}
```

在用户有权限访问本接口的基础上，当前登录的用户信息便可以注入loginUser变量。

## 6.lizhi-cors-spring-boot-starter 跨域处理模块

### 4.1基本介绍

lizhi-cors-spring-boot-starter提供了对各个微服务的跨域能力，开发者只需引入此模块，便可使微服务拥有了跨域处理的功能。

### 4.2使用

#### 1）导入依赖

```pom
       <dependency>
            <groupId>com.chengxiaoxiao</groupId>
            <artifactId>lizhi-cors-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

#### 2）使用

引入此模块，无需配置即可使用。