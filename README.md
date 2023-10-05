# SonarQube Ballerina Plugin (SLang)

## How it works

This Plugin provides Ballerina static code analysis support for SonarQube.

## Other ways to create a SonarQube plugin

- Using SSLR (SonarSource Language Recognizer)

  - [SSLR documentation](https://github.com/SonarSource/sslr)
  - [SonarQube Java Plugin](https://github.com/SonarSource/sonar-java)
  - [SonarQube Python Plugin](https://github.com/SonarSource/sonar-python)

- Using SLang (Sonar Lang)

  - [SLang documentation](https://github.com/SonarSource/slang)
  - [SonarQube jProperties Plugin](https://github.com/pepaproch/slang-jproperties)

- Using SonarQube Java Plugin API

  - [SonarQube Java Plugin API documentation](https://docs.sonarsource.com/sonarqube/9.9/extension-guide/developing-a-plugin/plugin-basics/)
  - [SonarQube Kotlin Plugin](https://github.com/SonarSource/SonarJS)
  - [SonarQube JavaScript Plugin](https://github.com/SonarSource/SonarJS)

## Plugin Creation steps

1. Clone the repository

2. Copy and existing SLang plugin to create the new language support on top of (Scala was chosen)

3. Rename it to Sonar Ballerina Plugin

4. Change the build.gradle file to suite Ballerina

5. Use the Plugin class as a starting point on converting the duplicated plugin to suite Ballerina

```
📦sonar-ballerina-plugin
 ┣ 📂bin
 ┣ 📂build
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂org
 ┃ ┃ ┃ ┃ ┗ 📂wso2
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ballerina
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂checks
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂externalreport
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂plugin
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BallerinaCheckList.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BallerinaLanguage.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ **📜BallerinaPlugin.java**
```

6. Continue Developing...

## Resources utilized for testing and creating the plugin

- Testing Source files analysis from SonarQube: [SonarQube Scans Project](https://github.com/SonarDance/SonarQube-scans-testing)
- Testing Ballerina Packages for integrations: [Shariff Project](https://github.com/SonarDance/ShariffCommand)

## SonarQube plugin integration steps

1. Run the gradle command to build a jar of the plugin

- With tests

```cmd
gradlew clean build
```

- Ignoring tests

```cmd
gradlew clean build -x test
```

2. The generated jar file can be found in the libs directory as follows:

```
📦slang-ballerina
 ┣ 📂.github
 ┣ 📂checkstyle-import
 ┣ 📂its
 ┣ 📂jruby-repackaged
 ┣ 📂slang-antlr
 ┣ 📂slang-api
 ┣ 📂slang-api
 ┣ 📂slang-checks
 ┣ 📂slang-plugin
 ┣ 📂slang-plugin
 ┣ 📂slang-testing
 ┣ 📂.github
 ┗ 📂sonar-ballerina-plugin
    ┣ 📂bin
    ┣ 📂build
    ┃ ┣ 📂classes
    ┃ ┣ 📂generated
    ┃ ┣ 📂jacoco
    ┃ ┣ 📂libs
    ┃ ┃ ┣ **📜sonar-ballerina-plugin-1.15.0-SNAPSHOT-all.jar**
    ┃ ┃ ┗ 📜sonar-ballerina-plugin-1.15.0-SNAPSHOT.jar
```

3. Place the generated jar file in the plugins directory of SonarQube as follows:

```
📦sonarqube-9.9.2.77730
 ┣ 📂bin
 ┣ 📂conf
 ┣ 📂data
 ┣ 📂elasticsearch
 ┣ 📂extensions
 ┃ ┣ 📂downloads
 ┃ ┣ 📂jdbc-driver
 ┃ ┗ 📂plugins
 ┃ ┃ ┣ 📜README.txt
 ┃ ┃ ┗ **📜sonar-ballerina-plugin-1.15.0-SNAPSHOT-all.jar**
```

4. Run SonarQube

# SLang

[![Build Status](https://travis-ci.org/SonarSource/slang.svg?branch=master)](https://travis-ci.org/SonarSource/slang)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.slang%3Aslang&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.sonarsource.slang%3Aslang) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.slang%3Aslang&metric=coverage)](https://sonarcloud.io/component_measures/domain/Coverage?id=org.sonarsource.slang%3Aslang)

This is a developer documentation. If you want to analyze source code in SonarQube read one of the following documentations:

- Ruby language: [analysis of Ruby documentation](https://docs.sonarqube.org/latest/analysis/languages/ruby/)
- Scala language: [analysis of Scala documentation](https://docs.sonarqube.org/latest/analysis/languages/scala/)
- Go language: [analysis of Go documentation](https://docs.sonarqube.org/latest/analysis/languages/go/)

SLang (SonarSource Language) is a framework to quickly develop code analyzers for SonarQube. SLang defines language agnostic AST. Using this AST
we can develop simple syntax based rules. Then we use parser for real language to create this AST. Currently Ruby and Scala
analyzers use this approach.

## Ruby

We use [whitequark parser](https://github.com/whitequark/parser) to parse Ruby language by embedding it using JRuby runtime.

- AST documentation for the parser can be found [here](https://github.com/whitequark/parser/blob/master/doc/AST_FORMAT.md)
- We use simple [Ruby script](sonar-ruby-plugin/src/main/resources/whitequark_parser_init.rb) to call the parser and invoke our [visitor](sonar-ruby-plugin/src/main/java/org/sonarsource/ruby/converter/RubyVisitor.java) written in Java

## Scala

We use [Scalameta](https://scalameta.org/) to parse Scala language.

### Scala coverage

For Scala files, we will import both [Scoverage](http://scoverage.org/) and JaCoCo coverage reports. Note that this will result in strange behavior since:

- Only line coverage will be used from the Scoverage report.
- JaCoCo can be imprecise when computing conditions coverage on Scala code, generating FP (typically on pattern matching).

This situation only applies to two Scala files, this current situation is acceptable.

## Go

We use the native Go parser to parse Go language.

## Have question or feedback?

To provide feedback (request a feature, report a bug etc.) use the [SonarQube Community Forum](https://community.sonarsource.com/). Please do not forget to specify the language, plugin version and SonarQube version.

## Building

### Setup

If you are on Windows, read the [sonar-go-to-slang/README.md](sonar-go-to-slang/README.md) instructions.

_SonarSource internal usage: Configure your `gradle.properties` - read the [private/README.md](private/README.md) instructions._

### Build

Build and run Unit Tests:

    ./gradlew build

## Integration Tests

By default, Integration Tests (ITs) are skipped during build.
If you want to run them, you need first to retrieve the related projects which are used as input:

    git submodule update --init its/sources

Then build and run the Integration Tests using the `its` property:

    ./gradlew build -Pits --info --no-daemon

You can also build and run only Ruling Tests using the `ruling` property:

    ./gradlew build -Pruling --info --no-daemon

If you want to run ruling tests for specific language, you can use `ruling-{lang}` property (`ruling-scala`, `ruling-ruby`, `ruling-go`). For example:

    ./gradlew build -Pruling-scala --info --no-daemon

## License headers

License headers are automatically updated by the spotless plugin but only for Java files.
Furthermore there are files such as `package-info.java`and `module-info.java`that spotless ignores. Also Scala and Go source files are not handled. For those files use a manual script like below to update the license. E.g., for Go files (on mac):

    `find . -type f -name "*.go" -exec sed -i '' 's/2018-2022/2018-2023/' "{}" \;`

```

```
