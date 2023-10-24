# Manual steps to create the Cloudformation Elastic Beanstalk stack

## Create a key pair
  - Give it the name 'CloudFormationKeyPair' so you don't have to replace the key pair name in the .json file

## Create an IAM for Cloudformation to create the resources
  - In the navigation pane, choose "Roles" and then "Create role". 
  - Choose "CloudFormation" as the AWS service that will use this role.
  - Choose the "CloudFormation" use case and then "Next: Permissions".
  - Attach the necessary permissions policies. attach existing policies like AdministratorAccess 
  - Review the role and then create it.

## Create Stack EveresstTempoSyncTool-EB with the .json file
	- Select the CloudFormation role from above

# For the Github workflow deploy

## On AWS IAM, create AWS Access Key ID and Secret Access Key
Access the IAM (Identity and Access Management) dashboard:
In the IAM dashboard, on the left-hand side, you will see a "Access management" section. 
Create a new user: GithubWorkFlowDeployToBeansTalk
Add user to the group: BeansTalkDeployAccess
Add Permissions policies to the group : AWSElasticBeanstalkRoleCore & AWSElasticBeanstalkRoleCWL
Create the User

In the user details page, click on the "Security credentials" tab.
Here, find the "Access keys" section and click on the "Create access key" button.
Select Application running outside AWS
Retrieve your new keys
Important: This is the only time you will be able to view or download the Secret Access Key. Be sure to save it in a secure location. You can either download the CSV file containing both the Access Key ID and Secret Access Key or copy them and store them securely.

## Add the credentials to your Github Repository
AWS_ACCESS_KEY_ID
AWS_APPLICATION_NAME
AWS_ENVIRONMENT_NAME
AWS_REGION
AWS_SECRET_ACCESS_KEY