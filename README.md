# 📦 InventoryManager – Android App
**CS 360 – Mobile Architecture and Programming**  
**👤 Created by:** Alex Leet

![Repo Size](https://img.shields.io/github/repo-size/AlexLeetDev/InventoryApp-Android?style=flat-square)
![Last Commit](https://img.shields.io/github/last-commit/AlexLeetDev/InventoryApp-Android?style=flat-square)
![Platform](https://img.shields.io/badge/platform-Android-blue?style=flat-square)
![Status](https://img.shields.io/badge/status-Active-brightgreen?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-lightgrey?style=flat-square)

---

## 📝 Project Summary
**InventoryManager** is a lightweight and user-friendly Android app for tracking inventory items. It is ideal for personal and small business use, allowing users to add items, update their quantities, or delete them. It also includes an optional SMS notification feature to alert users when certain inventory thresholds are met. The design is clean and follows Material 3 principles for a modern and accessible experience. 

---

## 💻 Tech Stack
![Java](https://img.shields.io/badge/code-Java-orange?logo=java&logoColor=white&style=flat-square)
![SQLite](https://img.shields.io/badge/database-SQLite-blue?logo=sqlite&logoColor=white&style=flat-square)
![Android Studio](https://img.shields.io/badge/IDE-Android%20Studio-brightgreen?logo=android-studio&logoColor=white&style=flat-square)
![Material 3](https://img.shields.io/badge/UI-Material%20Design%203-00C853?logo=material-design&logoColor=white&style=flat-square)
![XML](https://img.shields.io/badge/Layout-XML-ff69b4?style=flat-square)
![SMS Permission](https://img.shields.io/badge/Feature-SMS%20Permission-9C27B0?style=flat-square)

---

## 💭 Reflection

### 🎯 What was the goal of this app?
The app was built to allow users to manage an inventory list from their Android device. The goal was to add an intuitive tool for adding, updating, and deleting items. Additionally, the app allows users to receive SMS alerts about their inventory, providing a helpful notificaton feature.

### 📱 What screens and features were included?
- **Login screen** – For user authentication
- **Create account flow** – For first-time users
- **Dashboard** – A grid-style display of items with buttons to update quantities or delete entries
- **SMS Permission screen** – Allows users to grant permission and send a test SMS

These screens were designed to be simple and functional, focusing on user-centered design. The layout is consistent, with clear labels, helpful hints, and a logical flow.

### 🧑‍💻 How did I build the app?
The app was developed using Java in Android Studio. I used components such as `RecyclerView`, `MaterialButton`, and `TextInputLayout` for UI, and `SQLite` for local data storage. I added runtime permission handling for SMS functionality and structured the code using a clean separation between logic and UI.

### 🧪 How did I test it?
To make sure the app worked correctly and felt smooth to use, I tested it step-by-step on both an Android emulator and a real device. Here's what I checked:

- **Tested login and account creation** using both valid and invalid credentials
- **Verified item management**:
  - Added new inventory items
  - Updated item quantities (increase/decrease)
  - Delete items from the list
- **Checked duplicate entry handling** – confirmed that the app shows a message if the item already exists
- **Tested empty input cases** – made sure the app shows warnings if the input is left blank
- **Tested SMS permission flow**:
  - Permission requested only when needed
  - Verified SMS was sent only if permission was granted
  - Confirmed the app continues to work even if permission is denied

### 💡 Where did I have to get creative?
Getting the **Remember Me** checkbox to work properly presented some challenges. I wanted users to stay logged in if they selected that option, so they wouldn't have to enter their username and password every time. At first, I wasn't sure how to save that login state between sessions.

I eventually figured out how to use **shared preferences** to store the user's login info when the checkbox is checked. Then, when the app starts, it checks if the user chose to be remembered. If they did, it skips the login screen and goes straight to the dashboard.

### 🌟 What part am I most proud of?
I’m most proud of the **dashboard** screen where users can see all their items. It shows the item names and quantities, and the buttons let users update or delete items right away. It felt great to see everything update in real time when I got it working.

---

## 🖼️ App Screenshots

### 🔐 Login Screen  
[<img src="screenshots/01_login_screen.png" alt="Login Screen" width="400"/>](screenshots/01_login_screen.png)

### 🧾 Create Account  
[<img src="screenshots/02_create_account.png" alt="Create Account" width="400"/>](screenshots/02_create_account.png)

### 🧺 Empty Dashboard  
[<img src="screenshots/03_dashboard_empty.png" alt="Empty Dashboard" width="400"/>](screenshots/03_dashboard_empty.png)

### ➕ Item Added  
[<img src="screenshots/04_item_added.png" alt="Item Added" width="400"/>](screenshots/04_item_added.png)

### 📲 SMS Permission Screen  
[<img src="screenshots/05_sms_permission.png" alt="SMS Permission Screen" width="400"/>](screenshots/05_sms_permission.png)

### 🔔 Permission Popup  
[<img src="screenshots/06_sms_permission_popup.png" alt="Permission Popup" width="400"/>](screenshots/06_sms_permission_popup.png)

### ✅ Permission Granted  
[<img src="screenshots/07_sms_permission_granted.png" alt="Permission Granted" width="400"/>](screenshots/07_sms_permission_granted.png)

### 👁️ Password Toggle  
[<img src="screenshots/08_password_toggle.png" alt="Password Toggle" width="400"/>](screenshots/08_password_toggle.png)

### 📨 Test SMS Sent  
[<img src="screenshots/09_test_sms.png" alt="Test SMS Sent" width="400"/>](screenshots/09_test_sms.png)

---

## 🔗 Submission
This app was developed as part of **CS 360 – Mobile Architecture and Programming**. It reflects what I’ve learned about Android app development, including UI design, local storage with SQLite, and runtime permission management.


 

