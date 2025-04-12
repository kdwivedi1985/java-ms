aws cloudformation create-stack \
  --region us-east-1 \
  --stack-name my-demo-eks-cluster \
  --capabilities CAPABILITY_NAMED_IAM \
  --template-body file://eks-stack.yaml 
