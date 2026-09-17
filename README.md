# Running project locally #
To run project on your machine clone the repo and follow below instructions for backend and frontend
### Set up Backend ###
1. Copy env.sample and name it .env then link it as enviroment variables file in your IDE
2. Download and run Docker to enable easy database setup
3. Download and use JDK 21+ (Liberica preffered)
4. Create Resend account for email service
    - copy api key to dotenv
    ```
    SPRING_MAIL_API_KEY=<your resend mail api key>
    SPRING_MAIL_USERNAME=onboarding@resend.dev (leave as is)
    ```
    - this setup allows only sending mails to mail that account was created on
    - to get flexibility to send mails to every possible mailbox not just your mail follow steps in resend page for adding your domain and then set your sending email as:
    ```
    SPRING_MAIL_USERNAME=any_name_you_want@your_domain
    ```

5. To run the application locally create run configuration for Spring Boot app or use commands below in backend folder:
```
./mvnw spring-boot:run       # macOS/Linux
mvnw.cmd spring-boot:run     # Windows
```
Use `generated-requests.http` for testing endpoints

### Set up Frontend ###
1. Copy env.sample and name it .env
2. Install node and npm to check installation run:
```
node -v
npm -v
```
3. Install js modules (npm packages):
```
npm install -D
```
4. Run Frontend locally:
```
npm run dev
```
Frontend should be hosted on `localhost:5173` by default