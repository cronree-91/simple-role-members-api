package jp.cron.sample.api.response;

public abstract class Response<T> {
    public boolean success;
    public int code;
    public String message;
    public T data;
}
