package org.example.backend.global.infra.db;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.category.repository.CategoryRepository;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.product.model.entity.Product;
import org.example.backend.domain.board.product.repository.ProductRepository;
import org.example.backend.domain.board.repository.ProductBoardRepository;
import org.example.backend.global.common.constants.BoardStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final CategoryRepository categoryRepository;
    private final ProductBoardRepository productBoardRepository;
    private final ProductRepository productRepository;

    private List<Category> categories;
    private List<ProductBoard> productBoards;


    @PostConstruct
    public void execute() {
        insertCategory();
        insertBoard();
        insertProduct();
    }
    private void insertCategory(){
        List<String> categoryNames = List.of("식품", "의류", "뷰티", "라이프");
        categories = categoryNames.stream()
                .map(name -> Category.builder().name(name).build())
                .collect(Collectors.toList());

       categoryRepository.saveAll(categories);
    }

    private void insertBoard(){

        if (categories.size() == 0) {
            return;
        }

        productBoards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productBoards.add(ProductBoard.builder()
                    .title("[음성명작]500m 고랭지에서 수확한 사과1.3kg[품종:홍로] " + i)
                    .discountRate(23)
                    .startedAt(LocalDateTime.now())
                    .endedAt(LocalDateTime.now().plusDays(1))
                    .category(categories.get(0))
                    .productThumbnailUrl("sample-thumnail-url")
                    .productDetailUrl("sample-detail-url")
                    .status(BoardStatus.READY.getStatus())
                    .minimumPrice(15900)
                    .build());
        }

        productBoardRepository.saveAll(productBoards);
    }
    private void insertProduct() {

        if (productBoards.size() == 0) {
            return;
        }

        List<Product> products = new ArrayList<>();
        for (int i=0; i<productBoards.size();i++) {
            for (int j=0;j<2;j++) {
                products.add(Product.builder()
                        .name("상품 " + j)
                        .stock(100)
                        .price((i + 1) * 10000)
                        .productBoard(productBoards.get(i))
                        .build());
            }
        }

        productRepository.saveAll(products);
    }
}
