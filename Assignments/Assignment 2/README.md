# Assignment 2 - Parallel and Distributed Computing Lab

**Course:** CS-426L, Fall 2026  
**University:** Air University, Kharian

This folder contains beginner-oriented Java implementations for Questions 1-7 from the assignment PDF. The programs use simple menus, input validation, comments, and the class names requested in the assignment. Each question is in its own directory so it can be compiled and run independently.

## Before submitting

- Replace the student-name, registration-ID, semester/section, and GitHub URL placeholders in the Word report.
- Run every program on your own computer and check that you understand each class and method before the viva.
- Capture one genuine, uncropped, full-screen VS Code + Terminal screenshot for every question. Paste each screenshot into the matching evidence area in the Word report. In Word, add a rectangle shape with **No Fill** and a **red outline** around the important terminal output. The report currently contains clearly labelled placeholders rather than fabricated screenshots.
- Create your own GitHub repository, push the code, and replace the repository-link placeholder in the report. The files have not been pushed to GitHub from this package.
- The assignment asks for 50+ readable commit messages. Create meaningful commits as you build, test, fix, and document the project; do not use filler commits or claim commits that you did not make.
- Rename the report using your actual registration ID and semester/section, following `RegistrationID-A#2-SemesterSection` (for example, `247127-A#2-BSCYS-F24-A`).

## Requirements

- Java JDK 8 or newer.
- VS Code or another Java editor, plus a terminal.
- Compile each question inside its own directory because the programs are designed to run independently.

## Compile and run

Open a terminal in each question folder, then run the command shown below. Use `javac *.java` to compile all classes in that question, then run the main class.

| Question | Directory | Compile and run |
|---|---|---|
| 1 | `src/Question1` | `javac *.java` then `java TradingEngineApp` |
| 2 | `src/Question2` | `javac *.java` then `java PacketSymmetryAuditor` |
| 3 | `src/Question3` | `javac *.java` then `java AsyncWebEngineApp` |
| 4 | `src/Question4` | `javac *.java` then `java ForkJoinPortfolioApp` |
| 5 | `src/Question5` | `javac *.java` then `java SensorGridMonitorApp` |
| 6 | `src/Question6` | `javac *.java` then `java InventoryCatalogManager` |
| 7 | `src/Question7` | `javac *.java` then `java CacheLocalityBenchmark` |

Example for Question 1:

```bash
cd src/Question1
javac *.java
java TradingEngineApp
```

## Input validation included

- Menu options must be whole numbers within the displayed range.
- Required text fields cannot be blank.
- Monetary values and the Question 4 risk cap must be finite numbers greater than zero.
- Question 1 checks that the ask price is not lower than the bid price.
- Question 2 limits payloads to 256 non-blank characters to fit the fixed-size arrays.
- Question 5 checks that the upper reading is greater than the lower reading.
- Question 6 limits product count to 10-1000 while retaining the required 10 threads and 50,000 operations per test.

## Important notes

- These are educational benchmark programs. Runtime numbers vary with processor load, JVM warm-up, and the computer used. Do not alter or invent measured values in the report.
- Question 4 may show a serial/parallel ratio below 1 for a 10,000-value array because Fork/Join has overhead for small workloads. The code prints the measured result rather than guaranteeing a speedup.
- Question 7 uses a 2000 x 2000 matrix and performs 10 measured passes for each traversal order.