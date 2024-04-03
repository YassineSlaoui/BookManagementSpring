package com.ys.bookmanagementspring.conf;

public enum UserRole {
    /**
     * Admin can Create, Read, Update, and Delete Books
     */
    ADMIN,
    /**
     * Publisher can Create, Read, and Update Books
     */
    PUBLISHER,
    /**
     * READ_ONLY is a normal user that can only Read Books
     */
    READ_ONLY
}
