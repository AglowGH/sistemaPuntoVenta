/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basedatos;

/**
 *
 * @author rick
 */
public class Injection 
{
    public Injection()
    {
        
    }
    
    public String checarSQLInjection(String input)
    {
        String newInput = input.replace("/","");
        newInput = newInput.replace("-","");
        newInput = newInput.replace("=","");
        newInput = newInput.replace("(","");
        newInput = newInput.replace(")","");
        newInput = newInput.replace("%","");
        newInput = newInput.replace("'","");
        newInput = newInput.replace(":","");
        return newInput;
    }
    
    
}
