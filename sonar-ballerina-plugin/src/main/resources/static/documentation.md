---
title: Ballerina
key: Ballerina
---

## Prerequisites

* SonarQube Scanner should run on a x86-64 Windows, macOS or Linux 64bits machine

## Language-Specific Properties

By default, all the `vendor` directories are excluded from the analysis. However, you can change the property `sonar.go.exclusions` to a different pattern if you want to force their analysis (not recommended).

## "sonar-project.properties" Sample

Here is a first version of a `sonar-project.properties` file, valid for a simple `Ballerina` project:

```
  sonar.projectKey=com.company.projectkey1
  sonar.projectName=My Project Name

  sonar.sources=.
  sonar.exclusions=**/*_test.bal

  sonar.tests=.
  sonar.test.inclusions=**/*_test.bal
```