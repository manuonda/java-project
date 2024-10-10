# Kubernetes Ingress Controller and Ingress Resources

## Introduction
Kubernetes is an open-source platform designed to automate deploying, scaling, and operating application containers. One of the key components of Kubernetes is the Ingress, which manages external access to services within a cluster.

## Ingress Controller
An Ingress Controller is a specialized load balancer for Kubernetes that manages the routing of external HTTP/S traffic to the services in your cluster. It listens for changes to Ingress resources and updates its configuration accordingly.

### Key Features:
- **Traffic Management**: Directs traffic based on rules defined in Ingress resources.
- **SSL Termination**: Handles SSL certificates and terminates SSL connections.
- **Path-based Routing**: Routes traffic to different services based on the request path.
- **Host-based Routing**: Routes traffic based on the requested host.

## Ingress Resources
Ingress resources are Kubernetes objects that define how external HTTP/S traffic should be routed to services within the cluster. They specify rules for routing traffic based on hostnames and paths.

### Example of an Ingress Resource:
```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: example-ingress
spec:
  rules:
  - host: blog.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: blog-service
            port:
              number: 80
  - host: course.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: course-service
            port:
              number: 80


```

+-----------+          +--------------------+
|   Cliente  | ------> | Ingress Controller |
| (Browser)  |         |                    |
+-----------+          +--------------------+
                           |
     ----------------------|
    |                      |
    v                      v
+------------------+     +------------------+
|  Blog Service    |     |  Course Service  |
|                  |     |                  |
|  /blog           |     |  /course         |
|  /blog/posts     |     |  /course/list    |
|  /blog/authors   |     |  /course/details |
+------------------+     +------------------+




### Ejemplo de Rutas

- **Blog Service**:
  - `/blog`: Acceso a la página principal del blog.
  - `/blog/posts`: Acceso a la lista de publicaciones del blog.
  - `/blog/authors`: Acceso a la lista de autores del blog.

- **Course Service**:
  - `/course`: Acceso a la página principal de cursos.
  - `/course/list`: Acceso a la lista de cursos disponibles.
  - `/course/details`: Acceso a los detalles de un curso específico.


### Ejemplo de Configuración de Ingress

Aquí tienes un ejemplo de cómo podría verse el recurso Ingress para estos dos servicios:

```apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: microservices-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /$2
spec:
  rules:
    - host: javatechie.com
      http:
        paths:
          - path: "/course(/|$)(.*)"
            pathType: ImplementationSpecific
            backend:
              service:
                name: course-service
                port:
                  number: 80
          - path: "/blog(/|$)(.*)"
            pathType: ImplementationSpecific
            backend:
              service:
                name: blog-service
                port:
                  number: 80
```

### Instructions
#### Build Blog Service and push Docker
$ ```docker login```

blog-service$ ```./mvnw  clean install -D skipTests```

blog-service$ ```docker build -t manuonda/blog-service-ingress:1.0 ```

blog-service$ ```docker push  manuonda/blog-service-ingress:1.0``` 


#### Build Course Service and push Docker

course-service$ ```./mvnw  clean install -D skipTests```

course-service$ ```docker build -t  manuonda/course-service-ingress:1.0 . ```

course-service$ ```docker push  manuonda/course-service-ingress:1.0 ```


#### Kubernetes 

$ ``` minikube start```

$ ```minikube addons enable ingress```

blog-service$ ```kubectl  apply -f k8s-deployment.yaml``` 

course-service$ ``` kubectl  apply -f k8s-deployment.yam```

$ ```kubectl apply -f ingress.yaml```

```
$ kubectl  get pod -n ingress-nginx

NAME                                        READY   STATUS      

RESTARTS      AGE

ingress-nginx-admission-create-zgm7n        0/1     Completed   0             2d

ingress-nginx-admission-patch-zwrzr         0/1     Completed   2             2d
ingress-nginx-controller-7c6974c4d8-hr2jj   1/1     Running     4 (56m ago)   2d

```

```
$ kubectl get svc -n ingress-nginx
NAME                                 TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)                      AGE
ingress-nginx-controller             NodePort    10.96.105.209    <none>        80:32308/TCP,443:32427/TCP   90m
ingress-nginx-controller-admission   ClusterIP   10.103.121.152   <none>        443/TCP                      90m
```


```
$ minikube ip
192.168.49.2
```

#### Add row to file :  $ nano cat/hosts 

```192.168.49.2  javatechie.com```





### Testing 
```
 $ curl  http://javatechie.com/blog/allBlogs

[
  {
    "id": "1",
    "title": "title1",
    "content": "content1",
    "author": "author1"
  },
  {
    "id": "2",
    "title": "title2",
    "content": "content2",
    "author": "author2"
  }
]

$ curl http://javatechie.com/course/allCourses

[
  {
    "courseId": 1,
    "name": "course1",
    "price": 23.5
  },
  {
    "courseId": 2,
    "name": "course2",
    "price": 25.4
  }
]

```



### Conclusión

Este diagrama y la configuración de Ingress muestran cómo el navegador se comunica con el Ingress Controller, que a su vez dirige las solicitudes a los servicios de **Blog** y **Curso** según las rutas definidas. Si necesitas más detalles o ajustes, házmelo saber.

