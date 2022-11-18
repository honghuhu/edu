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
docker run --name mysql-oeieo -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
docker exec -it mysql-oeieo bash
mysql -h0.0.0.0 -uroot -p
CREATE USER 'oeieo'@'%' IDENTIFIED WITH mysql_native_password BY 'oeieo';
GRANT ALL PRIVILEGES ON *.* TO 'oeieo'@'%';
```