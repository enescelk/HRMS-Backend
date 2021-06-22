package kodlamaio.hrms;


import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.business.abstracts.ImageService;
import kodlamaio.hrms.core.business.concrates.CloudinaryManager;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ImageService imageService(){
        return new CloudinaryManager(cloudinary());
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "xlaann",
                "api_key", "874735932859311",
                "api_secret", "zK7z3Ov3bk37aHgT2mbOQ3LycVQ"));
    }
}