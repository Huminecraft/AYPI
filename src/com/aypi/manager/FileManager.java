package com.aypi.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
	
	private File file;
	
	public FileManager(File file) {
		this.file = file;
		
		if(!file.exists()) {
			System.out.println("Generate file: "+file.getPath());
			
			new File(file.getParentFile().getPath()).mkdirs();
			
			try {
				
				file.createNewFile();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void printLine(String line) {
		
		try(PrintWriter print = new PrintWriter(new FileOutputStream(this.file, true))){
			print.println(line);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void printList(List<String> list) {
		
		try(PrintWriter print = new PrintWriter(new FileOutputStream(this.file, true))){
			
			for(String str : list){
				print.println(str);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void removeAllLine(String line) {
		
		ArrayList<String> list = new ArrayList<>();
		
		try(FileInputStream fis = new FileInputStream(file)){
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNext()){
				String str = sc.nextLine();
				if(!str.equalsIgnoreCase(line)) {
					list.add(str);
				}
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		this.clearFile();
		
		this.printList(list);
		
		list.clear();
		
	}
	
	public void removeLine(String line) {
		
		ArrayList<String> list = new ArrayList<>();
		
		boolean first = true;
		
		try(FileInputStream fis = new FileInputStream(file)){
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNext()){
				String str = sc.nextLine();
				if(str.equalsIgnoreCase(line) && first) {
					first = false;
				}else{
					list.add(str);
				}
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		this.clearFile();
		
		this.printList(list);
		
		list.clear();
		
	}
	
	
	
	public void removeAllList(List<String> list) {
		
		for(String line : list) {
			this.removeAllLine(line);
		}
		
	}
	
	
	public void removeList(List<String> list) {
		
		for(String line : list) {
			this.removeLine(line);
		}
		
	}
	
	public void clearFile() {
		file.delete();
		try {
			file.createNewFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTextFile() {
		ArrayList<String> toReturn = new ArrayList<String>();
		
		try(FileInputStream fis = new FileInputStream(file)){
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNext()){
				String str = sc.nextLine();
				toReturn.add(str);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return toReturn;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
	public void delete() {
		File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    delete(f);
                }
            }
        }
        file.delete();
	}
	
	
    public static void copyFullRecursive(File src, File dest) throws IOException
    {
        if (src.isDirectory())
        {
            File dir = new File(dest, src.getName());
            dir.mkdir();
            
            File[] list = src.listFiles();
            if (list != null)
                for (File fic : list)
                    copyFullRecursive(fic, dir);
        }
        else
        {
            Files.copy(src.toPath(), new File(dest, src.getName()).toPath());
        }
    }
    
    public static void delete(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    delete(f);
                }
            }
        }
        file.delete();
    }

}
