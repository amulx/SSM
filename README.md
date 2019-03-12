#### mybatis generator的配置

* 1、在资源文件目录下新建`generatorConfig.xml`文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE generatorConfiguration
          PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
          "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
  
  <generatorConfiguration>
      <!--<classPathEntry location="/Program Files/IBM/SQLLIB/java/db2java.zip" />-->
  
      <context id="mysqlid" targetRuntime="MyBatis3">
          <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                          connectionURL="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8"
                          userId="root"
                          password="">
          </jdbcConnection>
  
          <javaTypeResolver >
              <property name="forceBigDecimals" value="false" />
          </javaTypeResolver>
  
          <javaModelGenerator targetPackage="com.xxx.Entities" targetProject="C:\Users\xxx\IdeaProjects\xxxlx\src\main\java">
              <property name="enableSubPackages" value="true" />
              <property name="trimStrings" value="true" />
          </javaModelGenerator>
  
          <sqlMapGenerator targetPackage="mappers"  targetProject="C:\Users\xxx\IdeaProjects\xxxlx\src\main\resources">
              <property name="enableSubPackages" value="true" />
          </sqlMapGenerator>
  
          <javaClientGenerator type="XMLMAPPER" targetPackage="com.xxx.mappers"  targetProject="C:\Users\xxx\IdeaProjects\xxxlx\src\main\java">
              <property name="enableSubPackages" value="true" />
          </javaClientGenerator>
  
          <table schema="test" tableName="product" domainObjectName="ProdInfo" >
              <property name="useActualColumnNames" value="true"/>
              <generatedKey column="prod_id" sqlStatement="SELECT LAST_INSERT_ID()" identity="true" />
          </table>
  
      </context>
  </generatorConfiguration>
  ```

* 2、在`pom.xml`中的`build`下的`plugins`新增编译依赖

  ```xml
          <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.7</version>
            <configuration>
              <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
              <overwrite>true</overwrite>
            </configuration>
            <executions>
              <execution>
                <id>Generate MyBatis Artifacts</id>
                <goals>
                  <goal>generate</goal>
                </goals>
              </execution>
            </executions>
            <dependencies>
              <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.15</version>
              </dependency>
            </dependencies>
          </plugin>
  ```

* 3、新增`Run/Debug Configurations`

  commond line 填入`mybatis-generator:generate`

* 需要增加的`maven`依赖

  ```xml
      <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
        <version>1.3.7</version>
      </dependency>
  
      <!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-maven-plugin -->
      <!-- 直接通过maven直接调用ybatis-generator自动生成配置文件 -->
      <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.7</version>
      </dependency>
  ```

  

---

#### 跨域访问的配置方式

```xml
<mvc:cors>
    <mvc:mapping path="/users/**"
                 allowed-origins="*"
                 allowed-methods="GET, POST, PUT,DELETE"
    />

    <mvc:mapping path="/resources/**"
                 allowed-origins="http://domain1.com" />
</mvc:cors>
```

---

#### 配置`JSON`格式输出的方式

```xml
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters" >
            <list>
                <ref bean="stringHttpMessageConverter"/>
                <ref bean="mappingJacksonHttpMessageConverter" />
            </list>
        </property>
    </bean>
    <bean id="stringHttpMessageConverter" class="org.springframework.http.converter.StringHttpMessageConverter">
        <constructor-arg name="defaultCharset" value="utf-8" />
        <property name="writeAcceptCharset" value="false" />
    </bean>
    <bean id="mappingJacksonHttpMessageConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">

    </bean>
```

* mavne依赖

  ```xml
      <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
      <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.9.8</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
      <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.8</version>
      </dependency>
  ```

  ---

#### Spring + Spring mvc的配置

* `pom.xml`中增加的依赖

  ```xml
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.1.5.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.5.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.5.RELEASE</version>
      </dependency>
  ```

* `web.xml`中的配置

  ```xml
    <filter>
      <filter-name>CharacterEncodingFilter</filter-name>
      <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
      <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
      </init-param>
    </filter>
    <filter-mapping>
      <filter-name>CharacterEncodingFilter</filter-name>
      <url-pattern>/*</url-pattern>
    </filter-mapping>
  
    <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
      <servlet-name>bbbb</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:myapp-servlet.xml</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
    </servlet>
  
    <servlet-mapping>
      <servlet-name>bbbb</servlet-name>
      <url-pattern>/</url-pattern>
    </servlet-mapping>
  ```

  ---

#### 数据库连接池的配置

* `pom.xml`中依赖的增加

  ```xml
       <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.15</version>
      </dependency>
      
      <dependency>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>tomcat-jdbc</artifactId>
        <version>9.0.16</version>
      </dependency>
      
      <dependency>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>tomcat-juli</artifactId>
        <version>9.0.16</version>
      </dependency>
      
   	<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.0</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.0</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.5.RELEASE</version>
      </dependency>
  ```

* `ApplicationContext.xml`的配置

  ```xml
      <bean class="org.apache.tomcat.jdbc.pool.PoolProperties" id="poolProperties" p:url="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8"
      p:username="root" p:password="" p:driverClassName="com.mysql.cj.jdbc.Driver">
      </bean>
  
      <bean class="org.apache.tomcat.jdbc.pool.DataSource" p:poolProperties-ref="poolProperties"
            id="dataSource"/>
  
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" p:mapperLocations="classpath:mappers/**">
          <property name="dataSource" ref="dataSource" />
          <!--<property name="configLocation" value="classpath:mybatis-config.xml" />-->
      </bean>
      <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
          <property name="basePackage" value="com.amu.mappers" />
          <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
      </bean>
  ```
