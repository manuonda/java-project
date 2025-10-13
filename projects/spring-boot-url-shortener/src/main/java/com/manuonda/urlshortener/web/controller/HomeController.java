package com.manuonda.urlshortener.web.controller;



import com.manuonda.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.manuonda.urlshortener.web.dto.CreateShortUrlForm;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        model.addAttribute("baseUrl", "http://localhost:8080");
        model.addAttribute("createShortUrlForm", new CreateShortUrlForm("", false, 30));
        return "index";
    }


    @PostMapping("/short-urls")
    String createShortUrl(@ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form ,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("shortUrls", this.shortUrlService.findAllPublicShortUrls());
            model.addAttribute("baseUrl", "http://localhost:8080");
            model.addAttribute("title", "URL Shortener");
            model.addAttribute("createShortUrlForm", form);
            return "index";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Short URL created successfully!");
        return  "redirect:/";
    }
}
