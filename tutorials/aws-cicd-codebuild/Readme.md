### Deploy Spring Boot to ECS Using CodeBuild & CodePipeline
![image project](image_aw_cicd_base.png)

## Tools Working 
- Aws Codebuild
- Aws ECR(Elastic Container Registry)
- Docker
- Aws ECS(Elastic Container Service)


## AWS CodeBuild 
AWS CodeBuild is a fully managed continuous integration (CI) service provided by Amazon Web Services (AWS). Its primary function is to compile source code, run tests, and produce deployable artifacts. Unlike traditional CI systems that require you to set up and maintain build servers, AWS CodeBuild automatically scales and runs multiple builds in parallel, speeding up your software delivery process.

![aws_codebuild](aws_codebuild.png)

## AWS ECR( Elastic Container Registry)
Amazon Elastic Container Registry (Amazon ECR) is an AWS managed container image registry service that is secure, scalable, and reliable. Amazon ECR supports private repositories with resource-based permissions using AWS IAM. This is so that specified users or Amazon EC2 instances can access your container repositories and images. You can use your preferred CLI to push, pull, and manage Docker images, Open Container Initiative (OCI) images, and OCI compatible artifacts.

![aws_ecr](aws_ecr.png)