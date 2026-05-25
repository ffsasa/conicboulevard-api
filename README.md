# ConicBoulevard API

Backend API cho hệ thống tiếp nhận yêu cầu tư vấn (consultation) và quản lý tài khoản người dùng.

## Công nghệ sử dụng

- Java 17
- Spring Boot 3.1
- Spring Web / WebFlux
- Spring Security + JWT
- Spring Data JPA
- SQL Server (prod/dev), H2 (default)
- Spring Mail
- OpenAPI (Swagger UI)
- Maven

## Cấu trúc chính

```text
src/main/java/com/example/formapi
├── config          # Cấu hình Security, OpenAPI, Mail, WebClient...
├── controller      # REST Controllers
├── dto             # Request/Response DTOs
├── entity          # JPA entities
├── exception       # Exception và handler toàn cục
├── repository      # Spring Data repositories
├── security        # JWT filter/provider
└── service         # Business logic
```

## API chính

### Account
Base path: `/api/account`

- `POST /register` - Đăng ký tài khoản
- `POST /login` - Đăng nhập, trả về token/JWT

### Consultation
Base path: `/api/consultation`

- `POST /` - Tạo yêu cầu tư vấn
- `PUT /check/{id}` - Đánh dấu đã kiểm tra
- `PUT /delete/{id}` - Đánh dấu xóa mềm
- `GET /?projectName=...` - Lấy danh sách theo projectName (phân trang)
- `GET /health` - Health check nhanh

## Chạy dự án ở local

### 1) Yêu cầu

- JDK 17+
- Maven 3.8+
- SQL Server (nếu chạy profile `dev`/`prod`)

### 2) Cài đặt và chạy

```bash
mvn clean install
mvn spring-boot:run
```

Mặc định app đang bật profile `dev` trong `application.properties`.

## Cấu hình môi trường

- `src/main/resources/application.properties`: cấu hình mặc định + profile active
- `src/main/resources/application-dev.properties`: cấu hình dev (SQL Server, JWT, mail...)
- `src/main/resources/application-prod.properties`: cấu hình production qua biến môi trường

Khuyến nghị:
- Không hardcode secret/token/password trong file cấu hình.
- Dùng biến môi trường cho JWT secret, DB credentials, mail credentials.

## Swagger / OpenAPI

Sau khi chạy ứng dụng, truy cập tài liệu API tại:

- `http://localhost:8080/swagger-ui/index.html`

## Docker

Dự án đã có `Dockerfile`. Build và chạy nhanh:

```bash
docker build -t conicboulevard-api .
docker run -p 8080:8080 conicboulevard-api
```
