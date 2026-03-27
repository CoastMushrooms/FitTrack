import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main extends JPanel
{
  Scanner keys = new Scanner(System.in);
  JFrame frame;
  String [] Cat; 
  String [] Cat2;
  String [] Results;
  String [] Results2;
  String [][] grid;
  String [][] sheet;
  int Days = 0;
  
  public Main()
  {
    frame = new JFrame("Fitness Tracker");
    frame.add(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(4000, 2000);
    perfo();
    fill();
    repaint();
    frame.setVisible(true);
    plan();
    repaint();
    Days = 1;

    for(int dayCount = 1; dayCount <= 14; dayCount++)
      {
        workout();
        advice();
        Days++;
      }
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0,0,600,600);
    g.setColor(Color.BLACK);
    
    for(int row = 0; row < grid.length; row++)
    {
      for(int col = 0; col < grid[0].length-1; col++)
      {
        g.drawRect(col * 100 + 50, row * 30 + 30, 200, 30);
      }
    }
     
    for(int row = 0; row < sheet.length; row++)
    {
      for(int col = 0; col < sheet[0].length-1; col++)
      {
        g.drawRect(col * 100 + 50, (row * 30 + 30) + 100, 200, 30);
        
      }
    }
 
    for(int row = 0; row < grid.length; row++)
      {
        for(int col = 0; col < grid[0].length; col++)
        {
          g.setFont(new Font("Arial", Font.BOLD, 15));
          if(row == 0)
          {
            g.drawString(grid[row][col], col * 100 + 55, row * 30 + 50);
          }
          if(row == 1)
          {
            g.drawString(grid[row][col], col * 100 + 55, row * 30 + 50);
          }
        }
      }
    
    for(int row = 0; row < sheet.length; row++)
    {
      for(int col = 0; col < sheet[0].length; col++)
      {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if(sheet[row][col] != null)
        {
          if(col == 0)
          {
            g.drawString("" + sheet[row][col], col * 100 + 90, (row * 30 + 30) + 125);
          }
          if(col > 0)
          {
            g.drawString("" + sheet[row][col], col * 100 + 90, (row * 30 + 30) + 125);
          }
        }
      }
    }
  }
  
  public static void main(String [] args)
  {
    Main run = new Main();
  }

  public void perfo()
  {
    Cat = new String []{"weight (lbs)", "total calories", "excercise time (minutes)", "excercise intensity (Scale of 1 - 10)", "height (in)", "gender (male or female (no caps))"};
    Results = new String [Cat.length];
    System.out.println("Please repond to the following prompts");
    for(int i = 0; i < Cat.length; i++)
    {
      System.out.println("What is your " + Cat[i] + ": ");
      Results[i] = keys.nextLine();
    }
  }

  public void fill()
  {
    grid = new String [2][7];
    Cat2 = new String []{"Weight", "Calories", "ET", "EI", "Height", "Gender"};
    for(int row = 0; row < grid.length; row++)
    {
      for(int col = 0; col < grid[0].length; col++)
      {
        if(row == 0)
        {
          if(col == 0)
          {
            grid[row][col] = "Day";
          }
          else
          {
            grid[row][col] = Cat2[col - 1];
          }
        }
        if(row == 1)
        {
          if(col == 0)
          {
            grid[row][col] = ("" + Days);
          }
          else
          {
            grid[row][col] = Results[col - 1];
          }
        }
      }
    }
    sheet = new String[14][5];
    for(int row = 0; row < sheet.length; row++)
    {
      for(int col = 0; col < sheet.length; col++)
      {
        if(col == 0)
        {
          if(row == 0)
          {
            sheet[row][col] = ("" + (Days + 1));
          }
          else
          {
            sheet[row][col] = ("" + (Days + row + 1));
          }
        }
      }
    } 
  }

  public void workout()
  {
    Cat = new String []{"weight (lbs)", "calories burned", "excercise time", "excercise intensity"};
    Results2 = new String [Cat.length];
    System.out.println("Please repond to the following prompts for todays workout");
    for(int i = 0; i < Cat.length; i++)
    {
      System.out.println("What is your " + Cat[i] + ": ");
      Results2[i] = keys.nextLine();
    }
    tracker();
  }

  public void tracker()
  {
    for(int col = 1; col < sheet[0].length; col++)
      {
        sheet[Days-1][col] = Results2[col-1];
      }
    repaint();
    
    System.out.println("\nYour tracker has been updated. Come back tomorrow to conitnue tracking!");
    
    
  }
  public void advice()
  {
    int weight = Integer.parseInt(sheet[Days-1][1]);
    int height = Integer.parseInt(grid[1][5]);
    int calories = Integer.parseInt(sheet[Days-1][2]);

    if(grid[1][6].equals("male"))
    {
      if(height >= 64)
        {
          if(weight <= 155 && weight >= 130)
          {
            System.out.println("You are regular weight!");
          }
          else if(weight > 155)
          {
            System.out.println("You are overweight.");
          }
          else
          {
            System.out.print("You are underweight.");
          }
        }
      if(height == 64)
      {
        if(weight <= 125 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 125)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height <= 64)
        {
          if(weight <= 130 && weight >= 100)
          {
            System.out.println("You are regular weight!");
          }
          else if(weight > 130)
          {
            System.out.println("You are overweight.");
          }
          else
          {
            System.out.print("You are underweight.");
          }
        }
    }
    if(grid[1][6].equals("female"))
    {
      if(height >= 61)
      {
        if(weight <= 140 && weight >= 120)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 140)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height == 61)
      {
        if(weight <= 120 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 120)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height <= 61)
      {
        if(weight <= 120 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 120)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
    }
  }
  public void plan()
  {
    int weight = Integer.parseInt(grid[1][1]);
    int height = Integer.parseInt(grid[1][5]);
    int calories = Integer.parseInt(grid[1][2]);
    
    if(grid[1][6].equals("male"))
    {
      if(height >= 64)
        {
          if(weight <= 155 && weight >= 130)
          {
            System.out.println("You are regular weight!");
          }
          else if(weight > 155)
          {
            System.out.println("You are overweight.");
          }
          else
          {
            System.out.print("You are underweight.");
          }
        }
      if(height == 64)
      {
        if(weight <= 125 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 125)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height <= 64)
        {
          if(weight <= 130 && weight >= 100)
          {
            System.out.println("You are regular weight!");
          }
          else if(weight > 130)
          {
            System.out.println("You are overweight.");
          }
          else
          {
            System.out.print("You are underweight.");
          }
        }
    }
    if(grid[1][6].equals("female"))
    {
      if(height >= 61)
      {
        if(weight <= 140 && weight >= 120)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 140)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height == 61)
      {
        if(weight <= 120 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 120)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
      if(height <= 61)
      {
        if(weight <= 120 && weight >= 100)
        {
          System.out.println("You are regular weight!");
        }
        else if(weight > 120)
        {
          System.out.println("You are overweight.");
        }
        else
        {
          System.out.print("You are underweight.");
        }
      }
    }
  }
}