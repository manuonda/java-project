# Sistema de Acortador de URLs (URL Shortener System)

Este proyecto es un **sistema de acortador de URLs** construido con **Spring Modulith**, que implementa una arquitectura modular limpia y escalable. Permite a los usuarios convertir URLs largas en versiones más cortas y manejables, con gestión de usuarios y notificaciones integradas.

## 🎯 Características

- ✅ Acortamiento de URLs largas con validación
- ✅ Redirección automática a la URL original
- ✅ Interfaz web intuitiva (Thymeleaf + Bootstrap 5)
- ✅ Almacenamiento en PostgreSQL
- ✅ Estadísticas de uso (número de clics)
- ✅ Gestión de usuarios con autenticación
- ✅ Sistema de notificaciones por eventos
- ✅ Historial de URLs por usuario
- ✅ Expiración automática de URLs
- ✅ URLs públicas y privadas

---

## 🏗️ Arquitectura: Spring Modulith

### ¿Qué es Spring Modulith?

Spring Modulith es un framework que proporciona **arquitectura guiada por el dominio** con validación automática de límites de módulos. En lugar de múltiples módulos Maven, utilizamos **un solo proyecto con estructura de paquetes clara**.

### Estructura de Módulos

```
com.manuonda.urlshortener
│
├── 📦 shorturl/                    ← Módulo: Acortador de URLs
│   ├── internal/
│   │   ├── ShortUrlService.java
│   │   ├── ShortUrlRepository.java
│   │   ├── UrlExistenceValidator.java
│   │   └── EntityMapper.java
│   ├── api/
│   │   ├── ShortUrlDto.java
│   │   └── CreateShortUrlCmd.java
│   └── ShortUrlCreatedEvent.java   ← Evento público
│
├── 📦 user/                        ← Módulo: Gestión de Usuarios
│   ├── internal/
│   │   ├── UserService.java
│   │   ├── UserRepository.java
│   │   └── UserValidator.java
│   ├── api/
│   │   ├── UserDto.java
│   │   └── CreateUserCmd.java
│   └── UserCreatedEvent.java       ← Evento público
│
├── 📦 notification/                ← Módulo: Notificaciones
│   ├── internal/
│   │   ├── NotificationService.java
│   │   └── EmailSender.java
│   └── NotificationSentEvent.java  ← Evento publicado
│
├── 🌐 web/                         ← Capa de Presentación (Controllers + Vistas)
│   ├── controller/
│   │   ├── ShortUrlController.java
│   │   ├── UserController.java
│   │   └── HomeController.java
│   ├── dto/
│   │   └── CreateShortUrlForm.java
│   ├── templates/                  ← 🎨 Vistas Thymeleaf
│   │   ├── layout.html             (Base layout compartida)
│   │   ├── index.html              (Home)
│   │   ├── urls/
│   │   │   ├── list.html
│   │   │   └── create.html
│   │   ├── users/
│   │   │   ├── register.html
│   │   │   └── profile.html
│   │   └── error/
│   │       └── 404.html
│   └── static/                     ← CSS, JS
│       └── css/
│           └── style.css
│
└── 🔧 config/
    └── ApplicationProperties.java
```

---

## 🌐 Gestión de la Capa Web (Thymeleaf)

### ¿Quién maneja las vistas?

El **módulo `web/`** es responsable de:
- ✅ Controladores MVC (`@Controller`)
- ✅ Vistas Thymeleaf (`.html`)
- ✅ Formularios y validación de entrada
- ✅ Manejo de redirecciones y modelos

### Flujo de Interacción

```
Usuario
  ↓
Navegador (http://localhost:8080)
  ↓
Controller (web/controller/ShortUrlController.java)
  ↓
Service (shorturl/internal/ShortUrlService.java)
  ↓
Repository (shorturl/internal/ShortUrlRepository.java)
  ↓
Base de Datos (PostgreSQL)
  ↓
Respuesta con Vista Thymeleaf (web/templates/*.html)
  ↓
Navegador (Renderiza HTML)
```

### Ejemplo: Crear una URL Corta

#### 1. **Controlador** (web/controller/ShortUrlController.java)
```java
@Controller
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("shortUrls",
            shortUrlService.findAllPublicShortUrls());
        model.addAttribute("createShortUrlForm",
            new CreateShortUrlForm("", false, 30));
        return "index"; // 🎨 Renderiza templates/index.html
    }

    @PostMapping("/short-urls")
    public String createShortUrl(@Valid CreateShortUrlForm form,
                                BindingResult result,
                                Model model) {
        // Convierte Form → Command
        CreateShortUrlCmd cmd = new CreateShortUrlCmd(
            form.originalUrl(),
            form.isPrivate(),
            form.expirationInDays(),
            null
        );

        // Delega al servicio del módulo shorturl
        ShortUrlDto dto = shortUrlService.createShortUrl(cmd);

        // Retorna vista actualizada
        return "redirect:/";
    }
}
```

#### 2. **Vista Thymeleaf** (web/templates/index.html)
```html
<div th:if="${shortUrls != null}">
    <table class="table">
        <tr th:each="url : ${shortUrls}">
            <td th:text="${url.shortKey()}"></td>
            <td th:text="${url.originalUrl()}"></td>
            <td th:text="${url.clickCount()}"></td>
        </tr>
    </table>
</div>

<form th:object="${createShortUrlForm}" method="post" action="/short-urls">
    <input th:field="*{originalUrl}" type="url" required />
    <button type="submit">Acortar URL</button>
</form>
```

