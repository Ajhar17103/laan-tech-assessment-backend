package com.laan.tech.assessment.common;

public class ApiProvider {

    private ApiProvider() {
    }

    public static final String SEPARATOR = "/";
    public static final String BASE = SEPARATOR + "api";
    public static final String VERSION = "/v1";
    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String IDENTIFIER_ID = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;

    public static final class Product {
        public static final String ROOT_PATH = BASE + VERSION + SEPARATOR  + "products";
        public static final String PRODUCT_IDENTIFIER = IDENTIFIER_ID;
        public static final String BULK_UPLOAD = SEPARATOR + "bulk-uploads" ;
    }

    public static final class File {
        public static final String ROOT_PATH = BASE + VERSION + SEPARATOR  + "attachments";
        public static final String FILE_IDENTIFIER = IDENTIFIER_ID;
        public static final String FILE_IDENTIFIER_BY_PRODUCT = SEPARATOR + "by-productId" + IDENTIFIER_ID;
    }


}
