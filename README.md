# spring-boot,mybatis,angularjs整合实例

## 项目简介
采用`spring-boot`搭建项目，IDE采用Eclipse，数据库支持Mysql以及H2，集成了Mybatis、AngularJS、百度编辑器、文件上传、Redis、Solr、验证码以及微信等功能，前后台数据传输采用JSON方式，同时实现了登录拦截器以及跨域访问的配置。

## 项目结构:
>spring-boot-project
>>spring-boot-common: `常用工具类`<br>
>>spring-boot-mapperpojo：`从数据库导出的model以及mapper`<br>
>>spring-boot-pagepojo：`页面model`<br>
>>spring-boot-service：`服务层`<br>
>>spring-boot-web：`页面，项目打包位置以及配置文件`

## 使用方法
* 1.创建一个Mysql数据库dbtest
* 2.导入`spring-boot-project\spring-boot-web\src\main\resources`目录下的`dbtest.sql`
* 3.设置配置文件`application.yml`，修改`static-locations`中的`webapp`路径指向本地路径，修改`datasource`中的数据库配置参数
* 4.运行`spring-boot-web`项目，选中`Application.java`，run as->java application

## 运行效果
1.用户管理：http://localhost:8002/admin/user/user.jsp
![用户管理测试](https://github.com/gjq246/spring-boot-project/raw/master/screenshot/useradmin.png)  

2.验证码：http://localhost:8002/
![验证码测试](https://github.com/gjq246/spring-boot-project/raw/master/screenshot/captcha.png)  

3.文件上传：http://localhost:8002/file.html
![文件上传测试](https://github.com/gjq246/spring-boot-project/raw/master/screenshot/upload.png)  

4.百度编辑器：http://localhost:8002/jslib/plugins/ueditor/index.html
![百度编辑器测试](https://github.com/gjq246/spring-boot-project/raw/master/screenshot/ueditor.png)  

5.api文档：http://localhost:8002/swagger-ui.html
![api文档测试](https://github.com/gjq246/spring-boot-project/raw/master/screenshot/swagger.png) 

## Mapper代码生成
在`spring-boot-mapperpojo`项目中,配置好`src/main/resources/generator/generatorConfig.xml`配置文件中的包名和路径，为了避免被重复覆盖，在mapper的java和xml文件导出路径中加了mappertemp目录，此目录仅作导出存储，需要手工复制一份到mapper路径。导出时采用`mvn mybatis-generator:generate`，如果是用maven build...菜单只要输入`mybatis-generator:generate`

## 登录拦截器
在`spring-boot-web`项目中的`com.kpttech.web`包中的`AdminInterceptor`类。
```java
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
	//这里验证用户是否登录
}
```

## 学习交流
[我的博客](http://blog.csdn.net/gjq246)
