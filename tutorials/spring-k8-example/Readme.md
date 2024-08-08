# Project Basic Docker and Kubernetes 

Project Basic Application Rest, where docker the application and run in kuberentes 

### Compilation maven
Run this command to create file jar to use in file Dockerfile
```
./mvnw clean install -D skipTests
```
#### Commands to run docker compose 
In the file compose.yaml contain the information to load application from Dockerfile and and database
```
docker compose  -f compose.yaml up
```



### Build image and upload to Docker Hub
```
docker build -t manuonda/spring8k:1.0 .
docker login
docker push manuonda/spring8k:1.0
```

### Commands kubernetes run postgresql
```
## Database 
kubectl  apply -f configMap.yaml
kubectl  apply -f secretKey.yaml
kubectl  apply -f deployment-posgresql.yaml
kubectl  apply -f pvc-postgresql.yaml

kubectl get deployment 
kubectl get pods 

kubectl describe deployment postgresql-deployment
kubectl describe pod postgresql-deployment-mwnw

## Connect to pod 
kubectl exec -it postgresql-deployment-594ddcc9df-blbm6 -- /bin/bash

psql -U postgres -d postgres



### Show logs pod
kubectl logs postgresql-deployment-594ddcc9df-blbm6
```

### Commands kubernetes application spring boot 
```
kubectl apply -f deployment.yaml
```

### Comands show service and connect to minikube 
```
$ kubectl get svc
NAME                  TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes            ClusterIP   10.96.0.1       <none>        443/TCP          43d
postgresql-svc        ClusterIP   None            <none>        5432/TCP         21h
springboot-crud-svc   NodePort    10.107.55.194   <none>        8081:32027/TCP   21h


$ minikube ip
192.168.49.2
$ curl http://192.168.49.2:32027/api/v1/orders
[]

```



### Problem file pg_hba.conf
1 - Ejecuta el siguiente comando para copiar el archivo pg_hba.conf del contenedor a tu máquina local:
```
kubectl cp postgresql-deployment-54d4c5dcff-pk6bs:/var/lib/postgresql/data/pg_hba.conf ./pg_hba.conf
```
2. Editar el Archivo Localmente

Edita el archivo pg_hba.conf en tu máquina local usando el editor de tu elección. Por ejemplo, si usas nano, el comando sería:
```
nano pg_hba.conf
```
Cambia la línea de md5 a trust para permitir conexiones sin contraseña. Después de editar, guarda el archivo.

3. Copiar el Archivo Modificado de Vuelta al Contenedor

Una vez que hayas editado el archivo, cópialo de vuelta al contenedor con el siguiente comando:
```
kubectl cp ./pg_hba.conf postgresql-deployment-54d4c5dcff-pk6bs:/var/lib/postgresql/data/pg_hba.conf
```
4. Reiniciar el Contenedor

Para que los cambios tomen efecto, necesitas reiniciar el contenedor. Puedes hacerlo eliminando el pod, lo que provocará que Kubernetes cree uno nuevo automáticamente:

```
kubectl delete pod postgresql-deployment-54d4c5dcff-pk6bs

```
### Command 
```
echo -n "postgres" | base64
```