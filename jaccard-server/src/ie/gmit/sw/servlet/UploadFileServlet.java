package ie.gmit.sw.servlet;

import ie.gmit.sw.DB4Oinit;
import ie.gmit.sw.model.Document;
import ie.gmit.sw.service.DocumentService;
import ie.gmit.sw.service.impl.DocumentServiceImpl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class UploadFileServlet
 */

@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocumentService dservice = new DocumentServiceImpl();
	private int id = 2;
	private DB4Oinit init = new DB4Oinit();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		String jaccard = "";
		Document d = new Document();
		// 1、Create a DiskFileItemFactory factory 创建一个了DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、Create a file upload parser 创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Solve the upload file name Chinese garbled 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		try {
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for (FileItem item : list) {
				// If the fileitem package is normal input data.
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					value = new String(value.getBytes("iso8859-1"), "UTF-8");
					System.out.println(name + "=" + value);
					if (name.equals("title")) {
						d.setTitle(value);
						d.setId(id);
					}
				} else {// If fileitem package is the upload file
						// 如果fileitem中封装的是上传文件
						// Get the name of the uploaded file 得到上传的文件名称
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// Processes the path part of the file name of the obtained
					// uploaded file,
					// leaving only the part of the file name.
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// Get the input stream of the uploaded file in item.
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// retrieve data 获取数据
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line);
						sb.append("\n");
					}
					d.setContext(sb.toString());
					System.out.println(sb.toString());
					in.close();
					item.delete();
					jaccard = dservice.checkDocumentAndSave(d);
					id++;
					message = "success!";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "error!";
		}

		request.setAttribute("message", message);
		request.setAttribute("jaccard", jaccard);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
