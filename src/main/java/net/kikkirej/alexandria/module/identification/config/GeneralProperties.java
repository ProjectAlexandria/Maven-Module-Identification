package net.kikkirej.alexandria.module.identification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("alexandria")
public class GeneralProperties {
    String sharedfolder = "/alexandriadata";

    public String getSharedfolder() {
        return sharedfolder;
    }

    public void setSharedfolder(String sharedfolder) {
        this.sharedfolder = sharedfolder;
    }
}
