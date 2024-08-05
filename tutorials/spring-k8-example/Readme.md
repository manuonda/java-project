## Comandos para ejecutar un archivo en deployment 

kubectl create deployment spring-app --image=manuonda/spring8k:latest --port=8081 --dry-run=client -o yaml> spring-app-deployment.yaml
