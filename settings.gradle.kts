pluginManagement{
    repositories {
        maven {
            url = uri("https://mirrors.cloud.tencent.com/maven/")
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "blog"
include("blog-api")