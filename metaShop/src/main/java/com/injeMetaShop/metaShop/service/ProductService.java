package com.injeMetaShop.metaShop.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.injeMetaShop.metaShop.dto.ProductDto;
import com.injeMetaShop.metaShop.entity.Product;
import com.injeMetaShop.metaShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 구성 파일(application.properties 또는 application.yml)에서 설정한 값 주입
    @Value("${spring.cloud.gcp.storage.bucketName}")
    private String bucketName;

    public List<Product> allProduct(){
        return productRepository.findAll();
    }

    public List<Product> categoryOfProduct(String category){
        return productRepository.findAllByCategory(category);
    }

    public List<Product> sexOfProdcut(String sex){
        return productRepository.findAllBySex(sex);
    }

    public Product addProduct(ProductDto productDto, MultipartFile thumbnail, MultipartFile fbxFile) throws IOException {
        // Product 생성
        Product product = Product.addProduct(productDto);

        // Google Cloud Storage에 파일 업로드
        if (thumbnail != null && !thumbnail.isEmpty()) {
            String thumbnailFileName = "assets/thumbnails/" + thumbnail.getOriginalFilename();
            uploadFile(thumbnail, thumbnailFileName);
            product.setImagePath(getGoogleCloudStorageUrl(thumbnailFileName));
        }

        if (fbxFile != null && !fbxFile.isEmpty()) {
            String fbxFileName = "assets/fbxs/" + fbxFile.getOriginalFilename();
            uploadFile(fbxFile, fbxFileName);
            product.setFbxPath(getGoogleCloudStorageUrl(fbxFileName));
        }

        // Product 저장
        return productRepository.save(product);
    }

    private void uploadFile(MultipartFile file, String fileName) throws IOException {
        // 파일을 임시 경로에 저장
        Path tempFile = Files.createTempFile("temp-", "-suffix");
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        // Google Cloud Storage로 파일 업로드
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, Files.readAllBytes(tempFile));

        // 임시 파일 삭제
        Files.delete(tempFile);
    }

    private String getGoogleCloudStorageUrl(String fileName) {
        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }
}
