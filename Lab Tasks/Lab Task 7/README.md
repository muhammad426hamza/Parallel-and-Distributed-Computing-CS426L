# Parallel and Distributed Computing Lab – Lab Task 7

## Overview

This project contains six separate beginner-friendly Java programs for practicing matrix multiplication, loop ordering, two-dimensional arrays, array traversal, memory locality, and execution-time measurement.

Each question is saved in its own `.java` file and can be compiled and executed independently. The programs use fundamental Java features such as `Scanner`, `Random`, arrays, methods, `for` loops, `while`/`do-while` loops, and `System.nanoTime()`.

## Requirements

- Java Development Kit (JDK) installed.
- A terminal such as PowerShell, Command Prompt, or the VS Code terminal.
- Enough available memory for the matrix size being tested.

Check Java installation with:

```bash
java -version
javac -version
```

## Project Files

| File | Description |
|---|---|
| `Q1_MatrixMultiplication.java` | Multiplies two square matrices using the `i → j → k` loop order. The default practical size is 2048 × 2048; a smaller/custom size is also available for testing. |
| `Q2_CompareLoopOrders.java` | Compares all six loop orders: `i-j-k`, `i-k-j`, `j-i-k`, `j-k-i`, `k-i-j`, and `k-j-i`. It uses the same input matrices for each order, resets the result matrix, and checks that the results match. |
| `Q3_DisplayMatrix.java` | Creates a 4 × 4 integer matrix, fills it with values from 1 to 16, and displays it through a simple menu. |
| `Q4_RowColumnTraversal.java` | Creates a square two-dimensional array and compares row-wise and column-wise traversal using repeated measurements and average execution times. |
| `Q5_TemporalSpatialLocality.java` | Demonstrates repeated access to the same array element (temporal locality), consecutive array access (spatial locality), and a comparison of the two patterns. |
| `Q6_DifferentMatrixSizes.java` | Multiplies matrices of sizes 128 × 128, 256 × 256, 512 × 512, and 1024 × 1024 using the same `i → j → k` loop order, then reports execution times. |

## How to Compile and Run

Open a terminal in the project directory.

Compile and run each program separately using its filename and class name.

### Question 1 – Matrix Multiplication

```bash
javac Q1_MatrixMultiplication.java
java Q1_MatrixMultiplication
```

Menu options:

1. Run the required 2048 × 2048 multiplication.
2. Test with a smaller/custom matrix size.
3. Exit.

### Question 2 – Compare Matrix Loop Orders

```bash
javac Q2_CompareLoopOrders.java
java Q2_CompareLoopOrders
```

Menu options:

1. Compare all six loop orders using 2048 × 2048 matrices.
2. Test all six orders using a smaller matrix size.
3. Exit.

### Question 3 – 4 × 4 Matrix Viewer

```bash
javac Q3_DisplayMatrix.java
java Q3_DisplayMatrix
```

Menu options:

1. Display the 4 × 4 matrix.
2. Exit.

### Question 4 – Row-wise and Column-wise Traversal

```bash
javac Q4_RowColumnTraversal.java
java Q4_RowColumnTraversal
```

Menu options:

1. Compare row-wise and column-wise traversal.
2. Exit.

When prompted, enter a positive array size and a positive number of repetitions. For an initial test, try an array size of `500` and `5` repetitions.

### Question 5 – Temporal and Spatial Locality

```bash
javac Q5_TemporalSpatialLocality.java
java Q5_TemporalSpatialLocality
```

Menu options:

1. Demonstrate temporal locality.
2. Demonstrate spatial locality.
3. Compare both access patterns.
4. Exit.

When prompted, enter a positive array size and a positive number of repetitions. For an initial test, try `10000` for the array size and `10` for repetitions.

### Question 6 – Matrix Size Performance Test

```bash
javac Q6_DifferentMatrixSizes.java
java Q6_DifferentMatrixSizes
```

Menu options:

1. Multiply 128 × 128 matrices.
2. Multiply 256 × 256 matrices.
3. Multiply 512 × 512 matrices.
4. Multiply 1024 × 1024 matrices.
5. Compare all matrix sizes.
6. Exit.

## Practical Concepts

### Matrix Multiplication

For square matrices, each result element is calculated by multiplying elements from a row of the first matrix by corresponding elements from a column of the second matrix, then adding those products.

### Loop Ordering

Changing the order of the three nested loops does not change the mathematical matrix product, but it changes the order in which array elements are accessed. In Java, a two-dimensional array is an array of row arrays. Accessing consecutive elements in the same row generally provides better spatial locality than repeatedly moving between different rows.

### Row-wise and Column-wise Traversal

The row-wise version visits all elements of one row before moving to the next row. The column-wise version visits one column across separate row arrays. These access patterns can behave differently with CPU caches.

### Temporal and Spatial Locality

- **Temporal locality:** recently accessed data is likely to be accessed again soon.
- **Spatial locality:** data near a recently accessed memory location is likely to be accessed soon.

Caches help by keeping recently used data and nearby data available for faster access.

## Timing Notes

- The programs use `System.nanoTime()` to measure elapsed time.
- Q1 and Q2 measure the matrix multiplication section after the matrices have been initialized.
- Q4 reports average row-wise and column-wise traversal times over the requested number of repetitions.
- Q5 measures the selected access patterns; its timing is a simple demonstration, not a full JVM benchmarking suite.
- Q6 measures the multiplication section for each matrix size.
- Results depend on the processor, memory, cache, JVM, operating-system activity, and warm-up effects. Run tests more than once when practical and record the actual results from your computer.
- Execution time alone does **not** give the exact number of cache hits or cache misses.

## Recommended Test Inputs

Use smaller inputs first to confirm that each program runs correctly. Larger required matrix sizes can take considerably longer.

| Program | Suggested initial test |
|---|---|
| Q1 | Select custom size and enter `64` or `128`. |
| Q2 | Select smaller/custom size and enter `16` or `32`. |
| Q3 | Select option `1` to display the matrix. |
| Q4 | Array size `500`; repetitions `5`. |
| Q5 | Array size `10000`; repetitions `10`. |
| Q6 | Run individual sizes first; then select option `5` to compare all four. |

For the required practical measurements, use the matrix sizes specified in the lab instructions. In particular, Q1 and Q2 include a 2048 × 2048 option. These tests may require substantial time and memory, especially the six multiplications in Q2.

## Important

Enter numeric menu choices and numeric values when prompted. Save the measured execution times from your own computer for the lab report; do not use sample or invented timings as actual results.
