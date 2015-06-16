package client.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

public class Searcher {
	private String resultFile = null;
	private String[] fileName = null;
	private String[] fileExts;
	private PrintStream printStream;
	//private StringBuilder build = new StringBuilder(""); 

	public Searcher() {
		fileExts = new String[0];
	}

	public void setOutputFile(String name) {
		this.resultFile = name;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public void setFileExts(String[] fileExts) {
		this.fileExts = fileExts;
	}

	public void setFileExts(List<String> fileExts) {
		this.fileExts = fileExts.toArray(new String[fileExts.size()]);
	}

	// Поиск по корневым дискам
	public void find() throws FileNotFoundException {
		printStream = new PrintStream(new FileOutputStream(resultFile, true),
				true);
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println("set disc - " + root.getPath());
			doFind(root.getPath());
		}
		printStream.close();
	}

	// Сравнивает имена файлов
	private boolean isFileNameEqual(File file) {
		if (fileName == null)
			return isFileExtEqual(file);

		String fullName = file.getName();
		for(int i = 1; i<fileName.length; i++){
		if (fullName.contains(".")){
		String curFileName = fullName.substring(0, fullName.lastIndexOf("."));
	
			if (fileName[i].equals(curFileName))
				return true;
		
		return false;
		}
		if (fileName[i].equals(fullName))
			return true;
		}
	return false;
	}
	
	// Сравнивает расширения файлов
	private boolean isFileExtEqual(File file) {
		String fileNameStr = file.getName();

		if (!fileNameStr.contains("."))
			return false;

		String curExt = fileNameStr.substring(fileNameStr.lastIndexOf(".") + 1,
				fileNameStr.length());
		for (int i = 1; i <fileExts.length; i++) {
			if (fileExts[i].equals(curExt))
				return true;
		}
		return false;
	}

	// Производит непосредственно поиск по заданному пути
	private void doFind(String path) {
		File f = new File(path);
		File[] list = f.listFiles();
		try {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					doFind(list[i].getPath());
				}
				if (isFileNameEqual(list[i])) {
					String fileInfo = list[i].getName() + "\r\n"
							+ list[i].getPath() + "\r\n"
							+ new Date(list[i].lastModified()) + "\r\n"
							+ list[i].length() + " bytes\r\n";
					writeToFile(fileInfo);

					System.out.println(list[i].getPath());
				}
			}
			;
		} catch (NullPointerException ex) {
			return;
		}
	}

	// Записывает информацию про найденный файл
	private void writeToFile(String data) {
		printStream.println(data);
		//build.append(data);
	}

}
