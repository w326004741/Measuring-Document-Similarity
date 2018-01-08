package ie.gmit.sw.service.impl;

import java.io.IOException;
import java.util.Set;

import ie.gmit.sw.KShingle;
import ie.gmit.sw.MinHash;
import ie.gmit.sw.dao.DocumentDao;
import ie.gmit.sw.dao.impl.DocumentDaoImpl;
import ie.gmit.sw.model.Document;
import ie.gmit.sw.service.DocumentService;

/**
 * @author weichenwang
 *
 */
public class DocumentServiceImpl implements DocumentService {
	private DocumentDao ddao = new DocumentDaoImpl();

	public String checkDocumentAndSave(Document d) throws Exception {
		Document dobj = new Document();
		dobj.setId(1);
		dobj = ddao.getDocumentByObj(dobj);
		Set<String> set = KShingle.jaccard(5, dobj.getContext(), d.getContext());
		String jaccard = MinHash.minHashJaccard(5, set, dobj.getContext(), d.getContext());
		d.setJaccard(String.valueOf(jaccard));
		ddao.saveDocument(d);
		return String.valueOf(jaccard);
	}

}
