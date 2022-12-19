/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;
import java.io.File;
import javax.swing.JOptionPane;
/**
 *
 * @author kingrishabdugar
 */
public class OpenPdf {
    public static void openById(String id,String path){
        try{
//location:\\ + "Green-Leaf-Gourmet_Bill_" +id(file name of bill)
            if((new File(path+"Green-Leaf-Gourmet_Bill_"+id+".pdf")).exists()){
                String fph = "rundll32 url.dll,FileProtocolHandler "+path;
                Process p = Runtime
                        .getRuntime()
                        .exec(fph + "Green-Leaf-Gourmet_Bill_"+id+".pdf" );
                
            }
            else
                JOptionPane.showMessageDialog(null, "File does not Exist");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