#### 3. **Servicio** (shorturl/internal/ShortUrlService.java)
El controlador **NO accede directamente** a repositories. Utiliza el servicio que publica eventos:
```java
@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final ApplicationEventPublisher events;

    @Transactional
    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        ShortUrl shortUrl = new ShortUrl();
        // ... crear lógica
        repository.save(shortUrl);

        // 📤 Publica evento para notificaciones
        events.publishEvent(new ShortUrlCreatedEvent(
            shortUrl.getShortKey(),
            shortUrl.getOriginalUrl(),
            cmd.userId(),
            shortUrl.getCreatedAt()
        ));

        return mapper.toDto(shortUrl);
    }
}
```

#### 4. **Comunicación entre Módulos** (notification/internal/NotificationService.java)
```java
@Service
public class NotificationService {

    private final EmailSender emailSender;

    @ApplicationModuleListener  // ✅ Escucha eventos de otros módulos
    void onShortUrlCreated(ShortUrlCreatedEvent event) {
        log.info("URL creada: {}", event.shortKey());
        emailSender.send(
            "URL acortada exitosamente",
            "Tu URL está disponible como: " + event.shortKey()
        );
    }
}
```

---

## 📊 Responsabilidades por Módulo

| Módulo | Responsabilidad | Controladores | BD |
|--------|-----------------|---|---|
| **shorturl** | Lógica de acortamiento | ❌ No | ✅ Sí |
| **user** | Gestión de usuarios | ❌ No | ✅ Sí |
| **notification** | Envío de eventos | ❌ No | ❌ No |
| **web** | 🌐 Vistas + Controllers | ✅ Sí | ❌ No |

---

## 🔀 Comunicación entre Módulos (Eventos)

```
┌─────────────────────────────────────────────────────┐
│  shorturl/ShortUrlCreatedEvent (evento público)     │
│  • shortKey: String                                 │
│  • originalUrl: String                              │
│  • userId: Long                                     │
│  • createdAt: Instant                               │
└────────────────┬────────────────────────────────────┘
                 │ (Publicado por ShortUrlService)
                 ↓
        ┌────────────────────┐
        │ NotificationService│
        │ (escucha evento)   │
        └────────────────────┘
                 │
                 ↓
        Envía email al usuario
        ✉️ "Tu URL fue creada"
```

---

## 🛠️ Tecnologías Utilizadas

| Aspecto | Tecnología |
|---------|-----------|
| **Lenguaje** | Java 21 |
| **Framework** | Spring Boot 3.5.6 |
| **Modularity** | Spring Modulith 1.3+ |
| **Persistencia** | Spring Data JPA + Hibernate |
| **Base de Datos** | PostgreSQL |
| **Vistas** | Thymeleaf + Bootstrap 5 |
| **Validación** | Hibernate Validator |
| **Build** | Maven |
| **Control de versiones** | Git |

---

## 📦 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <tu-repo>
   cd spring-boot-url-shortener
   ```

2. **Requisitos previos**
   - Java 21+
   - Maven 3.8+
   - PostgreSQL 13+

3. **Configurar base de datos**
   Editar `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/urlshortener
   spring.datasource.username=postgres
   spring.datasource.password=tuContraseña
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

5. **Acceder a la aplicación**
   - 🌐 http://localhost:8080

---

## 🚀 Uso

### Crear una URL corta
1. Ir a http://localhost:8080
2. Ingresar una URL larga
3. Hacer clic en "Acortar URL"
4. Copiar la URL corta generada
5. Compartir: http://localhost:8080/s/{shortKey}

### Acceder a una URL acortada
- Visitar http://localhost:8080/s/abc123
- Redirección automática a la URL original

---

## 🧪 Testing de Arquitectura

Spring Modulith incluye validación automática de límites:

```java
@Test
void testModuleStructure() {
    ApplicationModules modules = ApplicationModules.of(
        SpringBootUrlShortenerApplication.class);

    // ✅ Verifica que:
    // - Los módulos respeten sus límites
    // - No haya dependencias circulares
    // - Los paquetes internos no sean accesibles
    modules.verify();
}
```

---

## 🎓 Principios de Arquitectura

### Isolamiento de Módulos
- Cada módulo solo puede acceder a las APIs públicas (paquete raíz)
- Los paquetes `internal/` son privados
- Los eventos facilitan la comunicación desacoplada

### Escalabilidad
- Agregar nuevos módulos sin afectar los existentes
- Fácil migración a microservicios en el futuro
- Límites claros y verificables

### Mantenibilidad
- Código organizado por dominio (dominio-driven)
- Responsabilidades claras
- Dependencias inversas y explícitas

---

## 📚 Recursos

- [Spring Modulith Documentation](https://docs.spring.io/spring-modulith/reference/)
- [Spring Modulith Getting Started](https://spring.io/projects/spring-modulith/)
- [Baeldung - Spring Modulith Guide](https://www.baeldung.com/spring-modulith)

---

## 👨‍💻 Autor

**Manuonda** - Implementación de System Design con Spring Boot y Modulith