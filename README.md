## ApiBoot Admin

基于新一代接口服务框架 “ApiBoot” 的前后分离管理平台基础解决方案脚手架示例。

- [猛戳查看ApiBoot组件系列化文章教程](https://blog.yuqiyu.com/apiboot-all-articles.html)
- [ApiBoot源码仓库](https://gitee.com/minbox-projects/api-boot)

## I. 诞生

为了给`ApiBoot`使用者演示各个组件整合使用，因此`ApiBoot Admin`诞生了！！！

因为`ApiBoot`是由Java编写的纯后端组件化基础框架，为了结合实际中的开发场景，所以将`vue-element-admin`集成到了`ApiBoot Admin`中作为前端的展示，从而打造了一个简单的`前后端分离`的演示环境。

> 友情提示：可以将`admin-services`项目从`ApiBoot Admin`独立出来作为一个接口服务，由于内部提供了一些常用的常量、基础的统一配置等等，完全可以在其基础上进行二次开发。

## II. 源码目录

`ApiBoot Admin`是一个前后分离的演示项目。

### 1. 接口部分 (admin-services)

接口为后台的前端框架提供数据的支持，完全使用`ApiBoot`内提供的组件进行编写，使用的组件如下一览（点击直接把你送到组件的官方文档）：

- [x] [ApiBoot OAuth](http://apiboot.minbox.io/zh-cn/docs/api-boot-oauth.html)
- [x] [ApiBoot Security](http://apiboot.minbox.io/zh-cn/docs/api-boot-security.html)
- [x] [ApiBoot Logging](http://apiboot.minbox.io/zh-cn/docs/api-boot-logging.html)
- [x] [ApiBoot Logging Admin](http://apiboot.minbox.io/zh-cn/docs/api-boot-logging-admin.html)
- [x] [ApiBoot MyBatis Enhance](http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html)
- [x] [ApiBoot MyBatis Enhance Codegen](http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance-codegen.html)
- [x] [ApiBoot MyBatis Pageable](http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-pageable.html)
- [x] [ApiBoot Swagger](http://apiboot.minbox.io/zh-cn/docs/api-boot-swagger.html)
- [ ] [ApiBoot DataSource Switch](http://apiboot.minbox.io/zh-cn/docs/api-boot-datasource-switch.html)
- [ ] [ApiBoot Resource Load](http://apiboot.minbox.io/zh-cn/docs/api-boot-resource-load.html)
- [ ] [ApiBoot Rate Limiter](http://apiboot.minbox.io/zh-cn/docs/api-boot-rate-limiter.html)
- [ ] [ApiBoot Quartz](http://apiboot.minbox.io/zh-cn/docs/api-boot-quartz.html)
- [ ] [ApiBoot Aliyun Mail](http://apiboot.minbox.io/zh-cn/docs/api-boot-mail.html)
- [ ] [ApiBoot Aliyun OSS](http://apiboot.minbox.io/zh-cn/docs/api-boot-oss.html)
- [ ] [ApiBoot Aliyun SMS](http://apiboot.minbox.io/zh-cn/docs/api-boot-sms.html)
- [ ] [ApiBoot Message Push](http://apiboot.minbox.io/zh-cn/docs/api-boot-message-push.html)

### 2. 前端部分 (admin-ui)

前端采用 **花裤衩** 提供的`vue-element-admin`整合框架，详细使用请访问`vue-element-admin`[官方文档](https://panjiachen.github.io/vue-element-admin-site/zh/)。

### 日志服务端 (admin-log-server)

基于`ApiBoot Logging Admin`组件构建，用来采集接口服务（`admin-services`）所产生的全部日志信息并记录到数据库内（`请求日志`、`链路日志`、`全局日志`）。

## III. 运行环境

- JDK 1.8+
- MySQL 5.5+
- NodeJs 10.18.0
- Npm 6.13.4

## IV. 本地运行

### 1. 创建数据库

请复制仓库内的 [api-boot-admin.sql](https://gitee.com/minbox-projects/api-boot-admin/blob/master/api-boot-admin.sql) 脚本内容在本地自行创建数据库。

### 2. 下载源码

```bash
git clone git@gitee.com:minbox-projects/api-boot-admin.git
```

### 3. 编译项目

```bash
# 进入api-boot-admin目录
➜  cd api-boot-admin
# 在api-boot-admin根目录打包项目
➜  api-boot-admin git:(master) ✗ mvn clean package
```

### 4. 启动日志服务

```bash
➜  api-boot-admin git:(master) ✗ cd admin-log-server 
➜  admin-log-server git:(master) ✗ java -jar target/admin-log-server-0.1.0.RELEASE.jar
```

### 5. 启动接口

```bash
➜  api-boot-admin git:(master) ✗ cd admin-services 
➜  admin-services git:(master) ✗ java -jar target/admin-services-0.1.0.RELEASE.jar 
```

### 6. 运行后台

```bash
# 安装npm依赖包
➜  admin-ui git:(master) ✗ npm install
# 运行开发环境
➜  admin-ui git:(master) ✗ npm run dev
```

后台成功启动后，访问 [http://localhost:9527/](http://localhost:9527/) 可看到如下界面：


![](https://assets.yuqiyu.com/images/api-boot-admin/WX20200107-122654@2x.png)

> 在`api-boot-admin.sql`数据库脚本中有登录的用户信息。
>
> 用户名：hengboy，密码：123456

![](https://assets.yuqiyu.com/images/api-boot-admin/WX20200107-123051@2x.png)

> 首页只是图表组件示例，如需动态读取数据可以了解`vue-element-admin`。

![](https://assets.yuqiyu.com/images/api-boot-admin/WX20200107-122940@2x.png)

该模块内的功能是已经完善的，用到的接口都在`admin-services/org.minbox.framework.api.boot.admin.api.SystemUserApi`控制器内，查看源码可了解`ApiBoot`各个组件的使用方式。

## V. 接口文档

`ApiBoot Admin`内部通过集成`ApiBoot Swagger`来实现接口文档的自动生成。

访问地址：[http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)。

在接口Api类内的方法上简单的配置`Swagger2`所提供的注解就可以实现文档的自动生成，支持在线调试，还支持设置`OAuth2`所生成的请求令牌调试接口。

![](https://assets.yuqiyu.com/images/api-boot-admin/WX20200107-164206@2x.png)