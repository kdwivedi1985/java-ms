### Prerequisite

### Install AWS CLI
As the first step, you need to install AWS CLI -
https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html


### Install Terraform

https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli

brew tap hashicorp/tap
brew install hashicorp/tap/terraform

terraform -version
terraform -help plan

### Connect Terraform with AWS

Run <aws configure> 
Keep you access key and secret handy

### Initialize Terraform
Run <terraform init> in project directory. This will intialize the terraform environment for you and download the modules, providers and other configuration required.

it will generate .modules

### Optionally review the terraform configuration

Run <terraform plan> to see the configuration it creates when executed.

### Finally, Apply terraform configuation to create EKS cluster with VPC 
<terraform apply>

### Use following command to remove infrastructure
<terraform destroy>