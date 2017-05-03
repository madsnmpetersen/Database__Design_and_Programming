/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer_store;

/**
 *
 * @author ShameOnU
 */
public class Computer_store 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        /* Create and display gui */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                SQLHandler handler = new SQLHandler();
                handler.init();
                new GUI(handler).setVisible(true);
            }
        });      
    }    
}