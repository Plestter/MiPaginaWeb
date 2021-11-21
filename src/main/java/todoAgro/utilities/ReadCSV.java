package todoAgro.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import javax.servlet.http.Part;

public class ReadCSV {
	private static final Logger LOG = Logger.getLogger(ReadCSV.class);

	@SuppressWarnings("finally")
	public static final List<String[]> getInfoCSV(Part archivoExcel, String path) throws IOException {
		LOG.info(" *** cargarCSV: " + archivoExcel.getContentType());
		LOG.info(" path: " + path);

		List<String[]> infoCSV = new ArrayList<String[]>();
		File folder = createFolder(path);
		path = saveFileTmp(archivoExcel, folder, path);
		CSVReader reader = null;

		try {
			reader = new CSVReader(new FileReader(path), TransactionConstants.SEPARATOR, TransactionConstants.QUOTE);
			String[] nextLine = null;
			while ((nextLine = reader.readNext()) != null) {
				LOG.info(Arrays.toString(nextLine));
				infoCSV.add(nextLine);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			infoCSV = null;
		} finally {
			LOG.info(" ** End Excel");
			if (null != reader) {
				reader.close();
				Path patDel = Paths.get(path);
				Files.delete(patDel);
			}
			return infoCSV;
		}
	}

	private static final File createFolder(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

	private static final  String saveFileTmp(Part archivoExcel, File folder, String path) throws IOException {
		String nuevoNombre = archivoExcel.getSubmittedFileName();
		Files.copy(archivoExcel.getInputStream(), (new File(folder, nuevoNombre)).toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		return path += nuevoNombre;
	}
	
	public static final String delLastElement(String cad) {
		return cad.substring(0, cad.length()-1);
	}
}
