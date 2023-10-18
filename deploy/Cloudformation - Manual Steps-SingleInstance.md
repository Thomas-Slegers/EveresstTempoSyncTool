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
