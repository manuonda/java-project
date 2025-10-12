package com.manuonda.urlshortener.controller;


import com.manuonda.urlshortener.service.ShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ShortUrlService shortUrlService;

    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "URL Shortener");
        model.addAttribute("shortUrls", this.shortUrlService.findAllPublicShortUrls());
        return "index";
    }


}
