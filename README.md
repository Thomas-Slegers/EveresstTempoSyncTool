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

For the GitHub Action, the configuration is done in workflows/gradle-publish.yml
This file configures the environment variables necessary for retrieving and publishing artifacts from repositories, as defined in build.gradle.
Keep in mind that multiple repositories are used, defined in gradle.properties

## Executable jar
From Gradle (Terminal might not have the correct Java version), run bootJar
For deployment reasons, see README_AWS_ELASTIC_BEANSTALK, this is now removed to create a war file upon build.

## Improve the tool

###  Make it usable
Homepage to
    DONE Starting a sync
    DONE Look up a sync by UUID -> in the url /sync/UUID 

Starting a sync
    DONE UUID generated at the front-end
    DONE Disable the button to avoid double click
    DONE Show the SyncUUID
        (go straight away to the look-up sync page)
    DONE Run as a batch in the background
    DONE Remove the Slack information from the Camis library
    DONE Understand Thomas's code by passing period & minimum hours + adding the checks + validation
    Streamable result
    
UI
    Only show errors
        insufficient hours
        sync errors
    DONE /UUID/resourceId
        Overview of all hours instead of week + filter / grouped by week & summed by day
        
    Bug ? hours logged Camis <> Tempo shows situation before the actual sync ...
    No sync action seems to work => it's Correct ...

Slack input
    Werkt voor update ...
    Enkel voor fouten is dat interessant
    Groeperen van de fouten (te weinig uren / andere fouten ) + link naar probleempagina persoon (resourceID pagina)

### More functionality

    Improve by inputting the total file 
        and giving as extra input the budget-bucket / domain division.
    
    

