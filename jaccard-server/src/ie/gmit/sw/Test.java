package ie.gmit.sw;

import java.io.IOException;
import java.io.InputStream;

import ie.gmit.sw.model.Document;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * @author weichenwang
 *
 */
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "documnet.yap");
		try {
			// Add the original document 添加原始文档
			Document d = new Document();
			d.setContext(KShingle.ReadFile1());
			d.setId(1);
			d.setJaccard("");
			d.setTitle("text");
			db.store(d);
			// Query the document 查询文档
			// d.setId(1);
			// d = (Document) db.queryByExample(d).get(0);
			// System.out.println(d.getContext());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

}
