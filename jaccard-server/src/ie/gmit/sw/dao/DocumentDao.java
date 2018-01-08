package ie.gmit.sw.dao;

import ie.gmit.sw.model.Document;

/**
 * @author weichenwang
 *
 */
public interface DocumentDao {
	Document getDocumentByObj(Document d);

	void saveDocument(Document d);
}
