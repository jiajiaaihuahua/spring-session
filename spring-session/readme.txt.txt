前言
    本教程采用spring boot部署spring session，实现不同项目中的session共享
    spring boot 请参考https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/htmlsingle/
1:本地必须有mysql数据库。用户名和密码默认为root root，通过在application.properties中配置
2：视图采用的是spring temple替代了jsp。
3：请先启动session1项目，启动之后，数据库中test库会自动创建spring_session 和spring_session_attributes表
4：然后在启动session2项目，
5：通过访问http://localhost:8080/ session1后台程序会创建一个session
6：访问  http://localhost:8090/  session2后台程序会得到session1创建的session

是不是很神奇！