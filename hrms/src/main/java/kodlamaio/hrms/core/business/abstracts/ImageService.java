package kodlamaio.hrms.core.business.abstracts;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface ImageService {
    DataResult<Map> save(MultipartFile file);
}