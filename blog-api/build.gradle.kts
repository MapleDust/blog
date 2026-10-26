// 插件管理
plugins {
    java
    // springboot插件
    alias(libs.plugins.spring.boot)
    // spring依赖管理
    alias(libs.plugins.spring.dependency.management)
}

// 项目信息
group = "top.fcidd"
version = "4.0.0"
description = "blog-api"

//设置环境为java25
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

// 仓库管理
repositories {
    // maven镜像仓库
    maven {
        url = uri("https://mirrors.cloud.tencent.com/maven/")
    }
    // maven中央仓库
    mavenCentral()
}

// 依赖管理
dependencies {
    // 参数验证
    implementation(libs.spring.boot.validation)
    // web服务
    implementation(libs.spring.boot.web)
    // 邮箱服务
    implementation(libs.spring.boot.mail)
    // mybatis
    implementation(libs.spring.boot.mybatis)
    // jwt签发
    implementation(libs.jwt)
    // mysql数据库
    implementation(libs.mysql)
    // springboot jdbc
    implementation(libs.spring.boot.jdbc)
    // springboot 安全框架
    implementation(libs.spring.boot.security)
    // oauth2授权服务器
    implementation(libs.spring.boot.oauth2.authorization.server)
    // oauth2资源服务器
    implementation(libs.spring.boot.oauth2.resource.server)
    // redis
    implementation(libs.spring.boot.redis)

    // lombok工具（编译时有效）
    compileOnly(libs.lombok)
    // lombok工具（注解处理）
    annotationProcessor(libs.lombok)

    // 测试单元
    testRuntimeOnly(libs.junit)
    // 测试参数验证
    testImplementation(libs.spring.boot.validation.test)
    // 测试web服务
    testImplementation(libs.spring.boot.web.test)
    // 测试lombok工具（测试编译时有效）
    testCompileOnly(libs.lombok)
    // 测试lombok工具（测试注解处理）
    testAnnotationProcessor(libs.lombok)
    // 安全框架测试
    testImplementation(libs.spring.boot.security.test)
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    destinationDirectory.set(file("${rootProject.projectDir}/build/distributions"))
}

tasks.clean {
    var file = file("${rootProject.projectDir}/build")
    if (file.exists()) {
        delete(file)
    }
}