// $Id$
package org.hibernate.search.test.analyzer;

import java.io.Reader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Token;


/**
 * @author Emmanuel Bernard
 */
public abstract class AbstractTestAnalyzer extends Analyzer {

	protected abstract String[] getTokens();

	public TokenStream tokenStream(String fieldName, Reader reader) {
		return new InternalTokenStream();
	}

	private class InternalTokenStream extends TokenStream {
		private int position;

		public Token next() throws IOException {
			if ( position >= getTokens().length) {
				return null;
			}
			else {
				return new Token( getTokens()[position++], 0, 0 );
			}
		}
	}
}
