aws cloudformation create-stack \
  --region us-east-1 \
  --stack-name my-demo-eks1-vpc \
  --template-body file://eks-vpc-stack.yaml