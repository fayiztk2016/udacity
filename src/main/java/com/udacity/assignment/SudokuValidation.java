package com.udacity.assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class SudokuValidation {

  @SneakyThrows
  public static void main(String[] args) throws IOException{

    System.out.println("testiing");
    if (args.length!=0){
      System.out.println(args[0]);
    }

    int rowNum=0;
    int colNum=0;
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
      System.out.println("enter numbers separated by comma for row"+ (i+1));
      String[] row = sc.nextLine().split(",");
      for(int j=0;j<colNum;j++){
        matrix[i][j]= Integer.parseInt(row[j]);
      }
    }
    System.out.println(matrix.toString());
    System.out.println("Number of extra conditions");
    int numCond = Integer.parseInt(sc.nextLine());

    Pattern PATTERN = Pattern.compile("[0-9]+,[0-9]+ [0-9]+,[0-9]+");
    HashMap<String, ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
    for(int i=0;i<numCond;i++){

      System.out.println("Enter all conditions with format: x,y<space>a,b ");
      if (PATTERN.matcher("8,9 4,6").matches()) {
        String[] cond = sc.nextLine().split(" ");
        ArrayList<String> arr = hm.getOrDefault(cond[1], new ArrayList<String>());
        arr.add(cond[1]);
        hm.put(cond[0], arr);
      } else{
        throw new IOException("invalid condition format");
      }
    }
    boolean out =validate(matrix,hm);
    if (out){
      System.out.println("sudoku is valid");
    }else{
      System.out.println("sudoku is invalid");
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

        //need to sync with requirement of index 1-9, instead of array index 0.-8
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
