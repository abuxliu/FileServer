package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FileServer extends WebMvcConfigurerAdapter {
    @Value("${local.fileserver.dir}")
    private String localFileServerDir;

    @Value("${local.fileserver.path}")
    private String localFileServerPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + this.getLocalFileServerPath() + "/**").addResourceLocations("file:" + this.getLocalFileServerDir() + "/");
        super.addResourceHandlers(registry);
    }

    public String toServerPath(String absolutePath) {
        absolutePath = absolutePath.replaceAll("\\\\", "/");
        return "/" + absolutePath.replace(localFileServerDir, localFileServerPath);
    }

    public String getLocalFileServerDir() {
        return localFileServerDir;
    }

    public void setLocalFileServerDir(String localFileServerDir) {
        this.localFileServerDir = localFileServerDir;
    }

    public String getLocalFileServerPath() {
        return localFileServerPath;
    }

    public void setLocalFileServerPath(String localFileServerPath) {
        this.localFileServerPath = localFileServerPath;
    }
}
