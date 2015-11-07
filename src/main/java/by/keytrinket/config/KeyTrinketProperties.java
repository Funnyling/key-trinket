package by.keytrinket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * @author ntishkevich
 */
@ConfigurationProperties(prefix = "keytrinket", ignoreUnknownFields = false)
public class KeyTrinketProperties {

    private final Mail mail = new Mail();

    public Mail getMail() {
        return mail;
    }

    public Security getSecurity() {
        return security;
    }

    private final Security security = new Security();

    public static class Mail {

        private String from = "localhost@localhost";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
    }

    public static class Security {

        private final RememberMe rememberme = new RememberMe();

        private final Authentication authentication = new Authentication();

        public RememberMe getRememberme() {
            return rememberme;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private final Xauth xauth = new Xauth();

            public Xauth getXauth() {
                return xauth;
            }

            public static class Xauth {

                private String secret;

                private int tokenValidityInSeconds = 1800;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public int getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }
            }
        }
        public static class RememberMe {

            @NotNull
            private String key;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
