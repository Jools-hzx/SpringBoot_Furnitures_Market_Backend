# SpringBoot_Furnitures_Market
使用了前后端分离 前端的主题框架为 Vue 3 + 后端基础框架 Spring-Boot

根据您提供的项目描述和代码示例，我将对您的回答进行优化，以确保它更加清晰、结构化，并且易于理解。以下是优化后的文档内容：


# SpringBoot_Furnitures_Market

## 简介
SpringBoot_Furnitures_Market 是一个基于前后端分离架构的全栈Web应用程序，前端采用Vue 3和Element-Plus框架，后端基于Spring Boot和MyBatis-Plus构建。项目旨在提供一个高效、响应式的用户界面，并利用Spring Boot的便利性快速开发RESTful API。

## 技术栈
### 前端
- **Vue 3**：前端核心框架。
- **Axios**：用于浏览器和node.js的HTTP客户端。
- **Element-Plus**：一套为开发者、设计师和产品经理准备的Vue 3 UI工具包。

### 后端
- **Spring Boot**：简化新Spring应用的初始搭建以及开发过程。
- **MyBatis-Plus**：MyBatis的增强工具，简化CRUD操作。

## 主要配置和工具引用
### 前端

```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App)
  .use(store)
  .use(router)
  .use(ElementPlus, { locale: zhCn })
  .mount('#app')
```

### 请求拦截器
```javascript
import axios from 'axios';

const request = axios.create({
  timeout: 5000,
});

request.interceptors.request.use(
  config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  response => {
    const res = response.data;
    if (response.config.responseType === 'blob') return res;
    if (typeof res === 'string') res = JSON.parse(res);
    return res;
  },
  error => {
    return Promise.reject(error);
  }
);

export default request;
```

### 后端依赖与配置
```xml
<!-- pom.xml 配置摘录 -->
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.5.3</version>
</parent>

<dependencies>
  <!-- 省略其他依赖 -->

  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.3</version>
  </dependency>

  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.17</version>
  </dependency>
</dependencies>

<properties>
  <!-- 省略其他属性 -->
  <maven.compiler.source>8</maven.compiler.source>
  <maven.compiler.target>8</maven.compiler.target>
</properties>
```

### 数据源配置
```yaml
server:
  port: 9999

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot_furn_01?useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: hzx2001
```

### MyBatis-Plus配置
```java
// MybatisPlusConfig.java 配置摘录
@Configuration
@MapperScan("com.hzx.furn.mapper")
public class MybatisPlusConfig {
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
  }
}
```

### Druid数据源配置
```java
// DruidDataSourceConfig.java 配置摘录
@Configuration
@Slf4j
public class DruidDataSourceConfig {
  @ConfigurationProperties("spring.datasource")
  @Bean
  public DataSource dataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    log.info("数据源={}", druidDataSource.getClass());
    return druidDataSource;
  }
}
```

### 跨域代理配置
```javascript
// vue.config.js 配置摘录
module.exports = {
  devServer: {
    port: 10000,
    proxy: {
      '/api': {
        target: 'http://localhost:9999',
        changeOrigin: true,
        pathRewrite: {'/api': ''},
      },
    },
  },
};
```

## 安装
1. **克隆项目**：`git clone https://github.com/your-username/SpringBoot_Furnitures_Market.git`
2. **安装前端依赖**：`cd SpringBoot_Furnitures_Market/frontend && npm install`
3. **安装后端依赖**：`cd SpringBoot_Furnitures_Market/backend && mvn install`

## 运行
- **启动前端开发服务器**：`npm run serve`
- **启动后端服务**：`mvn spring-boot:run`

## 测试
- **运行测试套件**：`mvn test`

## 许可证
- 本项目采用 [MIT License](https://opensource.org/licenses/MIT)。
