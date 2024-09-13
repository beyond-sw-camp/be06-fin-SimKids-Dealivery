package org.example.backend.domain.board.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.category.repository.CategoryRepository;
import org.example.backend.domain.board.model.dto.BoardDto;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.model.entity.ProductThumbnailImage;
import org.example.backend.domain.board.repository.ProductBoardRepository;
import org.example.backend.domain.board.repository.ProductThumbnailImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductBoardService {
	private final ProductBoardRepository productBoardRepository;
	private final ProductThumbnailImageRepository productThumbnailImageRepository;
	private final CategoryRepository categoryRepository;
	private final AmazonS3 s3;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Transactional
	public void create(BoardDto.BoardCreateRequest boardCreateRequest, MultipartFile[] productThumbnails, MultipartFile productDetail) {
		List<String> thumbnailUrls = uploadImage(productThumbnails);
		String productDetailUrl = uploadImage(productDetail);
		Category category = categoryRepository.findByName(boardCreateRequest.getCategory().getType());

		ProductBoard productBoard = boardCreateRequest.toEntity(thumbnailUrls.get(0), productDetailUrl, category);
		productBoardRepository.save(productBoard);

		thumbnailUrls.forEach((url) -> {
			ProductThumbnailImage productThumbnailImage =  boardCreateRequest.toEntity(url, productBoard);
			productThumbnailImageRepository.save(productThumbnailImage);
		});
	}


	private List<String> uploadImage(MultipartFile[] files) {
		return Arrays.stream(files)
			.map(this::uploadImage)
			.collect(Collectors.toList());
	}

	private String uploadImage(MultipartFile file) {
		String folderPath = getFolderPath();
		String fileName = getFileName(folderPath, file.getOriginalFilename());

		ObjectMetadata objectMetadata = getS3ObjectMetadata(file.getContentType(), file.getSize());
		uploadToS3(fileName, file, objectMetadata);

		return s3.getUrl(bucket, fileName).toString();
	}

	private String getFolderPath() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "/";
	}

	private String getFileName(String folderPath, String fileName) {
		return folderPath + UUID.randomUUID().toString().substring(0, 8) + "_" + fileName;
	}

	private ObjectMetadata getS3ObjectMetadata(String contentType, long size) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(size);
		return objectMetadata;
	}

	private void uploadToS3(String fileName, MultipartFile file, ObjectMetadata objectMetadata) {
		try {
			s3.putObject(bucket, fileName, file.getInputStream(), objectMetadata);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
