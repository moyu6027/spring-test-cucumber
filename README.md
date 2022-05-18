# Скелет тестового проекта Junit5 + Cucumber + Spring + Allure

## Требования к ПО разработчика
* [OpenJDK11 Hotspot](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot)
* [Maven3](https://maven.apache.org/download.cgi)
* [IntelliJ Cucumber support](https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html)

## Запуск тестов
```
mvn clean test
```

## Генерация отчета
```
mvn allure:serve
```

## Управление количеством потоков
Осуществляется в файле [junit-platform.properties](src/test/resources/junit-platform.properties)