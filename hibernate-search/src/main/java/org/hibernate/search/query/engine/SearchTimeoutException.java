/* 
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.hibernate.search.query.engine;

import org.apache.lucene.search.Query;

/**
 * Represent a timeout during a Fulltext search in the HSQuery.
 * The Hibernate integration should catch this and throw an
 * {@link org.hibernate.QueryTimeoutException} instead.
 * 
 * @author Sanne Grinovero <sanne@hibernate.org> (C) 2011 Red Hat Inc.
 */
public class SearchTimeoutException extends RuntimeException {
	
	public static final TimeoutExceptionFactory DEFAULT_TIMEOUT_EXCEPTION_FACTORY = new DefaultSearchTimeoutException();
	
	private SearchTimeoutException(String message, Query query) {
		super( message + " \"" + query + '\"' );
	}
	
	private static class DefaultSearchTimeoutException implements TimeoutExceptionFactory {

		@Override
		public SearchTimeoutException createTimeoutException(String message, Query query) {
			return new SearchTimeoutException( message, query );
		}
		
	}

}
