package info.bliki.wiki.client.filter;

import info.bliki.wiki.client.filter.tags.AbstractTag;

import java.util.ArrayList;


public class TokenStack extends ArrayList {

	private static final long serialVersionUID = 7377721039394435077L;

	public TokenStack() {
		super();
	}

//	public TokenStack(int initialCapacity) { 
//		super(initialCapacity);
//	}

	/**
	 * @return
	 */
	public AbstractTag peek() {
		return (AbstractTag) get(size() - 1);
	}

	/**
	 * @return
	 */
	public Object pop() {
		return remove(size() - 1);
	}

	/**
	 * @param item
	 * @return
	 */
	public boolean push(AbstractTag item) {
		return add(item);
	}

}
