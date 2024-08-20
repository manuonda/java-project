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


### Steps
1 - File **buildspec.yaml** :  This file contains the instructions for executing the build process.

   *  Location in folder: In this case, tutorials/aws-cicd-codebuild.
   *  Compile application: The Java application is compiled using the Dockerfile.
   *  Login to ECR: Authenticate to the Amazon ECR registry.
   * REPOSITORY_URI: The URL of the repository created in AWS ECR.
   *  Build and upload: Build the application with docker build and upload the image to the repository (AWS ECR).


 **1** - Create repository 
 ![aws_create_repostiroy](images/create_repository.png)

 ![aws_created_repository](images/aws_repository_created.png)

 **2** - Create role with permissions to ECR.

 ![aws_create_role](images/roles_aws_ecr.png)


 **3** - Create Codebuild 
  
  Specify that the project to be public and set the repository url github 

 ![aws_create_build_step1](images/create_build_step_1.png)


 **4** -Specify the folder containing the buildspec.yaml file

 ![aws_create_build_file_spec](images/create_build_step_2.png)

 ![aws_created_finish](images/created_step3.png)

 **5** - Build project
 

