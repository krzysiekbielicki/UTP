package pl.edu.pjwstk.s8267.utp.lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BetterFile extends File {

	public BetterFile(File f) {
		super(f.getAbsolutePath());
	}
	
	public BetterFile(File parent, String child) {
		super(parent, child);
	}
	
	public BetterFile(String pathname) {
		super(pathname);
	}

	public BetterFile(String parent, String child) {
		super(parent, child);
	}
	
	public BetterFile(URI uri) {
		super(uri);
	}
	
	private List<File> listFilesRecurse(File dir, FileFilter filter) {
		List<File> ret = new ArrayList<File>();
		if(dir.isFile())
			ret.add(dir);
		else {
			File[] sub = dir.listFiles(filter);
			for(File f: sub) {
				ret.addAll(listFilesRecurse(f, filter));
			}
		}
		return ret;
	}
	
	public List<File> listFilesRecurse() {
		return listFilesRecurse(null);
	}
	
	public List<File> listFilesRecurse(FileFilter filter) {
		return listFilesRecurse(this, filter);
	}
	
	private static List<File> listDirsRecurse(File dir, FileFilter filter) {
		List<File> ret = new ArrayList<File>();
		if(dir.isFile())
			ret.add(dir);
		else {
			ret.add(dir);
			File[] sub = dir.listFiles(filter);
			for(File f: sub) {
				ret.addAll(listDirsRecurse(f, filter));
			}
		}
		return ret;
	}
	
	public List<File> listDirsRecurse() {
		return listDirsRecurse(null);
	}
	
	public List<File> listDirsRecurse(FileFilter filter) {
		return listDirsRecurse(this, filter);
	}
	
	private static boolean deleteDir(File dir) {
		boolean ret = true;
		if(dir.isDirectory()) {
			File[] sub = dir.listFiles();
			for(File f: sub) {
					ret = ret && deleteDir(f);
			}
		}
		ret = ret && dir.delete();
		return ret;
	}
	
	public boolean deleteDir() {
		return deleteDir(this);
	}
	
	public boolean copy(File toDir) {
		return copy(toDir.getAbsolutePath(), false);
	}

	public boolean copy(String newName) {
		return copy(newName, false);
	}
	
	private static boolean copy(File f, File newFile, boolean preserveTime) {
		if(f.isFile()) {
			try {
				InputStream in = new FileInputStream(f);
				OutputStream out = new FileOutputStream(newFile);
			    // Transfer bytes from in to out
			    byte[] buf = new byte[1024];
			    int len;
			    while ((len = in.read(buf)) > 0) {
			        out.write(buf, 0, len);
			    }
			    in.close();
			    out.close();
			    newFile.setLastModified(f.lastModified());
			    return true;
			} catch (Exception e) {
				return false;
			}
		}
		boolean ret = true;
		File[] fs = f.listFiles();
		newFile.mkdir();
		for(File ff : fs) {
			ret = ret && copy(ff, new File(newFile, ff.getName()), preserveTime);
		}
		newFile.setLastModified(f.lastModified());
		return ret;
	}
	
	public boolean copy(String newName, boolean preserveTime) {
		return copy(this, new File(newName), preserveTime);
	}
	
	public ArrayList<String> readLines() throws IOException {
		ArrayList<String> ret = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(this));
		String line;
		while((line = br.readLine()) != null)
			ret.add(line);
		br.close();
		return ret;
	}
	
	public static void main(String[] args) {
	}
}
