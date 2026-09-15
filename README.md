# Lab 9: Spring Boot - Transaction (ระบบฝากเงินอย่างง่าย)

โปรเจกต์นี้เป็นส่วนหนึ่งของวิชา **การพัฒนาเว็บเซอร์วิสและเว็บแอปพลิเคชัน**  
เน้นศึกษาและทดลองการควบคุมธุรกรรมฐานข้อมูล (Transaction Management) ด้วย `@Transactional` ใน Spring Boot เพื่อให้การทำงานแบบ **All or Nothing** เมื่อเกิดข้อผิดพลาดจะทำการ Rollback อัตโนมัติ

---

## 👤 ข้อมูลผู้จัดทำ

* **ชื่อ-นามสกุล:** แทนคุณ พันธ์นิกุล
* **รหัสนักศึกษา:** 673380301-0
* **Section:** 1

---

## 🛠️ เทคโนโลยีที่ใช้ (Tech Stack)

* **Language:** Java 17
* **Framework:** Spring Boot (Spring Web, Spring Data JPA)
* **Database:** PostgreSQL
* **Tool:** Postman, pgAdmin 4, Maven

---

## 🗄️ โครงสร้างฐานข้อมูล (Database Schema)

ความสัมพันธ์แบบ **One-to-Many** (1 บัญชี มีประวัติการฝากเงินได้หลายรายการ):

* **`account`**: เก็บข้อมูลบัญชี (`id` PK, `account_number`, `owner_name`, `balance`)
* **`deposit_transaction`**: เก็บประวัติการฝาก (`id` PK, `amount`, `account_id` FK อ้างอิงไปยัง `account`)

---

## 🚀 API Endpoints

| Method | Endpoint | Description | Request Body (ตัวอย่าง) |
| :--- | :--- | :--- | :--- |
| `POST` | `/accounts` | สร้างบัญชีธนาคารใหม่ | `{"accountNumber": "1234567890", "ownerName": "Tank", "balance": 0}` |
| `GET` | `/accounts/{id}` | ดูข้อมูลบัญชีและยอดเงินคงเหลือ | *(ไม่มี)* |
| `POST` | `/accounts/{id}/deposit` | ฝากเงินเข้าบัญชี (ควบคุมด้วย Transaction) | `{"amount": 1000}` |

---

## 🧪 การทำงานของ Transaction (`@Transactional`)

ในคลาส `DepositService.java` เมธอด `deposit()` จะทำงาน 2 ขั้นตอนพร้อมกัน:
1. อัปเดตยอดเงินในตาราง `account`
2. บันทึกประวัติการฝากเงินในตาราง `deposit_transaction`

* **กรณีปกติ (Commit):** ทำงานสำเร็จทั้ง 2 ขั้นตอน ข้อมูลถูกบันทึกลงฐานข้อมูลจริง
* **กรณีเกิดข้อผิดพลาด (Rollback):** หากเกิด `RuntimeException` ระบบจะทำการยกเลิก (Rollback) ข้อมูลทั้ง 2 ขั้นตอนกลับสู่สถานะเดิมทันที ไม่มีการบันทึกค้างไว้ครึ่งทาง

---

## 🏃 วิธีการรันโปรเจกต์ (How to Run)

1. ตั้งค่าการเชื่อมต่อฐานข้อมูลใน `src/main/resources/application.properties`
2. รันคำสั่งใน Terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
3. เซิร์ฟเวอร์จะเริ่มทำงานที่ `http://localhost:8080`

---

## 📑 เอกสารรายงานผลการทดลอง

รายงานผลการทดลองพร้อมภาพประกอบและการตอบคำถามท้าย Lab ทั้ง 6 ข้อจัดทำไว้ในโฟลเดอร์โปรเจกต์:
* 📄 **ไฟล์ Word:** `รายงานผลการทดลอง Lab 9_ Spring Boot - Transaction.docx`
* 📑 **ไฟล์ PDF:** `รายงานผลการทดลอง Lab 9_ Spring Boot - Transaction.pdf`
