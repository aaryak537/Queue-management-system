# 🚦 FlowQ — Smart Queue Management System

<p align="center">

<img src="https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android" />
<img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk" />
<img src="https://img.shields.io/badge/UI-XML%20%7C%20Material%20Design-blue?style=for-the-badge&logo=materialdesign" />
<img src="https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase" />
<img src="https://img.shields.io/badge/Database-Firestore-FFA000?style=for-the-badge&logo=firebase" />
<img src="https://img.shields.io/badge/Min%20SDK-26-success?style=for-the-badge" />

</p>

<p align="center">
  <b>A Smart, Real-Time Digital Queue Management System</b>
</p>

<p align="center">
  Replace physical waiting lines with digital tokens, live queue tracking and intelligent waiting-time visibility.
</p>

<p align="center">
  <a href="#-overview">Overview</a> •
  <a href="#-features">Features</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-setup">Setup</a>
</p>

---

## 📖 Table of Contents

<details>
<summary><b>Click to expand</b></summary>

* [🚦 FlowQ — Smart Queue Management System](#-flowq--smart-queue-management-system)
* [📖 Table of Contents](#-table-of-contents)
* [🎯 Overview](#-overview)
* [❗ Problem Statement](#-problem-statement)
* [💡 Proposed Solution](#-proposed-solution)
* [🎯 Aim](#-aim)
* [✨ Features](#-features)
* [🛠️ Technology Stack](#️-technology-stack)
* [⚡ Real-Time Queue Updates](#-real-time-queue-updates)
* [🔐 Security](#-security)
* [📂 Project Structure](#-project-structure)
* [⚙️ Setup](#️-setup)
* [🚀 Getting Started](#-getting-started)
* [💎 What Makes FlowQ Different](#-what-makes-flowq-different)
* [📊 Feature Comparison](#-feature-comparison)
* [🔮 Planned Additions](#-planned-additions)
* [🤝 Contribution](#-contribution)
* [📜 License](#-license)
* [⭐ Support](#-support)

</details>

---

# 🎯 Overview

**FlowQ** is a mobile-based **Smart Queue Management System** designed to digitize traditional physical queues.

Instead of standing in a crowded physical line, customers can:

* 📲 Register and log in
* 🏢 Select a service
* 🎟️ Receive a digital token
* 📍 Track their live queue position
* 👥 See the number of people ahead
* ⏱️ View estimated waiting time
* 🔔 Receive queue notifications
* 📜 View their queue history

Administrators receive a dedicated dashboard to:

* 📊 Monitor queues
* 🎟️ Call tokens
* 📈 View queue statistics
* 📢 Send announcements
* ⚙️ Manage services and queue settings

> **FlowQ = Digital Tokens + Live Queue Tracking + Smart Waiting-Time Visibility**

---

# ❗ Problem Statement

Traditional queue systems create several problems:

| Problem                 | Description                                                           |
| ----------------------- | --------------------------------------------------------------------- |
| 🧍 Long Physical Queues | Customers spend time standing in banks, hospitals and service centers |
| ❓ No Visibility         | Customers cannot easily know their position or expected waiting time  |
| 📝 Manual Management    | Staff often depend on manual tokens or basic displays                 |
| 😓 Poor Experience      | Physical waiting leads to wasted time and crowding                    |

FlowQ addresses these problems by converting the traditional physical queue into a digital, remotely trackable queue.

---

# 💡 Proposed Solution

FlowQ provides a mobile-based digital queue experience.

### 👤 Customer

```text
Register / Login
       ↓
Select Service
       ↓
Receive Digital Token
       ↓
Track Live Position
       ↓
Monitor Waiting Time
       ↓
Receive Notification
       ↓
Token Called
```

### 🛡️ Administrator

```text
Secure Login
      ↓
Live Dashboard
      ↓
Call Next Token
      ↓
Send Announcement
      ↓
Monitor Statistics
      ↓
Manage Settings
```

---

# 🎯 Aim

> **To develop a smart, mobile-based queue management application that reduces unnecessary physical waiting for customers while giving administrators a real-time system to manage and monitor queues.**

---

# ✨ Features

<table>
<tr>
<td>🎟️ Digital Token Generation</td>
<td>🔄 Real-Time Queue Updates</td>
</tr>

<tr>
<td>📍 Live Queue Position Tracking</td>
<td>⏱️ Estimated Waiting Time</td>
</tr>

<tr>
<td>🏢 Multiple Service Categories</td>
<td>📜 User Queue History</td>
</tr>

<tr>
<td>📊 Administrator Dashboard</td>
<td>🎛️ Token Calling & Queue Control</td>
</tr>

<tr>
<td>📈 Queue Statistics & Charts</td>
<td>🔔 Push Notifications</td>
</tr>

<tr>
<td>📢 Remote Announcements</td>
<td>🔐 Role-Based Access Control</td>
</tr>
</table>

---

# 👤 Customer Module

The customer-facing application allows users to manage their queue experience remotely.

### Core capabilities

* 🔐 User registration and login
* 🏢 Service selection
* 🎟️ Digital token generation
* 📍 Live queue position
* 👥 People-ahead count
* ⏱️ Estimated waiting time
* 🔔 Queue notifications
* 📜 Queue history

---

# 🛡️ Administrator Module

The administrator receives a dedicated interface for real-time queue management.

### Core capabilities

* 🔐 Secure administrator login
* 📊 Live dashboard
* 🎟️ Call next token
* 🎛️ Queue control
* 📢 Send announcements
* 📈 Monitor queue statistics
* ⚙️ Manage service categories
* 🔐 Role-restricted access

---

# 🔄 Workflow

## 👤 User Flow

```text
┌──────────────────┐
│ Register / Login │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Select Service   │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Digital Token    │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Live Position    │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Notification     │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Token Called     │
└──────────────────┘
```

## 🛡️ Admin Flow

```text
┌──────────────────┐
│ Secure Login     │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Live Dashboard   │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Call Next Token  │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Announcement     │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Statistics       │
└────────┬─────────┘
         ↓
┌──────────────────┐
│ Settings         │
└──────────────────┘
```

---

## Architecture Layers

### 📱 Client Layer

**User App**

* Registration / Login
* Service selection
* Queue joining
* Live position
* People ahead
* Waiting time
* Queue history

**Administrator Dashboard**

* Secure role-restricted login
* Live statistics
* Call next / selected token
* Category management
* Announcements

### 🌐 Communication Layer

* REST API over HTTPS
* Firestore real-time listeners
* Firebase Cloud Messaging

### ☁️ Backend Layer

**Firebase Authentication**

Handles secure registration, login and role-based access.

**Cloud Firestore**

Stores:

* Queue documents
* Tokens
* Service categories
* User profiles
* Queue history

**Firebase Cloud Messaging**

Used for:

* "You are next" alerts
* Token-called notifications
* Administrator announcements

---

# 🛠️ Technology Stack

## 📱 Application Layer

| Technology                   | Purpose                 |
| ---------------------------- | ----------------------- |
| Android Studio               | Android development     |
| Java                         | Application programming |
| XML                          | User interface          |
| Material Design              | UI components           |
| Android Navigation Component | Navigation              |
| MPAndroidChart               | Dashboard charts        |

## 🔥 Backend & Cloud

| Technology               | Purpose            |
| ------------------------ | ------------------ |
| Firebase Authentication  | Authentication     |
| Firebase                 | Backend services   |
| Firebase Cloud Messaging | Push notifications |

## ⚙️ Platform & Tools

| Technology           | Purpose                    |
| -------------------- | -------------------------- |
| Firestore Listeners  | Real-time updates          |
| REST API over HTTPS  | API communication          |
| Git                  | Version control            |
| GitHub               | Repository & collaboration |
| Android 8.0 / API 26 | Minimum platform           |

---

# 🔥 Firebase Architecture

```text
                    FLOWQ
                      │
          ┌───────────┴───────────┐
          │                       │
       USER APP               ADMIN APP
          │                       │
          └───────────┬───────────┘
                      │
                 FIREBASE
                      │
       ┌──────────────┼──────────────┐
       │              │              │
       ▼              ▼              ▼
 Authentication   Firestore         FCM
       │              │              │
       ▼              ▼              ▼
   Login & Roles   Queue Data     Notifications
                   Tokens
                   Services
                   History
```

---

# ⚡ Real-Time Queue Updates

One of FlowQ's important capabilities is **live queue visibility**.

Firestore listeners allow the application to receive queue changes without repeatedly refreshing the screen.

```text
Admin calls token
       ↓
Firestore updated
       ↓
Real-time listener detects change
       ↓
Customer app updates
       ↓
Position / people ahead changes
       ↓
Notification can be delivered
```

This enables customers to monitor the queue remotely instead of continuously checking a physical display.

---

# 🔐 Security

FlowQ incorporates security at the Firebase/backend level.

### Security components

* 🔐 Firebase Authentication
* 🛡️ Firestore Security Rules
* 🧩 Firebase App Check
* 👤 Role-based access control
* 🔒 HTTPS communication

The administrator functionality is restricted to authorized users.

---

# 📂 Project Structure

A suggested Android project organization:

```text
FlowQ/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   ├── navigation/
│           │   ├── values/
│           │   └── mipmap/
│           │
│           └── AndroidManifest.xml
│
├── screenshots/
│
├── assets/
│
├── README.md
├── LICENSE
└── .gitignore
```

> Update this structure to match the actual repository once the implementation is finalized.

---

# ⚙️ Setup

## 1️⃣ Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

```bash
cd FlowQ
```

---

## 2️⃣ Open in Android Studio

1. Open **Android Studio**
2. Select **Open**
3. Choose the cloned FlowQ project
4. Allow Gradle synchronization to complete

---

## 3️⃣ Configure Firebase

Create/configure a Firebase project and connect the Android application.

Required Firebase components:

* Firebase Authentication
* Firebase Cloud Messaging

---

## 5️⃣ Configure Firestore

Create the required Firestore collections/documents for:

```text
Users
Services
Queues
Tokens
History
Announcements
```

> The exact database structure should follow the implementation in the repository.

---

## 6️⃣ Run the Application

Connect an Android device or start an Android emulator.

Then run:

```text
▶ Run 'app'
```

Minimum supported platform:

```text
Android 8.0
API 26
```

---

# 🚀 Getting Started

### 👤 For Customers

```text
1. Create an account
2. Login
3. Select a service
4. Join the queue
5. Receive a digital token
6. Track your position
7. Monitor waiting time
8. Receive notification
9. Attend when your token is called
```

### 🛡️ For Administrators

```text
1. Login securely
2. Open dashboard
3. Monitor active queue
4. Call next token
5. Send announcements
6. Monitor statistics
7. Manage queue settings
```

---

# 💎 What Makes FlowQ Different?

FlowQ is designed around a simple idea:

> **Customers should not have to physically stand in a queue just to wait for their turn.**

### 🚫 No Hardware Dependency

FlowQ is designed to operate through software using the customer's phone and the operator's phone/tablet.

### ⚡ Real-Time Position

The system uses Firestore listeners to provide live queue information.

### 🏪 Built for a Single Branch

The concept is suitable for a small clinic, branch, service center or shop without requiring an enterprise-scale installation.

### 💰 Low-Cost Architecture

The project uses Firebase as its backend rather than requiring a self-hosted server or dedicated queue hardware.

---

# 📊 Feature Comparison

| Feature                 | Existing Queue Systems  | FlowQ                        |
| ----------------------- | ----------------------- | ---------------------------- |
| Queue Joining           | App / Web / Kiosk       | 📱 Mobile App                |
| Physical Waiting        | Often Required          | ✅ Virtual Waiting            |
| Live Tracking           | Basic Status            | 📍 Live Token & Position     |
| Waiting-Time Prediction | Limited / No            | 🤖 Intelligent Prediction    |
| Admin Management        | Available               | 📊 Dashboard + Queue Control |
| Infrastructure          | Hardware / Subscription | ☁️ Firebase-based            |

> FlowQ focuses on combining digital queue management with intelligent waiting-time visibility.

---

# 🔮 Planned Additions

The project can be extended with additional capabilities during development.

### Planned features

* ⭐ Post-service feedback rating
* ⏰ "Running late / Request more time"
* 📱 QR walk-in check-in

These additions can be introduced without changing the core FlowQ queue-management workflow.

---

# 🧠 Project Concept

```text
                 ┌────────────────────┐
                 │      CUSTOMER      │
                 └─────────┬──────────┘
                           │
                     Join Queue
                           │
                           ▼
                 ┌────────────────────┐
                 │   DIGITAL TOKEN    │
                 └─────────┬──────────┘
                           │
                           ▼
                 ┌────────────────────┐
                 │  LIVE QUEUE STATUS │
                 └─────────┬──────────┘
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
       People Ahead              Waiting Time
              │                         │
              └────────────┬────────────┘
                           │
                           ▼
                    🔔 NOTIFICATION
                           │
                           ▼
                    🎟️ TOKEN CALLED
```

---

# 👨‍💻 Development

FlowQ is designed as a collaborative Android project.

### Development stack

```text
Android Studio
      +
Java
      +
XML / Material Design
      +
Firebase
      +
Git & GitHub
```

Team members can work on separate modules and contribute through GitHub branches and pull requests.

---

# 🤝 Contribution

Contributions are welcome.

### Recommended workflow

```text
Fork
  ↓
Create Branch
  ↓
Make Changes
  ↓
Commit
  ↓
Push
  ↓
Pull Request
  ↓
Review
  ↓
Merge
```

Example:

```bash
git checkout -b feature/queue-management
```

```bash
git add .
git commit -m "Add queue management feature"
```

```bash
git push origin feature/queue-management
```

Then create a Pull Request on GitHub.

---

# 🐛 Issues & Suggestions

Found a bug or have an improvement idea?

Use the GitHub **Issues** section to report:

* 🐛 Bugs
* 💡 Feature requests
* 🎨 UI improvements
* ⚡ Performance issues
* 🔐 Security concerns

---


# 🏆 Project Goals

FlowQ aims to provide:

```text
Less Physical Waiting
        +
Better Queue Visibility
        +
Simpler Administration
        +
Real-Time Communication
        =
Better Queue Experience
```

---

# 📜 License

This project is developed as a final-year academic project.

---

# ⭐ Support the Project

If you find **FlowQ** interesting:

⭐ Star the repository
🍴 Fork the project
🐛 Report issues
💡 Suggest improvements
🤝 Contribute to development

---

<h2 align="center">🚦 FlowQ</h2>

<p align="center">
<b>Smart Queue Management System</b>
</p>

<p align="center">
Digital Tokens • Live Queue Tracking • Waiting-Time Visibility • Admin Control
</p>

<p align="center">
Made with ❤️ using Android + Java + Firebase
</p>

---

<p align="center">
  <sub>FlowQ — Making queues smarter, one token at a time.</sub>
</p>
