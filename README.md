# Junit5 + Cucumber + Spring + Allure + rest-assured

## 要求
* [OpenJDK17 Hotspot](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot)
* [Maven3](https://maven.apache.org/download.cgi)
* [IntelliJ Cucumber support](https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html)

## 运行测试
```
mvn clean test
```

## 生成报告
```
mvn allure:serve
```

## extent report
```
target/cucumber/report.html
```

