package ie.gmit.sw;

import java.io.IOException;

import ie.gmit.sw.model.Document;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * @author weichenwang
 *
 */
public class DB4Oinit {
	public DB4Oinit() {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "documnet.yap");
		Document d = new Document();
		try {
			d.setContext(KShingle.ReadFile1());
			d.setId(1);
			d.setJaccard("");
			d.setTitle("text");
			db.store(d);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
}
