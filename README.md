# 📊 Social Media Analytics Microservice

An intelligent, real-time microservice that provides analytical insights into user activity on a social media platform — including the most popular users, trending posts, and the latest content.

> This project leverages Spring Boot and React to deliver a fast, responsive, and scalable social media analysis dashboard.

---
![image](https://github.com/user-attachments/assets/25fa0aa4-cdeb-48c3-8c91-fe53770fbe7c)

## 🚀 Features

- 🔝 **Top Users**: Shows top 5 users with the most commented posts.
- 🔥 **Popular Posts**: Displays posts with the highest comment count.
- 🆕 **Latest Posts**: Real-time feed of the latest 5 posts.
- ⚡ **Optimized Performance**: Minimal API calls to reduce cost and maximize freshness.
- 📦 Fully decoupled architecture (Spring Boot backend + React frontend).

---

## 🛠️ Tech Stack

| Layer      | Technology       |
|------------|------------------|
| Backend    | Spring Boot 3    |
| Frontend   | React + Tailwind CSS |
| Language   | Java, JavaScript |
| Build Tool | Maven            |
| Data       | Consumed from test server API (no local DB) |
| Deployment | Easily deployable to Heroku / Netlify / Vercel |

---


## 📡 Backend API Endpoints

### 🔹 GET `/users`

Returns the top 5 users who have authored the most commented posts.

```json
[
  { "id": 1, "name": "John Doe" },
  { "id": 10, "name": "Helen Moore" }
]
