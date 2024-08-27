package dev.service.cloud.domain.request;

public class Request {
    private long library_id;
    private String name;

    public Request(long library_id, String name) {
        super();
        this.library_id = library_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request [library_id=" + library_id + ", name=" + name + "]";
    }

}
