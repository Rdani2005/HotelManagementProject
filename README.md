# 🏨 Hotel Administrator

**Hotel Administrator** is a Java console-based application designed for managing a hotel’s core operations — including **users**, **employees**, and **contracts** — following **Domain-Driven Design (DDD)** principles and a **service-oriented architecture**.

---

## 🚀 Key Features

- 🔐 **User authentication** via the `UserService` module.
- 👥 **Employee management**, including creation, activation, deactivation, and deletion.
- 📄 **Contract management**, linked to employees with built-in business rules:
  - An employee can have only one active contract.
  - An employee must exist before assigning a contract.
- 🧩 **Modular and scalable architecture**, with clear separation of concerns:
  - `domain.entity` – domain entities with business invariants.
  - `domain.service` – domain services implementing business logic.
  - `domain.service.repository` – repository interfaces and in-memory implementations.
  - `services` – Composition Root for dependency injection and service wiring.
  - `application` – console-based user interface with separate menus per module.


  ## ⚙️ Requirements

- **Java 17 or higher**
- **Maven** or any compatible build tool
- No database required — repositories are **in-memory implementations** (`UserRepositoryImpl`, `EmployeeRepositoryImpl`, `ContractRepositoryImpl`).

---

## ▶️ How to Run

1. **Compile the project:**
```bash
   javac -d out $(find src -name "*.java")
```

2. **Run the application:**

```bash
    java -cp out edu.hotel.administrator.MainApp
```


3. **Default login credentials:**
```txt
    Username: ROOT
    Password: ROOT
```

## 🧱 Design Principles

- Domain-Driven Design (DDD)
    Each aggregate (User, Employee, Contract) defines its own identity (UserId, EmployeeId, ContractId) and business invariants.

- Composition Root Pattern
    Dependency injection is centralized in CompositionRoot.java, keeping configuration in a single place.

- Repository Pattern (In-Memory)
    Each aggregate has a repository (UserRepository, EmployeeRepository, ContractRepository) managing entities through in-memory arrays.

- Clean Separation of Concerns
    Console menus (ui.menu.*) interact only through domain/application services — never directly with repositories.

## 🧰 Development Notes

- Repositories are currently in-memory only, ideal for academic or prototyping purposes.

- They can easily be extended to JDBC, Hibernate, or JPA with minimal changes by implementing the same repository interfaces.

## 👨‍💻 Author

Daniel Ricardo Sequeira Campos
Developer & Architect of Hotel Administrator


## 📜 License

This project is licensed under the MIT License.
Feel free to use it for educational or demonstration purposes.

> "A well-structured system doesn’t just perform actions — it reflects its domain rules." 💡
