package ie.gmit.sw.dao.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import ie.gmit.sw.dao.DocumentDao;
import ie.gmit.sw.model.Document;

/**
 * @author weichenwang
 *
 */
public class DocumentDaoImpl implements DocumentDao {

	public Document getDocumentByObj(Document d) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "documnet.yap");
		try {
			d = (Document) db.queryByExample(d).get(0);
			// ObjectSet<Object> set = db.queryByExample(d);
			System.out.println(d);
		} finally {
			db.close();
		}
		return d;
	}

	public void saveDocument(Document d) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "documnet.yap");
		try {
			db.store(d);
		} finally {
			db.close();
		}
	}

}
