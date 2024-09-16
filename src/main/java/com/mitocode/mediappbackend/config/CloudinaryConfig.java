package com.mitocode.mediappbackend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name" , "dxko4ycdf",
                    "api_key" , "399622694139693",
                    "api_secret" , "OMbJWatW5A5R7g0DRGQSfuqPZE8"
        ));
    }
}
