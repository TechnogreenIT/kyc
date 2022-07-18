package com.tes.utilities;

/**
 * Copyright (c) 1995 - 2008 Sun Microsystems
 * This should be read ---- http://www.java2s.com/Code/Java/File-Input-Output/Getextensionpathandfilename.htm
 * Necessary Code For Use this class
 * public class FilenameDemo {
 * public static void main(String[] args) {
 * final String FPATH = "/home/mem/index.html";
 * Filename myHomePage = new Filename(FPATH, '/', '.');
 * System.out.println("Extension = " + myHomePage.extension());
 * System.out.println("Filename = " + myHomePage.filename());
 * System.out.println("Path = " + myHomePage.path());
 * }
 * }
 */
public class Filename
{
	private String fullPath;
	private char pathSeparator, extensionSeparator;

	public Filename(String str, char sep, char ext)
	{
		fullPath = str;
		pathSeparator = sep;
		extensionSeparator = ext;
	}

	public String extension()
	{
		int dot = fullPath.lastIndexOf(extensionSeparator);
		return fullPath.substring(dot + 1);
	}

	public String filename()
	{ // gets filename without extension
		int dot = fullPath.lastIndexOf(extensionSeparator);
		int sep = fullPath.lastIndexOf(pathSeparator);
		return fullPath.substring(sep + 1, dot);
	}

	public String path()
	{
		int sep = fullPath.lastIndexOf(pathSeparator);
		return fullPath.substring(0, sep);
	}

}
