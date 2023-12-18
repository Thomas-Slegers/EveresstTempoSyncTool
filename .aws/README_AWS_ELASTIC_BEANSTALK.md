# Hosting on AWS Elastic Beanstalk
The frontend application linked to this backend is deployed on Amazon S3 & CloudFront.

## Using Cloudformation
(preferred way when deploying to Amazons Web Services)
Do the Cloudformation - Manual Steps-SingleInstance.md 
and upload the corresponding .json template file to Cloudformation.
Also provide and configure the IAM role for Github to deploy on Beanstalk.

## Alternative Manual steps

### Deployment
https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.cloud.aws

The backend application can be deployed on AWS Elastic Beanstalk.
Elastic Beanstalk environments run an nginx instance on port 80 to proxy the actual application, 
running on port 5000. Enable the CORS configuration to support the frontend to reach the backend application.

    See the application.properties file for this configuration.

Create an Elastic Beanstalk environment for Java without a database (since this has an embedded database).


|   	|   	|
|---	|---	|
| EveresstTempoCamisSync-env | Environment |
| EveresstTempoCamisSync | Application |

## Deploy using Github Action

### Add the user credentials to your Github Repository

under EveresstTempoSyncTool/settings/secrets/actions

Add the following secrets and their values :

    AWS_ACCESS_KEY_ID
    AWS_SECRET_ACCESS_KEY
    AWS_REGION
    AWS_APPLICATION_NAME
    AWS_ENVIRONMENT_NAME
    

### Run the Github Action
by taking a release (defined in gradle-deploy.yml) and afterwards check the url
    http://AWS_ENVIRONMENT_NAME.[RANDOMIZED].AWS_REGION.elasticbeanstalk.com

### Spring Actuator & Logs
/logs
/actuator/health