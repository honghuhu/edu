- 安装本地 jar 到本地仓库
```
/Applications/IntelliJ\ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.13 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.13.jar
```

- MacOs 启动 Nacos
```
cd ~/Downloads/extensions/nacos-1.2.1 && sh bin/startup.sh -m standalone
```

- 新建模块后, 双击空文件夹, 可以设置 Project

- idea 显示 Services 窗口, 编辑 (workspace.xml)
```
  <component name="RunDashboard">
    <option name="configurationTypes">
      <set>
        <option value="SpringBootApplicationConfigurationType" />
      </set>
    </option>
  </component>
```
### docker 安装 mysql
```bash
docker search mysql
docker pull mysql:latest
docker run --name mysql-oeieo -p 3306:3306 --restart=always -e MYSQL_ROOT_PASSWORD=root -d mysql
docker exec -it mysql-oeieo bash
mysql -h0.0.0.0 -uroot -p
CREATE USER 'oeieo'@'%' IDENTIFIED WITH mysql_native_password BY 'oeieo';
GRANT ALL PRIVILEGES ON *.* TO 'oeieo'@'%';
```
#### 将正在运行的容器设为自启动
```
docker update --restart=always mysql-oeieo
```
### docker 查看容器 ip
```
docker inspect mysql-oeieo | grep IPAddress
```
### docker 安装 nacos
#### 创建 nacos 数据库，创建表结构
```
docker exec -it mysql-oeieo bash
mysql -uroot -p
create database nacos_config;
```
#### 启动 nacos
```
docker run --name nacos -p 8848:8848 --privileged=true --restart=always -e JVM_XMS=256m -e JVM_XMX=256m -e MODE=standalone -e SPRING_DATASOURCE_PLATFORM=mysql -e MYSQL_SERVICE_HOST=172.17.0.2 -e MYSQL_SERVICE_DB_NAME=nacos_config -e MYSQL_SERVICE_USER=oeieo -e MYSQL_SERVICE_PASSWORD=oeieo -d nacos/nacos-server
```