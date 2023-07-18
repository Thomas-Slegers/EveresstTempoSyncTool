# EveresstTempoSyncTool

Backend service which uses the CamisAPI library. Packages for this library are available in the repository defined in gradle.properties.

The backend service can be used by EveresstTempoSyncUI frontend to upload the necessary files.

## Get it started
To be able to retrieve the artifacts from Gradle :

Place under your Gradle User Home ( C:\Users\xxx\.gradle in Windows)
a gradle.properties file including 

    gpr.user=
    gpr.key=

For the Github Action, the configuration is done in workflows/gradle-publish.yml
This file configures the environment variables necessary for retrieving and publishing artifacts from repositories, as defined in build.gradle.
Keep in mind that multiple repositories are used, defined in gradle.properties

## Executable jar
From Gradle (Terminal might not have the correct Java version), run bootJar

## Deployment

### Backend application
https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.cloud.aws

The backend application can be deployed on AWS Elastic Beanstalk.
Elastic Beanstalk environments run an nginx instance on port 80 to proxy the actual application, running on port 5000.
See the application.properties file for this configuration.

### Frontend application
The frontend application can be deployed on Amazon S3.

## Improve the tool
	

