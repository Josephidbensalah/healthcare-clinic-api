## 🏥 Healthcare Clinic REST API
This is a Spring Boot 3.x backend built to handle clinic operations like patient registration, doctor management, and appointment scheduling. The focus here was on keeping the data logic clean and the performance tight.
------------------------------
## 🚀 How it Works## 🛠 Manual Data Mapping (The "No-Library" Approach)
Instead of relying on magic libraries like MapStruct or ModelMapper, I wrote custom mapping logic.

* Why? It gives us total control over Partial Updates.
* The Benefit: If you only want to update a patient's email, you just send the email. The mapper checks for nulls and won't overwrite your existing database data with empty values.

## 🔐 Security & "Live" Sessions

* JWT Auth: We're using stateless JSON Web Tokens. You log in, get a token, and use it for every request.
* Logout (Redis Blacklist): Since JWTs don't "expire" on the server naturally, I integrated Redis. When someone logs out, their token is blacklisted in Redis until its natural expiration time, effectively killing the session.

## ⚡ Performance Tweaks

* Killing the N+1 Problem: When fetching a list of patients with their appointments, JPA usually runs way too many queries. I fixed this using Entity Graphs to pull everything in one single SQL JOIN.
* Caching: The patient list is cached in Redis. This means we aren't hitting the database every single time a user refreshes the page.
* Async Tasks: Notifications (like appointment confirmations) run on background threads using @Async. The user gets their response immediately, and the "heavy lifting" happens in the back.

------------------------------
## 📦 Running Redis with Docker
Since the project uses Redis for caching and session security, you need it running. The easiest way is using Docker:

# Pull and run Redis on the default port
docker run -d --name redis-server -p 6379:6379 redis:latest
# (Optional) Verify it's running
docker exec -it redis-server redis-cli ping# Should return "PONG"

If you prefer a visual UI to see your cached data, run Redis Stack instead:

docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest# Access UI at http://localhost:8001

------------------------------
## 📡 The Endpoints

| Category | Method | Path | What it does |
|---|---|---|---|
| Auth | POST | /api/auth/signin | Exchange credentials for a JWT. |
| Auth | POST | /api/auth/logout | Kills the session via Redis blacklist. |
| Patient | GET | /api/patients/all | Pulls the full (cached) patient list. |
| Patient | PUT | /api/patients/update | Updates specific fields via National ID. |
| Appt | POST | /api/appointments/schedule | Books a slot and triggers an async alert. |

------------------------------
## ⚙️ The Environments (Profiles)
I set up Spring Profiles to make moving between dev and production easy:

* Dev/Test: Uses H2 In-Memory DB. It's fast and requires zero setup.
* Prod: Swaps over to MySQL and enables full Redis caching for a real-world environment.

------------------------------
## 🧪 Testing & Logs

* Unit Tests: Written with Mockito to test business logic (like preventing duplicate National IDs) without needing a database.
* Postman: I've set up scripts that automatically grab the JWT from the login response and stick it into the headers for all other requests.
* Logging: Everything is tracked via SLF4J. Errors go to a dedicated clinic-app.log file, and in dev mode, you can see the exact SQL parameters in the console.

------------------------------
## 📖 Swagger Docs
Once the app is up, you can hit the interactive docs here:
👉 http://localhost:8080/swagger-ui.html

