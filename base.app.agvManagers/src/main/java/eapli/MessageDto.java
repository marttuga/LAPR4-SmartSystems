package eapli;

public class MessageDto {

    private int version;
    private int code;
    private int size;
    private byte[] data_message;

    public MessageDto(int version, int code, int size, byte[] data_message) {
        this.version = version;
        this.code = code;
        this.size = size;
        this.data_message = data_message;
    }

    public int getVersion() {
        return version;
    }

    public int getCode() {
        return code;
    }

    public int getSize() {
        return size;
    }

    public byte[] getData_message() {
        return data_message;
    }
}
