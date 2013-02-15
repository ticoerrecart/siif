package ar.com.siif.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LogAction extends DispatchAction {

	private byte[] getByteArrayFromFile(File file) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(file);
		// System.out.println(file.exists() + "!!");
		// InputStream in = resource.openStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum); // no doubt here is 0
				// Writes len bytes from the specified byte array starting at
				// offset off to this byte array output stream.
				System.out.println("read " + readNum + " bytes,");
			}
		} catch (IOException ex) {
		}
		return bos.toByteArray();

		/*
		 * // below is the different part File someFile = new File("java2.pdf");
		 * FileOutputStream fos = new FileOutputStream(someFile);
		 * fos.write(bytes); fos.flush(); fos.close();
		 */
	}

	@SuppressWarnings("unchecked")
	public ActionForward log(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			File file = new File("/usr/local/tomcat-7.0.6/logs/catalina.out");
			byte[] bytes = getByteArrayFromFile(file);

			// Lo muestro en la salida del response
			response.setContentType("text/plain");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}
}
