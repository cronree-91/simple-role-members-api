package jp.cron.sample.api.response;

import java.util.List;

public class RoleMembersResponse extends Response<List<String>> {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean success;
        private int code;
        private String message;
        private List<String> data;

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(List<String> data) {
            this.data = data;
            return this;
        }

        public RoleMembersResponse build() {
            RoleMembersResponse response = new RoleMembersResponse();
            response.success = this.success;
            response.code = this.code;
            response.message = this.message;
            response.data = this.data;
            return response;
        }
    }
}
