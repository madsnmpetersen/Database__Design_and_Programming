/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer_store;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ShameOnU
 */
public class SQLHandler 
{
    Connection c = null;
    HashMap<String, Integer> prices = new HashMap();
    HashMap<String, Integer> sellPrices = new HashMap();
    
    public void init()
    {
        try
        {
            if(connect())
                System.out.println("Connected to database");
            else
                System.out.println("Not connected");
            buildPricesDict();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
    
    public Boolean connect()
    {
      try 
      {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer store", "postgres", "123");
         return true;
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         return false;
      }
        
    }
    
    public ResultSet executeSQL(String statement)
    {
        try
        {
            return c.createStatement().executeQuery(statement);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return null;
        }      
    }
    
    public Boolean updateSQL(String statement)
    {
        try
        {
           c.createStatement().executeUpdate(statement);
           return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }        
    }
    
    public void closeConnection()
    {
        try
        {
            if(c != null)
            c.close();
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    /*
    Takes a result set containing only 1 column and returns a String array.
    */
    public String[] resultSetToArray(ResultSet rs) throws SQLException
    {
        ArrayList<String> helper = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        if(meta.getColumnCount() > 1)
        {
            System.out.print("Too many columns");
            return null;
        }
        else
        {
            int i;
            for(i = 0 ; rs.next() ; i++)
            {
                helper.add(rs.getString(1));
            }
            
            String[] result = new String[i];
            
            for(int ni = 0 ; !helper.isEmpty() ; ni++)
            {
                result[ni] = helper.get(0);
                helper.remove(0);
            }            
            rs.close();
            return result;
        }
        
    }
    
    public int getComponentPrice(String component) throws SQLException
    {
        String statement =
                "SELECT price "
               +"FROM component "
               +"WHERE name = '"+component+"';";
        //System.out.println(statement);
        ResultSet rs = executeSQL(statement);
        if(rs.next())
        {
            int result = rs.getInt(1);
            rs.close();
            return  result;
        }
        else
        {
            rs.close();
            return 0;
        }
        
    }
    
    private int getComponentQty(String component) throws SQLException
    {
        String statement =
                "SELECT qty "
               +"FROM component "
               +"WHERE name = '"+component+"';";
        //System.out.println(statement);
        ResultSet rs = executeSQL(statement);
        if(rs.next())
        {
            int result = rs.getInt(1);
            rs.close();
            return  result;
        }
        else
        {
            rs.close();
            return 0;
        }
        
    }
    
    public ResultSet getAllComponents()
    {
        String statement =
        "SELECT * "
      + "FROM component;";
        return executeSQL(statement);
    }
    
    public ResultSet getAllComponentNames()
    {
        String statement =
        "SELECT name "
      + "FROM component;";
        return executeSQL(statement);
    }
    
    public ResultSet getAllSystemNames()
    {
        String statement =
        "SELECT name "
      + "FROM system;";
        return executeSQL(statement);
    }
    
    public ResultSet getAllSystems()
    {
        String statement =
        "SELECT * "
      + "FROM system;";
        return executeSQL(statement);
    }
    
    private ResultSet getSystem(String system)
    {
        String statement =
        "SELECT * "
      + "FROM system "
      + "WHERE name = '"+system+"';";
        return executeSQL(statement);
    }
    
    public int getSystemPrice(String system)
    {
        return prices.get(system);
    }
    
    public int getSystemSellPrice(String system)
    {
        return sellPrices.get(system);
    }
    
    private void buildPricesDict() throws SQLException
    {
        ResultSet systems = getAllSystems();
        ResultSetMetaData meta = systems.getMetaData();
        while(systems.next())
        {
            String name = null;
            int price = 0;
            for(int i = 1 ; i <= meta.getColumnCount() ; i++)
            {
                if(i == 1)
                {                    
                    name = systems.getString(i);
                }
                else
                {
                    String comp = systems.getString(i);
                    System.out.println(comp);
                    if(comp != null)
                    price = price + getComponentPrice(comp);
                }
            }
            prices.put(name, price);
            price = (int) (price * 1.3);
            if(price < 100)
                    sellPrices.put(name, 99);
                else
                {
                    price = price / 100;
                    price = price + 1;
                    price = price * 100;
                    price = price - 1;
                    sellPrices.put(name, price);
                }
        }
        systems.close();
    }
    
    public int systemsInStock(String system) throws SQLException
    {
        ResultSet rs = getSystem(system);
        if(rs.next())
        {
            ArrayList<Integer> list = new ArrayList();
            int columns = rs.getMetaData().getColumnCount();
            for(int i = 2 ; i < columns ; i++)
            {
                String comp = rs.getString(i);
                if(!comp.equals(""))
                {
                    list.add(getComponentQty(comp));
                }
            }
            return java.util.Collections.min(list);
        }
        else
            return 0;       
    }
    
    public Boolean buyComponents(String component, int qty) throws SQLException
    {
        int currentQty = getComponentQty(component);
        if( currentQty >= qty)
        {
            String statement =
                    "UPDATE component "
                   +"SET qty = "+(currentQty - qty)+" "
                   +"WHERE name ='"+component+"';";
            try
            {
                return updateSQL(statement);
            }
            catch(Exception e)
            {
                System.out.println(e);
                return false;
            }
        }
        else
            return false;
    }
    
    public Boolean buySystems(String system, int qty) throws SQLException
    {
        if(systemsInStock(system) >= qty)
        {
            ResultSet rs = getSystem(system);
            if(rs.next())
            {
                ArrayList<String> list = new ArrayList();
                String comp;
                for(int i = 2 ; i < rs.getMetaData().getColumnCount() ; i++)
                {
                    if(!((comp = rs.getString(i)).equals("")))
                    list.add(comp);
                }
                while(!list.isEmpty())
                {
                 if(buyComponents(list.get(0),qty))
                    list.remove(0);
                    else
                     return false;
                }
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    public int getCompRestock(String component) throws SQLException
    {
        String statement =
        "SELECT component.name, component.qty, stock_rules.pref_qty "
      + "FROM component, stock_rules "
      + "WHERE component.name = stock_rules.name AND component.name ='"+component+"';";
        ResultSet rs = executeSQL(statement);
        if(rs.next())
        {
            int restock = rs.getInt("pref_qty")-rs.getInt("qty");
            if(restock > 0)
                return restock;
            else
                return 0;
        }
        else
            return 0;
        
    }
    
    public Boolean restockAll()
    {
        ResultSet rs = getAllComponentNames();
        try 
        {
            String[] comps = resultSetToArray(rs);
            for(String comp : comps)
            {
                int restock = getCompRestock(comp);
                if(restock>0)
                {
                    String statement=
                    "UPDATE component "
                   +"SET qty = qty + "+restock+" "
                   +"WHERE name = '"+comp+"';";
                    updateSQL(statement);
                }
            }
            return true;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return false;
        }
    }
}