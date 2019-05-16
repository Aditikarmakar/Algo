/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring2018cg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ObjGenerator {

    public static void main(String[] args) {
        String filename = "models.txt";
        
        try (RandomAccessFile input = new RandomAccessFile(filename, "r")) {
            String line;
            
            while ((line = input.readLine()) != null) {
                if (line.startsWith("cube")) {
                    String tokens[] = line.split("\\ "); // regex
                    int length = Integer.parseInt(tokens[1]);
                    generateCube(length);
                }
                else if (line.startsWith("cylinder")) {
                    String tokens[] = line.split("\\ "); // regex
                    int len = tokens.length;
                    for(int i=1; i<len; i+=2){
                        double  radius = Integer.parseInt(tokens[i]);
                        int  height = Integer.parseInt(tokens[i+1]);
                        generateCylinder(radius, height);
                        
                    } 
                }
                else if (line.startsWith("sphere")) {
                    String tokens[] = line.split("\\ "); //regex
                    int radius = Integer.parseInt(tokens[1]);
                    generateSphere(radius);
                } 
                else {
                    // add your own code for cyllinder and helix
                }
            }
            
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   private static void generateCube(int length) {
        try (RandomAccessFile output = new RandomAccessFile("output.obj", "rw")) {
            output.setLength(0);
            output.writeBytes("o Cube\n");
            double halfLength = length / 2.0;
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", halfLength, -halfLength, halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", halfLength, -halfLength, -halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", -halfLength, -halfLength, -halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", -halfLength, -halfLength, halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", halfLength, halfLength, halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", halfLength, halfLength, -halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", -halfLength, halfLength, -halfLength));
            output.writeBytes(String.format("v %.3f %.3f %.3f\n", -halfLength, halfLength, halfLength));

            //output.writeBytes(String.format("f %d %d %d %d\n", 1, 2, 3, 4));
            
            output.writeBytes(String.format("f %d %d %d %d\n", 5, 6, 7, 8));
            output.writeBytes(String.format("f %d %d %d %d\n", 1, 4, 3, 2));
            output.writeBytes(String.format("f %d %d %d %d\n", 1, 2, 6, 5));
            output.writeBytes(String.format("f %d %d %d %d\n", 4, 8, 7, 3));
            output.writeBytes(String.format("f %d %d %d %d\n", 2, 3, 7, 6));
            output.writeBytes(String.format("f %d %d %d %d\n", 1, 5, 8, 4));
            // add the other two faces by yourself
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static void generateCylinder(double radius, int height) {
        try (RandomAccessFile output = new RandomAccessFile("output.obj", "rw")) {
            output.setLength(0);
            output.writeBytes("o Cylinder\n");
            
            double r=radius, z=height/2;
            int n=32;    //n=vertices
            
            for(int i=0;i<n;i++) {
                double x=(r*Math.cos((i*Math.PI*2)/n));
                double y=(r*Math.sin((i*Math.PI*2)/n));
                output.writeBytes(String.format("v %.3f %.3f %.3f\n", x, z, y));
                output.writeBytes(String.format("v %.3f %.3f %.3f\n", x, -z, y));
            }
            
            
            for(int i=0;i<(n-1)*2;i+=2){
                    output.writeBytes(String.format("f %d %d %d %d\n", i+1, i+2, i+4, i+3));
            } output.writeBytes(String.format("f %d %d %d %d\n", n*2-1, n*2, 2, 1));
             
                
            output.writeBytes(String.format("f"));
            for(int i=1; i<=n*2; i+=2){
                output.writeBytes(String.format(" %d", i ));            
            } output.writeBytes(String.format("\n"));
        
            
            output.writeBytes(String.format("f"));
            for(int i=2; i<=n*2; i+=2){
                output.writeBytes(String.format(" %d", i ));            
            } output.writeBytes(String.format("\n"));
            
               //output.writeBytes(String.format("f %d %d %d %d %d", 1, 3, 5, 7, 9));
               //output.writeBytes(String.format("f %d %d %d %d %d", 2, 4, 6, 8, 10));

            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generateSphere(int radius) {
        try (RandomAccessFile output = new RandomAccessFile("output.obj", "rw")) {
            output.setLength(0);
            output.writeBytes("o Sphere\n");
            
            double r=radius;
            
           /* for(int i=0;i<??;i++) {
                double x = radius*Math.sin thita Math.cos phi;
                double y = radius*Math.sin thita *Math.sin phi;
                double z = radius*Math.cos thita;
                output.writeBytes(String.format("v %.3f %.3f %.3f\n", x, z, y)); 
            }
            
            for(int i=0;i<??;i++){
                output.writeBytes(String.format("f %d %d %d %d\n", i));
            }*/
 
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
