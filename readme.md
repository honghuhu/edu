### Maven 导包失败问题

![](https://imgkr.cn-bj.ufileos.com/1e349549-85ab-46fc-8206-04c3304fd0b5.png)

1. 去仓库将下载失败的依赖版本删掉

![](https://imgkr.cn-bj.ufileos.com/b6e82c15-c9b8-4535-baa5-a15e603d7b13.png)

2. 重新 Reimport

![](https://imgkr.cn-bj.ufileos.com/b4545d48-1487-4efd-a6a2-b5869b87a226.png)

3. 安装本地 jar 到本地仓库
```
/Applications/IntelliJ\ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.12 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.12.jar
```

4. MacOs 启动 Nacos
```
cd ~/Downloads/extensions/nacos-1.2.1 && sh bin/startup.sh -m standalone
```

5. bug 启动项目修改配置文件没有同步到 target,导致启动报错找不到对应配置

![](https://imgkr.cn-bj.ufileos.com/a43b75a1-9c5f-430d-8fbd-780a8db902da.png)

6. 新建模块后, 双击空文件夹, 可以设置 Project

