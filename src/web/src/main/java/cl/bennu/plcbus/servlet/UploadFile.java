package cl.bennu.plcbus.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class UploadFile
 */

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
	        if (isMultipart) {

                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(repository);

// Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
                try {
                    List<FileItem> items2 = upload.parseRequest(request);
                    System.out.println(items2);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }

                // Create a factory for disk-based file items
                factory = new DiskFileItemFactory();
                //factory.setSizeThreshold(THRESHOLD_SIZE);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        	// Create a new file upload handler
	        	upload = new ServletFileUpload(factory);
                //upload.setFileSizeMax(MAX_FILE_SIZE);
                //upload.setSizeMax(MAX_REQUEST_SIZE);
	            try {
	            	// Parse the request
					List /* FileItem */ items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();	 
	                        String root = getServletContext().getRealPath("/");
	                        File path = new File(root + "/uploads");
	                        if (!path.exists()) {
								boolean status = path.mkdirs();
	                        }
	 
	                        File uploadedFile = new File(path + "/" + fileName);
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                    }
	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}

}
