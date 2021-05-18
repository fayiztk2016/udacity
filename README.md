

# Problem

The game has an n by n board. Each row and column will be filled by numbers between 1 and n. Like Sudoku, each
number can appear once and only once in each row and each column. But unlike Sudoku some
adjacent cells have additional constraints between them. For example, in the board shown
below cell (1, 1) should be greater than cell (1, 2), cell (2,3) should be greater than cell (3,3),
and so on.

Assumption: As of now, support only one constraint , that is `greater than`

How do we run this: 
- ./gradlew shaodwjar
- java -cp build/libs/udacity-1.0-SNAPSHOT-all.jar com.udacity.assignment.SudokuValidation
- follow the instructions to give inputs

A sample illustration is given below
```shell
java -cp build/libs/udacity-1.0-SNAPSHOT-all.jar com.udacity.assignment.SudokuValidation
enter size of the matrix separated by comma
3,3
numbers for matrix:
enter numbers separated by comma for row 1
1,2,3
enter numbers separated by comma for row 2
2,5,8
enter numbers separated by comma for row 3
10,11,12
Number of extra conditions:
2
input format: x,y<space>a,b  -> denotes (x,y) greater than (a,b)
indexes to support condition 1
1,2 1,1
indexes to support condition 2
2,3 2,2

valid solution
```
