# EveresstTempoSyncTool

Backend service which uses the CamisAPI library. Packages for this library are available in the repository defined in gradle.properties.

The backend service can be used by EveresstTempoSyncUI frontend to upload the necessary files.

## Web API
Paths are configured in SyncController.

with

    /input : to start the sync with a generated UUID

    /sync/UUID/ : to show the result of the sync

    /sync/UUID/ResourceId/Date : to show the sync result of the given day


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
For deployment reasons, see README_AWS_ELASTIC_BEANSTALK, this is now removed to create a war file upon build.

## Improve the tool
Improve by inputting the total file and giving as extra input the budget-bucket / domain division.

