package ie.gmit.sw.model;

/**
 * @author weichenwang
 *
 */
public class Document {
	private java.lang.Integer id;
	private java.lang.String title;
	private java.lang.String context;
	private java.lang.String jaccard;

	/**
	 * @return id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * @return title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * @return context
	 */
	public java.lang.String getContext() {
		return context;
	}

	/**
	 * @param context
	 */
	public void setContext(java.lang.String context) {
		this.context = context;
	}

	/**
	 * @return jaccard
	 */
	public java.lang.String getJaccard() {
		return jaccard;
	}

	/**
	 * @param jaccard
	 */
	public void setJaccard(java.lang.String jaccard) {
		this.jaccard = jaccard;
	}
}
