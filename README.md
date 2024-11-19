# Simple Inventory Store App

This is a simple inventory store application that provides basic inventory management features. It includes functionality for:

- **Adding Products**: Add new products to the inventory.
- **Updating Products**: Update details of existing products.
- **Getting Products**: Retrieve details of all or specific products.
- **Deleting Products**: Remove products from the inventory.

The application connects to a **MySQL** database to manage the data and uses **Docker Compose** for deployment and setup.

## Running the Application

1. Ensure you have **Docker** and **Docker Compose** installed on your system.
2. Clone the repository and navigate to the project directory.
3. Run the following command to start the application:

   ```bash
   docker-compose up
## Technologies Used

- **Backend**: Spring framework
- **Database**: MySQL 
- **Containerization**: Docker and Docker Compose

# Feedback for Development Tasks

## 1. Writing Docker Script

- **Was it easy to complete the task using AI?**  
  Yes, using AI to generate the Docker script was straightforward. It provided a great starting point.

- **How long did the task take you to complete?**  
  Approximately 30 minutes, including testing.

- **Was the code ready to run after generation? What did you have to change to make it usable?**  
  The script required minor modifications, such as correcting port configurations and ensuring MySQL credentials matched the application setup.

- **Which challenges did you face during completion of the task?**  
  Some initial issues occurred with container linking and ensuring the database was initialized correctly before the application started.

- **Which specific prompts you learned as a good practice to complete the task?**  
  Providing detailed prompts with environment variable requirements, volume configurations, and service dependencies yielded better results.

---

## 2. Writing Configuration Files

- **Was it easy to complete the task using AI?**  
  Yes, AI assistance made writing configuration files, such as `.env` or database connection settings, much quicker.

- **How long did the task take you to complete?**  
  About 15–20 minutes.

- **Was the code ready to run after generation? What did you have to change to make it usable?**  
  Minor changes were required to match specific project paths and database credentials.

- **Which challenges did you face during completion of the task?**  
  Ensuring consistency between generated configuration files and the application logic.

- **Which specific prompts you learned as a good practice to complete the task?**  
  Explicitly mentioning which configuration values (e.g., database name, user, password) need to be generated improved results.

---

## 3. Writing CRUD Logic

- **Was it easy to complete the task using AI?**  
  Moderately easy. The logic for CRUD operations was straightforward but required adaptation to the database schema.

- **How long did the task take you to complete?**  
  About 1.5–2 hours, including testing and debugging.

- **Was the code ready to run after generation? What did you have to change to make it usable?**  
  The generated code needed adjustments for specific SQL queries and API route definitions.

- **Which challenges did you face during completion of the task?**  
  Ensuring SQL queries were optimized and properly structured for the MySQL database.

- **Which specific prompts you learned as a good practice to complete the task?**  
  Providing schema details and examples of expected outputs helped refine the generated code.

---

## 4. Overall Challenges

- Resolving dependency initialization between the application and MySQL in Docker Compose.
- Managing database migrations and ensuring data persistence.

---

## Lessons Learned and Best Practices

- Providing clear and detailed prompts for AI generates better and more usable results.
- Iterating over the generated code and testing frequently ensures fewer runtime issues.
- Using AI as a starting point for repetitive tasks (like writing configuration files) significantly improves productivity.