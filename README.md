# SillyGooseClient
   Silly Goose Client  
   Android项目  
   Gradle4.1 构建  
   minSdk 23  
   targetSdk 26  
   compileSdk 26  
### Author:
    Wang jiaruo   
    Zhu xiaoyue  
    Yang guoxxueying  
    Du yuwen  
### Description:
  游戏客户端
  bug贼多，美工不是专业的，程序员也不是专业的，凑活看吧
  界面需美化，游戏反馈需调整采用OkHttp3框架进行http连接，图片加载库为Universal-Image-Loader，天气服务使用的是易源数据(ShowAPI)，可以说是一个大杂烩了，参照网上的代码自建了很多没用的轮子（为了学习嘛），本来打算将工具类做成开箱即用的，然而水平不够，做了很多修改（在 com.SillyGoose.Utils Package下）
### Project Structure
        |app : 项目源文件
            |build
            |debug
            |libs : 项目依赖的额外jar包、lib包
            |src : 源文件
                |androidTest : 测试类，包括单元测试，但我们没用
                |main : 源文件包
                |java ：Java源程序
                    |com.SillyGoose
                        |Activity : Android Activity/Service 包，界面包
                            |
                        |Model ： 模型包，包含程序运行所需的数据模型类
                            |
                        |Utils : 工具包，包含程序运行的工具
                            |
                |res :资源文件夹
                |AndroidManifest.xml : 描述了package中暴露的组件（activities, services, 等等），他们各自的实现类，各种能被处理的数据和启动位置。
                app.iml :
                build.gradle : app gradle(项目基本构建信息)
          |build ： 历史编译信息、调试信息
          |gradle ： gradle
          build.gradle : app gradle 信息
          README.md ： readme
### Build In Your computer
1. clone to your computer

        git clone https://github.com/SillyGoose4/Client.git Client

2. Import into android Studio
  (It will take you a lot of time,just wait for it build success)  

3. Run. 

### Release
[逛吃的鹅v1.0.9](/app/build/outputs/apk/release/逛吃的鹅1.0.9.apk)
