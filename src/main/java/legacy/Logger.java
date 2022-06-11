package legacy;

public interface Logger {

    void logInfo(String message);

    class Sout implements Logger {

        @Override
        public void logInfo(String message) {
            System.out.println("[INFO]: " + message);
        }
    }
}
