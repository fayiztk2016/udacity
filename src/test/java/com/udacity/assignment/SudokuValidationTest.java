package com.udacity.assignment;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;

public class SudokuValidationTest {

  @Test
  public void validInputTest(){

    int[][] matrix = {{1,2},{3,4}};
    HashMap conditions =  new HashMap<String, String>();

    boolean out= SudokuValidation.validate(matrix,conditions);
    assertEquals(out, true);
  }

  @Test
  public void invalidInputTest(){

    int[][] matrix = {{1,2},{1,4}};
    HashMap conditions =  new HashMap<String, String>();

    boolean out= SudokuValidation.validate(matrix,conditions);
    assertEquals(out, false);
  }


  @Test
  public void conditionsTest(){

    int[][] matrix = {{1,2},{3,4}};
    HashMap condition1 =  new HashMap<String, ArrayList<String>>(){{
      put("1,2", new ArrayList(Arrays.asList("1,1")));
    }};

    boolean out= SudokuValidation.validate(matrix,condition1);
    assertEquals(out, true);

    HashMap condition2 =  new HashMap<String, ArrayList<String>>(){{
      put("1,1", new ArrayList(Arrays.asList("1,2")));
    }};
    out= SudokuValidation.validate(matrix,condition2);
    assertEquals(out, false);


    HashMap condition3 =  new HashMap<String, ArrayList<String>>(){{
      put("2,2", new ArrayList(Arrays.asList("1,2","2,1")));
    }};
    out= SudokuValidation.validate(matrix,condition3);
    assertEquals(out, true);


  }

}