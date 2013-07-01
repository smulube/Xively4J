// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;


/**
 * @author sam
 *
 */
public class XivelyPagedRequest<V> extends XivelyRequest {

    public static final int DEFAULT_START_PAGE = 1;

    public static final int DEFAULT_PAGE_SIZE = 100;

    private final int pageSize;

    private final int page;

    public XivelyPagedRequest() {
        this(DEFAULT_START_PAGE, DEFAULT_PAGE_SIZE);
    }

    /**
     * @param startPage
     * @param pageSize
     */
    public XivelyPagedRequest(int startPage, int pageSize) {
        this.page = startPage;
        this.pageSize = pageSize;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return this.page;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return this.pageSize;
    }
}
