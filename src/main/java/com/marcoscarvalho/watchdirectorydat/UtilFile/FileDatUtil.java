package com.marcoscarvalho.watchdirectorydat.UtilFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDatUtil {

	private static final Logger logger = LogManager.getLogger(FileDatUtil.class);
	private static final String NEW_LINE = "\n";

	public static synchronized List<String> readFile(String file) {

		List<String> list = new LinkedList<String>();

		BufferedReader br = null;
		String linha = "";
		try {
			br = new BufferedReader(new FileReader(file));
			while ((linha = br.readLine()) != null) {
				list.add(linha);
			}

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		return list;
	}

	public static synchronized String generateFile(String nameFile, LinkedList<String> list) {

		String file = nameFile.replace(".dat", "").replace(".DAT", "") + "."
				+ new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(Calendar.getInstance().getTime()) + ".done.dat";

		try {
			FileWriter writer = new FileWriter(file);

			for (String linha : list) {
				writer.append(linha);
				writer.append(NEW_LINE);
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return file;

	}

}
