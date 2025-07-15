Bosch E-Commerce Backend API
📋 Project Overview
Spring Boot REST API for Bosch e-commerce with:
✅ JWT Authentication
✅ Product Catalog
✅ Shopping Cart
✅ Swagger Documentation

🚀 Quick Start
Prerequisites
Java 21

MongoDB 6.0+

Run the App
bash
git clone https://github.com/djz231/Bosch_backend_project.git
cd Bosch_backend_project
./mvnw spring-boot:run
Debug mode:

bash
./mvnw spring-boot:run -Ddebug
🔧 Tech Stack
Framework: Spring Boot 3.2.5

Database: MongoDB

Auth: JWT

Docs: SpringDoc OpenAPI

🌐 API Endpoints
Endpoint	Method	Description
/api/auth/register	POST	Register user
/api/products	GET	List products
/api/cart	GET	View cart
📄 Swagger UI: http://localhost:8080/swagger-ui.html

⚙️ Configuration
properties
# application.properties
server.port=8080
spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce
jwt.secret=your-secret-key
🗃️ Database
Collections: users, products, carts

Sample Data: 20 Bosch tools auto-loaded

📜 License
