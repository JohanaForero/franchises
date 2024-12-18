package com.accenture.franchises.domain.exception;

import java.io.Serial;

public class FranchiseException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -7251982212216647350L;

    FranchiseException(final String message) {
        super(message);
    }

    public static class FranchiseAlreadyExistsException extends FranchiseException {
        @Serial
        private static final long serialVersionUID = -489471689131499313L;

        public FranchiseAlreadyExistsException(final String message){
            super(message);
        }

    }

    public static class ServerExceptionDB extends FranchiseException {
        @Serial
        private static final long serialVersionUID = 2690945267548434525L;

        public ServerExceptionDB(final String message){
            super(message);
        }

    }

    public static class BranchServiceException extends FranchiseException {
        @Serial
        private static final long serialVersionUID = -8630630071543047818L;

        public BranchServiceException(final String message){
            super(message);
        }

    }

    public static class ProductNotFoundException extends FranchiseException {
        @Serial
        private static final long serialVersionUID = 3978370933682047820L;

        public ProductNotFoundException(final String message){
            super(message);
        }

    }
}
