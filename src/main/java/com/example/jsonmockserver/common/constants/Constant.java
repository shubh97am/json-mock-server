package com.example.jsonmockserver.common.constants;

public class Constant {
    private Constant() {

    }

    public static class Fields {
        private Fields() {

        }

        public static final String READ_STORED_RESULT_FILE_PATH = "store1.json";
        public static final String WRITE_STORED_RESULT_FILE_PATH = "store1.json";
    }

    public static class Controller {
        private Controller() {

        }

        public static final String BASE_URL = "/api/mock-server";
    }

    public static class Logging {
        private Logging() {

        }

        public static final String KEY_CLASS_NAME = "className";
        public static final String KEY_METHOD_NAME = "methodName";
        public static final String KEY_METHOD_ARGUMENTS = "methodArguments";
        public static final String KEY_METHOD_PHASE = "methodPhase";
        public static final String KEY_RETURN_VALUE = "returnValue";
        public static final String KEY_TIME_ELAPSED_IN_MILLIS = "timeElapsedInMillis";
        public static final String KEY_CAUSE = "cause";
        public static final String KEY_STACK_TRACE = "stackTrace";
        public static final String KEY_LOCALIZED_MESSAGE = "localizedMessage";
        public static final String KEY_SUPPRESSED = "suppressed";
        public static final String KEY_MESSAGE = "message";
        public static final String KEY_TIME = "time";
        public static final String KEY_EPOCH_TIME = "epochTime";
        public static final String METHOD_PHASE_STARTED = "started";
        public static final String METHOD_PHASE_COMPLETED = "completed";
        public static final String METHOD_PHASE_EXCEPTION_THROWN = "exceptionThrown";
    }


}
