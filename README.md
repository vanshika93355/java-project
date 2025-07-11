# 🏨 Hotel Management System (Java + Swing)

This is a beginner-friendly **Hotel/Hostel Room Booking System** created using **Java** and **Swing** for GUI. It allows customers to book rooms, cancel bookings, and view available rooms — complete with image previews for each room.

> Built during my first year to learn object-oriented design and Java GUI basics.

---

## ✨ Features

- Book a room with name & contact info
- Cancel an existing booking
- Check room availability with image previews
- Visual interface using `JOptionPane` and `JFrame`
- Tracks booked vs available rooms
- Image changes for booked/unbooked rooms

---

## ⚙️ Technologies Used

- Java (JDK 8+)
- Swing (Java GUI)
- OOP concepts: Classes, Objects, Lists, Inheritance
- Basic Image Handling with `ImageIcon`

---

## 🔍 Code Overview

### 📁 Classes:

- **Room**: Handles room status, number, and image display.
- **Customer**: Stores customer name and contact details.
- **Booking**: Associates a room with a customer.
- **HOTEL**: Main controller class that:
  - Manages list of rooms and bookings
  - Handles room booking, cancellation, and availability
- **HotelManagementApp**: Entry point with menu-driven UI using `JOptionPane`.

### 🖼 GUI:
- `JOptionPane` for user input/alerts.
- `JFrame + JPanel + JLabel` for displaying available rooms with images.

---

## 🚀 How to Run

1. **Clone the repo:**
   ```bash
   git clone https://github.com/vanshika93355/hotel-management-java.git
