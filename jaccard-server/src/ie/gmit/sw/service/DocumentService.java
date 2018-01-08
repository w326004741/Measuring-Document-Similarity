package ie.gmit.sw.service;

import ie.gmit.sw.model.Document;

/**
 * @author weichenwang
 *
 */
public interface DocumentService {
	String checkDocumentAndSave(Document d) throws Exception;
}
