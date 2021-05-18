package com.udacity.assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import lombok.SneakyThrows;

public class SudokuValidation {

  @SneakyThrows
  public static void main(String[] args) throws IOException{

    int rowNum;
    int colNum;
    Scanner sc = new Scanner(System.in);
    System.out.println("enter size of the matrix separated by comma");
    String[] size = sc.nextLine().split(",");
    if (size.length ==2){
        rowNum = Integer.parseInt(size[0]);
        colNum = Integer.parseInt(size[1]);
    }else{
      throw new IOException("not a valid size");
    }


    System.out.println("numbers for matrix:");
    int[][] matrix =new int[rowNum][colNum];
    for (int i=0;i<rowNum;i++){
      System.out.println("enter numbers separated by comma for row "+ (i+1));
      String[] row = sc.nextLine().split(",");
      for(int j=0;j<colNum;j++){
        matrix[i][j]= Integer.parseInt(row[j]);
      }
    }

    System.out.println("Number of extra conditions:");
    int numCondition = Integer.parseInt(sc.nextLine());
    if (numCondition >0 )
      System.out.println("input format: x,y<space>a,b  -> denotes (x,y) greater than (a,b)");

    Pattern PATTERN = Pattern.compile("[0-9]+,[0-9]+ [0-9]+,[0-9]+");
    HashMap<String, ArrayList<String>> condMap=new HashMap<String,ArrayList<String>>();
    for(int i=0;i<numCondition;i++){

      System.out.println("indexes to support condition "+ (i+1));
      String condition = sc.nextLine();

      if (PATTERN.matcher(condition).matches()) {
        String[] indexes = condition.split(" ");
        ArrayList<String> arr = condMap.getOrDefault(indexes[0], new ArrayList<String>());
        arr.add(indexes[1]);
        condMap.put(indexes[0], arr);
      } else{
        throw new IOException("invalid condition format");
      }
    }

    boolean out =validate(matrix,condMap);
    if (out){
      System.out.println("\nvalid solution");
    }else{
      System.out.println("\ninvalid solution");
    }
  }

  public static boolean validate(int[][] matrix, HashMap<String,ArrayList<String>> hm){
    HashMap<Integer, HashSet<Integer>> rowMap =  new HashMap<Integer, HashSet<Integer>>();
    HashMap<Integer, HashSet<Integer>> colMap =  new HashMap<Integer, HashSet<Integer>>();

    for (int i=0;i<matrix.length;i++){
      for (int j=0;j<matrix[0].length;j++){

        int current = matrix[i][j];

        if ((rowMap.containsKey(i) && rowMap.get(i).contains(current))|
            (colMap.containsKey(j) && colMap.get(j).contains(current))
        ){
          return false;
        }

        //need to sync with requirement of index starting with 1, instead of starting with 0
        String currentIndex = (i+1)+","+(j+1);
        if(hm.containsKey(currentIndex)) {
          for (String cond : hm.get(currentIndex)) {
            String[] index = cond.split(",");
            if (current < matrix[Integer.parseInt(index[0])-1][Integer.parseInt(index[1])-1]) {
              return false;
            }
          }
        }

        HashSet<Integer> row=rowMap.getOrDefault(i,new HashSet<Integer>());
        row.add(current);
        rowMap.put(i,row);

        HashSet<Integer> col=colMap.getOrDefault(j,new HashSet<Integer>());
        col.add(current);
        colMap.put(j,col);

      }
    }

    return true;
  }


}
