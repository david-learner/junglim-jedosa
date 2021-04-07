package com.jedosa.junglim.home;

import com.jedosa.junglim.admin.menu.main.domain.ProductSampleImage;
import com.jedosa.junglim.admin.menu.main.service.ProductSampleImageService;
import com.jedosa.junglim.article.ArticleService;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ArticleService articleService;
    private final OrderService orderService;
    private final ProductSampleImageService productSampleImageService;

    public HomeController(ArticleService articleService, OrderService orderService, ProductSampleImageService productSampleImageService) {
        this.articleService = articleService;
        this.orderService = orderService;
        this.productSampleImageService = productSampleImageService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        ArticleSearchCondition orderQuestionCondition = new ArticleSearchCondition(
                Board.ORDER_QUESTION.getId(), Boolean.FALSE, Boolean.FALSE, 0, 5);
        ArticleSearchCondition coverSampleCondition = new ArticleSearchCondition(
                Board.COVER_SAMPLE.getId(), Boolean.FALSE, Boolean.FALSE, 0, 4);
        model.addAttribute("articles", articleService.getArticles(orderQuestionCondition));
        model.addAttribute("coverSampleArticles", articleService.getArticles(coverSampleCondition));
        model.addAttribute("productSampleImages", productSampleImageService.getImages());
        model.addAttribute("orders", orderService.getRecentOrders());
        return "home/index";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
}
