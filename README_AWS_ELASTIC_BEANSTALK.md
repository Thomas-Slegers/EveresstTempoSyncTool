# Hosting on AWS Elastic Beanstalk
The frontend application linked to this backend is deployed on Amazon S3.

## Deployment
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

### Set up SSL certificate for HTTPS
To avoid block mixed content problems, set up a SSL certificate using AWS Certificate Manager.
This certificate can be used in the Elastic Beanstalk environment.
Also adapt your DNS settings to point to the Elastic Beanstalk environment.

### Set up a load balancer
When creating the Elastic Beanstalk environment, set up a load balancer.
This load balancer will be used to redirect HTTPS traffic coming in to HTTP traffic.
To do add, add a listener to the load balancer, listening on port 443 and forwarding to port 80.
The nginx instance will then redirect the traffic to port 5000, where the application is running.



## Add the user credentials to your Github Repository

under EveresstTempoSyncTool/settings/secrets/actions

Add the following secrets and their values :

    AWS_ACCESS_KEY_ID
    AWS_SECRET_ACCESS_KEY
    AWS_REGION
    AWS_APPLICATION_NAME
    AWS_ENVIRONMENT_NAME
    

## Run the Github Action
by taking a release and check the url
    http://AWS_ENVIRONMENT_NAME.eba-jxuqphcp.AWS_REGION.elasticbeanstalk.com

## Spring Actuator & Logs
/logs
/actuator/health