package com.manuonda.urlshortener.web.controller;



import com.manuonda.urlshortener.domain.exceptions.ShortUrlNotFoundException;
import com.manuonda.urlshortener.domain.models.CreateShortUrlCmd;
import com.manuonda.urlshortener.domain.models.ShortUrlDto;
import com.manuonda.urlshortener.service.ShortUrlService;
import com.manuonda.urlshortener.ApplicationProperties;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.manuonda.urlshortener.web.dto.CreateShortUrlForm;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class ShortUrlController {

    private static final Logger logger  = LoggerFactory.getLogger(ShortUrlController.class.getName());
    private final ShortUrlService shortUrlService;
    private final ApplicationProperties applicationProperties;

    public ShortUrlController(ShortUrlService shortUrlService, ApplicationProperties applicationProperties) {
        this.shortUrlService = shortUrlService;
        this.applicationProperties = applicationProperties;
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
        if(bindingResult.hasErrors() && bindingResult.getErrorCount() > 0){
            model.addAttribute("shortUrls", this.shortUrlService.findAllPublicShortUrls());
            model.addAttribute("baseUrl", this.applicationProperties.baseUrl());
            model.addAttribute("title", "URL Shortener");
            model.addAttribute("createShortUrlForm", form);
            return "index";
        }

        try{
           CreateShortUrlCmd cmd   = new CreateShortUrlCmd(
                   form.originalUrl(),
                   form.isPrivate(),
                   form.expirationInDays(),
                   null);
            var shortUrlDto = this.shortUrlService.createShortUrl(cmd);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Short URL created successfully! "+
                    this.applicationProperties.baseUrl()+"/s/"+shortUrlDto.shortKey());
        } catch (Exception e){
            logger.error("Error creating short URL", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating short URL");
        }

        return  "redirect:/";
    }

    @GetMapping("/s/{shortKey}")
    String redirectToOriginalUrl(@PathVariable String shortKey) {
        Optional<ShortUrlDto> shortUrlDtoOptional  = this.shortUrlService.accessShortUrl(shortKey);
        if (shortUrlDtoOptional.isEmpty()) {
            throw new ShortUrlNotFoundException("Invalid short URL key: " + shortKey);
        }
        ShortUrlDto shortUrlDto = shortUrlDtoOptional.get();
        return "redirect:" + shortUrlDto.originalUrl();
    }
}
